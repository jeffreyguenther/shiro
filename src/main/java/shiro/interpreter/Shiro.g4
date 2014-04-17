/*
    Definition of the Shiro dataflow language
*/
grammar Shiro;

@lexer::members{
public static final int WHITESPACE = 1;
public static final int COMMENTS = 2;
}

shiro : statement+
      ;

statement
    :   nodestmt
    |   statestmt
    |   graphDecl
    |   sNode
    |   NEWLINE
    ;

statestmt
	:	STATE stateName BEGIN NEWLINE
		stateHeader
		END     
	;
	
stateHeader
	: 	(stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt | activation | /*activationPath |*/ NEWLINE)+ 	
	;
	
stateTimeStmt
	:	'Time' time
	;

stateCommentStmt
	:	'Comment' comment	
	;
	
stateParentStmt
	:	'Parent' stateParent
	;
	
stateGraphStmt
	:	'Graph' stateGraph
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

nodestmt
    :   nodeType IDENT ('[' activeSelector ']')? BEGIN NEWLINE 
        nodeInternal
        END
    ;

nodeType 
    :   NODE | SUBJUNCT
    ;

nodeInternal
    :   ( nodeProduction
        | portAssignment
        | portstmt
        | NEWLINE)+
    ;

sNode
    :	SUBJ_NODE nodeName=IDENT LSQUARE selectedSubjunct=IDENT RSQUARE BEGIN NEWLINE
		(subjunctDeclNodeProd | subjunctDecl | NEWLINE)+
		END
    ;
	
subjunctDeclNodeProd
	:	nodeName=IDENT PROD_OP newName=IDENT BEGIN NEWLINE
		nodeInternal
		END
	;
	
subjunctDecl
	:	nodestmt
	;
	
subjunctSelector
	:	IDENT
	;

graphDecl
	:	'graph' IDENT BEGIN NEWLINE
		graphLine+
		END
	;
	
graphLine
	:	nodeProduction | portAssignment | NEWLINE
	;

activeSelector	
	:	IDENT
	;

nodeProduction
	:	path (PROD_OP activation)+ NEWLINE//activationPath )+ NEWLINE
	;

/*activationPath
	:	l=activation ('.' (r=activation | activationList))*
	;

activationList
	:	'<' activation (',' activation)* '>'
	;
*/	
activation
	:	nodeName=IDENT ( LSQUARE activeObject=IDENT RSQUARE)?
	;


portAssignment
	:	path '(' mfparams ')' NEWLINE
	;	

portDecl
	:	portType portName mfName
	;
	
portDeclInit
	:	portType portName mfCall
	;

portstmt	
	:	(portDecl | portDeclInit ) NEWLINE
	;	
	
portName 
	:	IDENT
	;
	
portType:	'port'
	| 	'eval'
	;
	
mfCall	:	mfName '(' mfparams ')'
	;
	
mfName 	:	IDENT
	;

mfparams:	expr(',' expr)* 
	;

expression: expr;

// Path
path 	:	(IDENT)('.' IDENT)* (LSQUARE pathIndex RSQUARE)?
	;
	
pathIndex
	:	index=(NUMBER              
        |       STRING_LITERAL)
	;
	
expr
	:	expr  OR_OP expr                        # OrExp
        |       expr (MULT_OP | DIV_OP | MOD_OP) expr   # MultExp
        |       expr ( PLUS_OP | MINUS_OP ) expr        # AddExp
        |       path                                    # PathExp
	|	NUMBER                                  # NumberExp
        |       STRING_LITERAL                          # StringExp
    //    |	'(' expr ')'                            # BracketsExp
	;

/**
* Tokens
* -----------------------------------------------------------------------------
**/

PROD_OP : '->';

STATE
    : 'state'
    ;

SUBJ_NODE
    : 'subjunctive node'
    ;        

NODE
    : 'node';

SUBJUNCT
    : 'subjunct'
    ;

BEGIN
    : 'begin'
    ;

END
    : 'end'
    ;

OR_OP :   '|';
PLUS_OP :   '+';
MINUS_OP : '-';
MULT_OP : '*';
DIV_OP  : '/';
MOD_OP : '%';
LSQUARE: '[';
RSQUARE: ']';

STRING_LITERAL
    :	'"' .*?'"'
    ;

NUMBER 	
    :	DIGIT+ ('.'DIGIT+)?
    ;

IDENT
    : (LCLETTER | UCLETTER | DIGIT)(LCLETTER | UCLETTER | DIGIT|'_')*
    ;

WS :  (' '|'\t'|'\f')+ -> channel(WHITESPACE)
   ;

COMMENT 
    :   '//' ~('\n'|'\r')* -> channel(COMMENTS)
    ;

LINE_COMMENT 
    :   '/*' .*? '*/' NEWLINE? -> channel(WHITESPACE)
    ;

NEWLINE : '\r'?'\n'
        ;

fragment LCLETTER : 'a'..'z';
fragment UCLETTER : 'A'..'Z';
fragment DIGIT : '0'..'9';            