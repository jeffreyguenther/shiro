/*
    Definition of the Shiro dataflow language
*/
grammar Shiro;

shiro : statement+
      ;

statement
    :   nodestmt
    |   statestmt
    |   graphDecl
    |   NEWLINE
    ;

statestmt
	:	'state' stateName 'begin' NEWLINE
		stateHeader
		'end'
	;
	
stateHeader
	: 	(stateTimeStmt | stateCommentStmt | stateParentStmt | stateGraphStmt /*| activationPath |*/ NEWLINE)+ 	
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
	:	nodeName=IDENT ('[' updatePort=IDENT ']')?
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

// Path
path 	:	(IDENT)('.' IDENT)* ('[' pathIndex ']')?
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
    //    |     STRING_LITERAL                          # StringExp
    //    |	'(' expr ')'                            # BracketsExp
	;

OR_OP :   '|';
PLUS_OP :   '+';
MINUS_OP : '-';
MULT_OP : '*';
DIV_OP  : '/';
MOD_OP : '%';

PROD_OP : '->';

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

STRING_LITERAL
    :	'"' .*?'"'
    ;

NUMBER 	
    :	DIGIT+ ('.'DIGIT+)?
    ;

IDENT
    : (LCLETTER | UCLETTER | DIGIT)(LCLETTER | UCLETTER | DIGIT|'_')*
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

fragment LCLETTER : 'a'..'z';
fragment UCLETTER : 'A'..'Z';
fragment DIGIT : '0'..'9';
                    