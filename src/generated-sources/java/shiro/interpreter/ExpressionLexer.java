// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Expression.g4 by ANTLR 4.1
package shiro.interpreter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, OR_OP=2, PLUS_OP=3, MINUS_OP=4, MULT_OP=5, DIV_OP=6, MOD_OP=7, 
		LSQUARE=8, RSQUARE=9, STRING_LITERAL=10, NUMBER=11, IDENT=12;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'.'", "'|'", "'+'", "'-'", "'*'", "'/'", "'%'", "'['", "']'", "STRING_LITERAL", 
		"NUMBER", "IDENT"
	};
	public static final String[] ruleNames = {
		"T__0", "OR_OP", "PLUS_OP", "MINUS_OP", "MULT_OP", "DIV_OP", "MOD_OP", 
		"LSQUARE", "RSQUARE", "STRING_LITERAL", "NUMBER", "IDENT", "LCLETTER", 
		"UCLETTER", "DIGIT"
	};


	public ExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expression.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\16]\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\7\13\66"+
		"\n\13\f\13\16\139\13\13\3\13\3\13\3\f\6\f>\n\f\r\f\16\f?\3\f\3\f\6\fD"+
		"\n\f\r\f\16\fE\5\fH\n\f\3\r\3\r\3\r\5\rM\n\r\3\r\3\r\3\r\3\r\7\rS\n\r"+
		"\f\r\16\rV\13\r\3\16\3\16\3\17\3\17\3\20\3\20\3\67\21\3\3\1\5\4\1\7\5"+
		"\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\2"+
		"\1\35\2\1\37\2\1\3\2\2c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\3!\3\2\2\2\5#\3\2\2\2\7%\3\2\2\2\t"+
		"\'\3\2\2\2\13)\3\2\2\2\r+\3\2\2\2\17-\3\2\2\2\21/\3\2\2\2\23\61\3\2\2"+
		"\2\25\63\3\2\2\2\27=\3\2\2\2\31L\3\2\2\2\33W\3\2\2\2\35Y\3\2\2\2\37[\3"+
		"\2\2\2!\"\7\60\2\2\"\4\3\2\2\2#$\7~\2\2$\6\3\2\2\2%&\7-\2\2&\b\3\2\2\2"+
		"\'(\7/\2\2(\n\3\2\2\2)*\7,\2\2*\f\3\2\2\2+,\7\61\2\2,\16\3\2\2\2-.\7\'"+
		"\2\2.\20\3\2\2\2/\60\7]\2\2\60\22\3\2\2\2\61\62\7_\2\2\62\24\3\2\2\2\63"+
		"\67\7$\2\2\64\66\13\2\2\2\65\64\3\2\2\2\669\3\2\2\2\678\3\2\2\2\67\65"+
		"\3\2\2\28:\3\2\2\29\67\3\2\2\2:;\7$\2\2;\26\3\2\2\2<>\5\37\20\2=<\3\2"+
		"\2\2>?\3\2\2\2?=\3\2\2\2?@\3\2\2\2@G\3\2\2\2AC\7\60\2\2BD\5\37\20\2CB"+
		"\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH\3\2\2\2GA\3\2\2\2GH\3\2\2\2H"+
		"\30\3\2\2\2IM\5\33\16\2JM\5\35\17\2KM\5\37\20\2LI\3\2\2\2LJ\3\2\2\2LK"+
		"\3\2\2\2MT\3\2\2\2NS\5\33\16\2OS\5\35\17\2PS\5\37\20\2QS\7a\2\2RN\3\2"+
		"\2\2RO\3\2\2\2RP\3\2\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\32\3"+
		"\2\2\2VT\3\2\2\2WX\4c|\2X\34\3\2\2\2YZ\4C\\\2Z\36\3\2\2\2[\\\4\62;\2\\"+
		" \3\2\2\2\n\2\67?EGLRT";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}