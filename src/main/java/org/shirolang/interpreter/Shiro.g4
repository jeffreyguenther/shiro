grammar Shiro;

@lexer::members{
public static final int WHITESPACE = 1;
public static final int COMMENTS = 2;
}

shiro : statement
	  ;

statement :  	expr
			|	portstmt
		  ;

portDecl
	:	portType portName mfName
	;
	
portDeclInit
	:	portType portName mfCall
	;

portstmt	
	:	(portDecl | portDeclInit ) NEWLINE?
	;	
	
portName 
	:	IDENT
	;
	
portType:	'port'
		|	'input'
		| 	'output'
        |   'eval'
	;
	
mfCall	:	mfName '(' mfparams ')'
	;
	
mfName 	:	IDENT
	;

mfparams:	expr(',' expr)* 
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
	 // put in identifier once it's time.
	 |	NUMBER 								  #numExpr
	 |  BOOLEAN_LITERAL						  #boolExpr
	 ;

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