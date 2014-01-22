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
		LSQUARE=8, RSQUARE=9, STRING_LITERAL=10, NUMBER=11, IDENT=12, WS=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'.'", "'|'", "'+'", "'-'", "'*'", "'/'", "'%'", "'['", "']'", "STRING_LITERAL", 
		"NUMBER", "IDENT", "WS"
	};
	public static final String[] ruleNames = {
		"T__0", "OR_OP", "PLUS_OP", "MINUS_OP", "MULT_OP", "DIV_OP", "MOD_OP", 
		"LSQUARE", "RSQUARE", "STRING_LITERAL", "NUMBER", "IDENT", "WS", "LCLETTER", 
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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 12: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\17f\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3"+
		"\13\7\138\n\13\f\13\16\13;\13\13\3\13\3\13\3\f\6\f@\n\f\r\f\16\fA\3\f"+
		"\3\f\6\fF\n\f\r\f\16\fG\5\fJ\n\f\3\r\3\r\3\r\5\rO\n\r\3\r\3\r\3\r\3\r"+
		"\7\rU\n\r\f\r\16\rX\13\r\3\16\6\16[\n\16\r\16\16\16\\\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\21\3\21\39\22\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17"+
		"\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\2\35\2\1\37\2\1!\2\1\3"+
		"\2\3\5\2\13\13\16\16\"\"m\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\3#\3\2\2\2\5%\3\2\2"+
		"\2\7\'\3\2\2\2\t)\3\2\2\2\13+\3\2\2\2\r-\3\2\2\2\17/\3\2\2\2\21\61\3\2"+
		"\2\2\23\63\3\2\2\2\25\65\3\2\2\2\27?\3\2\2\2\31N\3\2\2\2\33Z\3\2\2\2\35"+
		"`\3\2\2\2\37b\3\2\2\2!d\3\2\2\2#$\7\60\2\2$\4\3\2\2\2%&\7~\2\2&\6\3\2"+
		"\2\2\'(\7-\2\2(\b\3\2\2\2)*\7/\2\2*\n\3\2\2\2+,\7,\2\2,\f\3\2\2\2-.\7"+
		"\61\2\2.\16\3\2\2\2/\60\7\'\2\2\60\20\3\2\2\2\61\62\7]\2\2\62\22\3\2\2"+
		"\2\63\64\7_\2\2\64\24\3\2\2\2\659\7$\2\2\668\13\2\2\2\67\66\3\2\2\28;"+
		"\3\2\2\29:\3\2\2\29\67\3\2\2\2:<\3\2\2\2;9\3\2\2\2<=\7$\2\2=\26\3\2\2"+
		"\2>@\5!\21\2?>\3\2\2\2@A\3\2\2\2A?\3\2\2\2AB\3\2\2\2BI\3\2\2\2CE\7\60"+
		"\2\2DF\5!\21\2ED\3\2\2\2FG\3\2\2\2GE\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IC\3\2"+
		"\2\2IJ\3\2\2\2J\30\3\2\2\2KO\5\35\17\2LO\5\37\20\2MO\5!\21\2NK\3\2\2\2"+
		"NL\3\2\2\2NM\3\2\2\2OV\3\2\2\2PU\5\35\17\2QU\5\37\20\2RU\5!\21\2SU\7a"+
		"\2\2TP\3\2\2\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2"+
		"\2\2W\32\3\2\2\2XV\3\2\2\2Y[\t\2\2\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2\2\2\\"+
		"]\3\2\2\2]^\3\2\2\2^_\b\16\2\2_\34\3\2\2\2`a\4c|\2a\36\3\2\2\2bc\4C\\"+
		"\2c \3\2\2\2de\4\62;\2e\"\3\2\2\2\13\29AGINTV\\";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}