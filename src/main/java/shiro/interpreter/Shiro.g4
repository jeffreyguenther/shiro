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
    |   NEWLINE
    ;

statestmt
	:	STATE stateName BEGIN NEWLINE
		stateHeader
		END     
	;
	
stateHeader
	: 	(stateGraphStmt | activation | NEWLINE)+ 	
	;
	
stateGraphStmt
	:	'Graph' stateGraph
	;
	
stateName
	:	IDENT
	;
	
stateGraph
	:	IDENT
	;

nodestmt
    :   NODE IDENT ('[' activeSelector ']')? BEGIN NEWLINE 
        nodeInternal
        END
    ;

nodeInternal
    :   ( portstmt
        | subjunctDeclNodeProd
        | OPTION? nodestmt
        | NEWLINE)+
    ;
	
subjunctDeclNodeProd
	:	OPTION instanceName=IDENT PROD_OP type=IDENT BEGIN NEWLINE
		(portAssignment | NEWLINE)+
		END
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
	:	path (PROD_OP activation)+ NEWLINE
	;
	
activation
	:	nodeName=IDENT ( LSQUARE activeObject=IDENT RSQUARE)?
	;


portAssignment
	:	path '(' mfparams ')' NEWLINE
	;	

portDecl
	:	OPTION? portType portName mfName
	;
	
portDeclInit
	:	OPTION? portType portName mfCall
	;

portstmt	
	:	(portDecl | portDeclInit ) NEWLINE
	;	
	
portName 
	:	IDENT
	;
	
portType:	'input'
	| 	'output'
        |       'eval'
	;
	
mfCall	:	mfName '(' mfparams ')'
	;
	
mfName 	:	IDENT
	;

mfparams:	expr(',' expr)* 
	;

expression: expr;

// Path
path 	:	(IDENT|THIS)('.' IDENT)* (LSQUARE pathIndex RSQUARE)?
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

THIS : 'this';
    
OPTION : 'option';
    
PROD_OP : '->';

REFINES_OP: '<-';

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