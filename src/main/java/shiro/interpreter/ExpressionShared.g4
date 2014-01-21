grammar ExpressionShared;

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
    //    |     STRING_LITERAL                          # StringExp
    //    |	'(' expr ')'                            # BracketsExp
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

fragment LCLETTER : 'a'..'z';
fragment UCLETTER : 'A'..'Z';
fragment DIGIT : '0'..'9';