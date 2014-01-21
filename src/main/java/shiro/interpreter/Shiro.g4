/*
    Definition of the Shiro dataflow language
*/
grammar Shiro;
import ExpressionShared;

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


COMMENT 
    :   '//' ~('\n'|'\r')* -> channel(HIDDEN)
    ;

LINE_COMMENT 
    :   '/*' .*? '*/' NEWLINE? -> skip
    ;

WS :  (' '|'\t'|'\f')+ -> skip
   ;

NEWLINE : '\r'?'\n'
        ;

                    