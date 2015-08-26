grammar Shiro;

shiro : includeStmt*
        shiroStmt*
        EOF
	  ;

includeStmt: INCLUDE STRING_LITERAL NEWLINE;

shiroStmt
    :
        anonymousGraphStmt
        | nodeDecl
        | graphDecl
        | stateDecl
        | NEWLINE
	;

stateDecl
    :   STATE stateName BEGIN NEWLINE
        stateStmt*
        END
    ;

stateName
    :   IDENT
    ;

stateStmt
    :   stateGraphSelection
    |   stateActivation
    |   nestedStateActivation
    |   NEWLINE
    ;

stateGraphSelection
    :   GRAPH (IDENT | DEFAULT)
    ;

nestedStateActivation
    :   stateActivation BEGIN NEWLINE
        (stateActivation
        | nestedStateActivation
        | NEWLINE)*
        END
    ;

stateActivation
    :   nodeName=IDENT ( LSQUARE activeObject=IDENT RSQUARE )?
    ;

graphDecl
	:	GRAPH IDENT BEGIN NEWLINE
		graphStmt+
		END
	;

graphStmt
	:	portAssignment | funcDeclInit | funcDecl | NEWLINE
	;

nodeDecl
    :   NODE MFNAME ('[' optionSelector ']')? BEGIN NEWLINE
        nodeStmt
        END
    ;

funcDeclInit
    :   nodeName=IDENT fullyQualifiedType ( LSQUARE activeObject=IDENT RSQUARE )? ('(' arguments ')')?
    ;

funcCall : fullyQualifiedType ( LSQUARE activeObject=IDENT RSQUARE )? ('(' arguments ')')?;

funcDecl
    :   nodeName=IDENT fullyQualifiedType
    ;

arguments
    :   argMap | argList
    ;

argMap
    :   (keys+=IDENT ':' values+=arg)(',' keys+=IDENT ':' values+=arg)*
    ;

argList
    :	arg(',' arg)*
    ;

arg: expr;

optionSelector
	:	IDENT
	;

nodeStmt
    :   (portstmt
        | portAssignment
        | nodeDecl
        | NEWLINE)*
    ;

portDecl
	:	OPTION? accessModifier? funcDecl
	;
	
portDeclInit
	:	OPTION? accessModifier? funcDeclInit
	;

portstmt	
	:	( portDeclInit | portDecl ) NEWLINE
	;	
	
portName 
	:	IDENT
	;
	
accessModifier
    :   INPUT | OUTPUT
	;

fullyQualifiedType
    :   types+=MFNAME ('.' types+=MFNAME)*
    ;

path
    :   (REF| SELECT)? segments+=pathSegment ('.' segments+=pathSegment)*
    ;

pathSegment
    :    IDENT
    |    (INPUTS| OUTPUTS) LSQUARE pathIndex RSQUARE
    ;

pathIndex
	:	index=(NUMBER | IDENT)
	;

portAssignment
    :	path '(' arguments ')' NEWLINE
    ;

anonExpr
    : expr NEWLINE
    ;

anonymousGraphStmt
    :   portAssignment
    |   funcDeclInit NEWLINE
    |   funcDecl NEWLINE
    |   anonExpr
    ;

expr : '(' expr ')'						      #parensExpr
	 |	NOT_OP expr 				          #notExpr
	 |  MINUS_OP expr      					  #negExpr
	 |  expr AND_OP expr  		  			  #andExpr
	 |  expr OR_OP expr 					  #orExpr
	 |	expr (DIV_OP | MULT_OP | MOD_OP) expr #multExpr
	 |  expr (PLUS_OP | MINUS_OP ) expr       #addExpr
	 |  expr (GT | GTE | LT | LTE) expr       #comparisonExpr
	 |  expr ( EQ | NEQ ) expr                #equalityExpr
	 |  funcCall                              #inlineFuncCall
	 |  fullyQualifiedType                    #typeExpr
	 |  path                                  #pathExpr
	 |	NUMBER 								  #numExpr
	 |  BOOLEAN_LITERAL						  #boolExpr
	 |  STRING_LITERAL                        #stringExpr
	 ;

SELECT   : '@';
REF      : '~';
DEFAULT  : '^';
PORT     : 'port';
INPUT    : 'input';
OUTPUT   : 'output';
EVAL     : 'eval';
THIS     : 'this';
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

INCLUDE: 'include';
STATE: 'state';
NODE: 'node';
GRAPH: 'graph';
BEGIN: 'begin';
END: 'end';
INPUTS: 'inputs';
OUTPUTS: 'outputs';
OPTION : 'option';

BOOLEAN_LITERAL
    : 'true' | 'false'
    ;

STRING_LITERAL : '"' (~'"'|'\\"')* '"'  ;

NUMBER 	
    :	DIGIT+ ('.'DIGIT+)?
    ;

IDENT
    : LCLETTER (LCLETTER | UCLETTER | DIGIT|'_')*
    ;

MFNAME
    : UCLETTER (LCLETTER | UCLETTER | DIGIT|'_')*
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
