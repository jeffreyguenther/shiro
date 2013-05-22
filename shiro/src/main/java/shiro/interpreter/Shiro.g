grammar Shiro;

options{
	language = Java;
	ASTLabelType=CommonTree;
	output=AST;
}

tokens{
	NEGATION;
	STATE_DECL;
	PORT_DECL;
	PORT_INIT;
	PORT_ASSIGNMENT;
	PORT_TAG;
	PATH;
	PORT_INDEX;
	EVAL_SELECT;
	NODE_STMT;
	NODE_UPDATE_PORT;
	SUBJ_SELECT;
	SUBJ_NODE_PROD;
	ACTIVATION;
	ACTIVATION_LIST;
	PRODUCES;
}

@header{
package shiro.interpreter;
import java.util.HashMap;
import java.util.Map;

}

@lexer::header{
package shiro.interpreter;
}

@members{
// store the AST from the node definitions
private Map<String, CommonTree> defs = new HashMap<String, CommonTree>();

public Map<String, CommonTree> getNodeDefinitions(){
    return defs;
}	

}

shiro 	: 	statement+
	;	
	
statement
	:	nodestmt
	| sNode 
	|	graphDecl
	|	statestmt
	|	collection
	|	view
	| NEWLINE!
	;
	
view	:	'view' IDENT mfName IDENT -> ^('view' IDENT mfName IDENT)
	;
	
collection
	:	'collection' IDENT orderingFunc path 'begin' NEWLINE
			(collItem)+ NEWLINE?
		'end'
		-> ^('collection' IDENT orderingFunc path collItem+)
	;

collItem:	IDENT -> IDENT
	;

orderingFunc
	:	IDENT -> IDENT
	;
	
statestmt
	:	'state' stateName 'begin' NEWLINE
		stateHeader
		'end' -> ^(STATE_DECL stateName stateHeader)
	;
	
stateHeader
	: 	(stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activationPath | NEWLINE!)+ 	
	;
	
stateTimeStmt
	:	'Time' time -> ^('Time' time)
	;

stateCommentStmt
	:	'Comment' comment -> ^('Comment' comment)	
	;
	
stateParentStmt
	:	'Parent' stateParent -> ^('Parent' stateParent)
	;
	
stateGraphStmt
	:	'Graph' stateGraph -> ^('Graph' stateGraph)
	;
	
stateName
	:	IDENT
	;
	
time	:	STRING_LITERAL	
	;
	
comment	:	STRING_LITERAL
	;
	
stateParent
	:	IDENT
	;
	
stateGraph
	:	IDENT
	;

activationPath
	:	l=activation ('.'^ (r=activation | activationList))*
	;

activationList
	:	'<' activation (',' activation)* '>' -> ^(ACTIVATION_LIST activation+)
	;
	
activation
	:	c=IDENT ('[' v=IDENT ']')? -> ^(ACTIVATION $c ($v)?)
	;
	
graphDecl
	:	'graph' IDENT 'begin' NEWLINE
		graphLine+
		'end'
		-> ^('graph' IDENT graphLine+)
	;
	
graphLine
	:	nodeProduction | portAssignment | NEWLINE!
	;
	
nodeInternal
	:	( nodeProduction 
		| portAssignment 
		| portstmt
		| nodestmt 
		| sNode 
		| NEWLINE!)+
	;
	
nodestmt 
@init{
String nodeName = "";

}
@after{
// add the node AST to list of definition
defs.put(nodeName , $nodestmt.tree);
}
	:	nodeType IDENT {nodeName = $IDENT.text;} ('[' activeSelector ']')? 'begin' NEWLINE
		nodeInternal
		'end' -> ^(NODE_STMT IDENT activeSelector? nodeInternal)
	;
	
nodeType:	'node'| 'subjunct'
	;
	
sNode
@init{
String nodeName = "";

}	
@after{
// add the node AST to list of definition
defs.put(nodeName, $sNode.tree);
}
:	'subjunctive node'^ IDENT {nodeName = $IDENT.text;}'['! subjunctSelector ']'! 'begin'! NEWLINE!
		(subjunctDeclNodeProd | subjunctDecl | NEWLINE!)+
		'end'!
	;
	
subjunctDeclNodeProd
	:	l=IDENT '->' r=IDENT 'begin' NEWLINE
		nodeInternal
		'end' -> ^(SUBJ_NODE_PROD $l $r nodeInternal)
	;
	
subjunctDecl
	:	nodestmt
	;
	
subjunctSelector
	:	IDENT -> ^(SUBJ_SELECT IDENT)
	;
	
activeSelector	
	:	IDENT -> ^(EVAL_SELECT IDENT)
	;

nodeProduction
	:	path ('->'^ activationPath )+ NEWLINE!
	;

portAssignment
	:	path '(' mfparams ')' NEWLINE -> ^(PORT_ASSIGNMENT path mfparams)
	;	

portDecl
	:	portType portName mfName -> ^(PORT_DECL ^(PORT_TAG portType) portName mfName)
	;
	
portDeclInit
	:	portType portName mfCall -> ^(PORT_INIT ^(PORT_TAG portType) portName mfCall)
	;

portstmt	
	:	(portDecl | portDeclInit ) NEWLINE!
	;	
	
portName 
	:	IDENT
	;
	
portType:	'port'
	| 	'eval'
	;
	
mfCall	:	mfName '(' mfparams ')' -> ^(mfName mfparams)
	;
	
mfName 	:	IDENT
	;

mfparams:	expression(',' expression)* -> expression+
	;

// Path
path 	:	(IDENT)('.' IDENT)*('[' pathIndex ']')? -> ^(PATH IDENT+ pathIndex?)
	;
	
pathIndex
	:	portIndex -> ^(PORT_INDEX portIndex)
	;
	
portIndex
	:	( NUMBER |STRING_LITERAL )
	;

// Expressions
term 	:	path
	|	'(' expression ')' -> expression
	|	NUMBER
	| 	STRING_LITERAL
	;
	
unary 	:	('+'^ | '-'^)* term
		
	;

mult 	:	unary (('*'^ | '/'^ | '%'^) unary)*
	;

add 	
	:	mult (( '+'^ | '-'^ ) mult)*
	;
	
expression
	:	add (( '|'^ ) add)*
	;

// LEXEMES
STRING_LITERAL
	:	'"' .* '"'
	;
	
NUMBER 	:	DIGIT+ ('.'DIGIT+)?
	;

IDENT 	:	(LCLETTER | UCLETTER | DIGIT)(LCLETTER | UCLETTER | DIGIT|'_')*
	;

COMMENT
    	:   	'//' ~('\n'|'\r')*  {$channel=HIDDEN;}
   	| 	'/*' ( options {greedy=false;} : . )* '*/' NEWLINE?{$channel=HIDDEN;}
    	;

WS
	:	(' ' | '\t' | '\f')+ {$channel = HIDDEN;}
	;
		
NEWLINE	:	'\r'? '\n'
	;
	
fragment
LCLETTER 
	:	'a'..'z'
	;

fragment
UCLETTER:	'A'..'Z'
	;	
	
fragment
DIGIT	:	'0'..'9'
	;