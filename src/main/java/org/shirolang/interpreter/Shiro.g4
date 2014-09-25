grammar Shiro;

//@lexer::members{
//public static final int WHITESPACE = 1;
//public static final int COMMENTS = 2;
//}

shiro : statement* EOF
	  ;

statement
    :
          nodestmt
        | portstmt
        | inLineExpr
        | NEWLINE
	;

nodestmt
    :   NODE MFNAME ('[' activeSelector ']')? BEGIN NEWLINE
        nodeInternal
        END
    ;

activeSelector
	:	IDENT
	;

nodeInternal
    :   (portstmt
        | NEWLINE)*
    ;

portDecl
	:	portType portName MFNAME
	;
	
portDeclInit
	:	portType portName mfCall
	;

portstmt	
	:	( portDeclInit | portDecl ) NEWLINE
	;	
	
portName 
	:	IDENT
	;
	
portType
    :       PORT
	    |   INPUT
	    |   OUTPUT
        |   EVAL
	;
	
mfCall	:	mfName '(' mfparams ')'
	;
	
mfName 	:	MFNAME
	;

mfparams:	expr(',' expr)* 
	;

path 	:	(IDENT | THIS)('.' IDENT)* (LSQUARE pathIndex RSQUARE)?
        |   REF IDENT('.' IDENT)*
        |   SELECT IDENT('.' IDENT)*
	;
	
pathIndex
	    :	index=(NUMBER
        |   STRING_LITERAL)
	;

inLineExpr : expr NEWLINE
    ;

expr :  '(' expr ')'						  #parensExpr
	 |	NOT_OP expr 				          #notExpr
	 |  MINUS_OP expr      					  #negExpr
	 |  expr AND_OP expr  		  			  #andExpr
	 |  expr OR_OP expr 					  #orExpr
	 |	expr (DIV_OP | MULT_OP | MOD_OP) expr #multExpr
	 |  expr (PLUS_OP | MINUS_OP ) expr       #addExpr
	 |  expr (GT | GTE | LT | LTE) expr       #comparisonExpr
	 |  expr ( EQ | NEQ ) expr                #equalityExpr
	 |  path                                  #pathExpr
	 |	NUMBER 								  #numExpr
	 |  BOOLEAN_LITERAL						  #boolExpr
	 |  STRING_LITERAL                        #stringExpr
	 ;

SELECT: '@';
REF : '~';
PORT: 'port';
INPUT: 'input';
OUTPUT: 'output';
EVAL: 'eval';
THIS : 'this';
NOT_OP   : '!';
AND_OP	 : '&&';
OR_OP    : '||';
PLUS_OP  : '+';
MINUS_OP : '-';
MULT_OP  : '*';
DIV_OP   : '/';
MOD_OP   : '%';
LSQUARE  : '[';
RSQUARE  : ']';
GT		 : '>';
GTE		 : '>=';
LT		 : '<';
LTE      : '<=';
EQ		 : '==';
NEQ      : '!=';

BEGIN: 'begin';
END: 'end';
NODE: 'node';

OPTION : 'option';

BOOLEAN_LITERAL
    : 'true' | 'false'
    ;

STRING_LITERAL
    :	'"' .*?'"'
    ;

NUMBER 	
    :	DIGIT+ ('.'DIGIT+)?
    ;

IDENT
    : LCLETTER (LCLETTER | UCLETTER | DIGIT|'_')*
    ;

MFNAME: UCLETTER (LCLETTER | UCLETTER | DIGIT|'_')*
    ;

WS :  (' '|'\t'|'\f')+ -> channel(HIDDEN)
   ;

COMMENT 
    :   '//' ~('\n'|'\r')* -> channel(HIDDEN)
    ;

LINE_COMMENT 
    :   '/*' .*? '*/' NEWLINE? -> channel(HIDDEN)
    ;

NEWLINE : '\r'?'\n'
        ;

fragment LCLETTER : 'a'..'z';
fragment UCLETTER : 'A'..'Z';
fragment DIGIT : '0'..'9';