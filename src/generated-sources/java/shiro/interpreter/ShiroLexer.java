// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Shiro.g4 by ANTLR 4.1
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
public class ShiroLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__10=1, T__9=2, T__8=3, T__7=4, T__6=5, T__5=6, T__4=7, T__3=8, T__2=9, 
		T__1=10, T__0=11, PROD_OP=12, STATE=13, SUBJ_NODE=14, NODE=15, SUBJUNCT=16, 
		BEGIN=17, END=18, COMMENT=19, LINE_COMMENT=20, WS=21, NEWLINE=22, OR_OP=23, 
		PLUS_OP=24, MINUS_OP=25, MULT_OP=26, DIV_OP=27, MOD_OP=28, LSQUARE=29, 
		RSQUARE=30, STRING_LITERAL=31, NUMBER=32, IDENT=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'eval'", "')'", "'.'", "','", "'('", "'Graph'", "'Comment'", "'graph'", 
		"'Parent'", "'Time'", "'port'", "'->'", "'state'", "'subjunctive node'", 
		"'node'", "'subjunct'", "'begin'", "'end'", "COMMENT", "LINE_COMMENT", 
		"WS", "NEWLINE", "'|'", "'+'", "'-'", "'*'", "'/'", "'%'", "'['", "']'", 
		"STRING_LITERAL", "NUMBER", "IDENT"
	};
	public static final String[] ruleNames = {
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "PROD_OP", "STATE", "SUBJ_NODE", "NODE", "SUBJUNCT", "BEGIN", 
		"END", "COMMENT", "LINE_COMMENT", "WS", "NEWLINE", "OR_OP", "PLUS_OP", 
		"MINUS_OP", "MULT_OP", "DIV_OP", "MOD_OP", "LSQUARE", "RSQUARE", "STRING_LITERAL", 
		"NUMBER", "IDENT", "LCLETTER", "UCLETTER", "DIGIT"
	};


	public ShiroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Shiro.g4"; }

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
		case 18: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 19: LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 20: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: skip();  break;
		}
	}
	private void LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = HIDDEN;  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2#\u0111\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5"+
		"\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\24\7\24\u00b4\n\24\f\24\16\24\u00b7\13\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\25\7\25\u00bf\n\25\f\25\16\25\u00c2\13\25\3\25\3\25\3\25\3"+
		"\25\5\25\u00c8\n\25\3\25\3\25\3\26\6\26\u00cd\n\26\r\26\16\26\u00ce\3"+
		"\26\3\26\3\27\5\27\u00d4\n\27\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \7 \u00ea\n \f"+
		" \16 \u00ed\13 \3 \3 \3!\6!\u00f2\n!\r!\16!\u00f3\3!\3!\6!\u00f8\n!\r"+
		"!\16!\u00f9\5!\u00fc\n!\3\"\3\"\3\"\5\"\u0101\n\"\3\"\3\"\3\"\3\"\7\""+
		"\u0107\n\"\f\"\16\"\u010a\13\"\3#\3#\3$\3$\3%\3%\4\u00c0\u00eb&\3\3\1"+
		"\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31"+
		"\16\1\33\17\1\35\20\1\37\21\1!\22\1#\23\1%\24\1\'\25\2)\26\3+\27\4-\30"+
		"\1/\31\1\61\32\1\63\33\1\65\34\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1"+
		"E\2\1G\2\1I\2\1\3\2\4\4\2\f\f\17\17\5\2\13\13\16\16\"\"\u011c\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3K\3\2\2\2\5P\3\2\2\2\7R\3"+
		"\2\2\2\tT\3\2\2\2\13V\3\2\2\2\rX\3\2\2\2\17^\3\2\2\2\21f\3\2\2\2\23l\3"+
		"\2\2\2\25s\3\2\2\2\27x\3\2\2\2\31}\3\2\2\2\33\u0080\3\2\2\2\35\u0086\3"+
		"\2\2\2\37\u0097\3\2\2\2!\u009c\3\2\2\2#\u00a5\3\2\2\2%\u00ab\3\2\2\2\'"+
		"\u00af\3\2\2\2)\u00ba\3\2\2\2+\u00cc\3\2\2\2-\u00d3\3\2\2\2/\u00d7\3\2"+
		"\2\2\61\u00d9\3\2\2\2\63\u00db\3\2\2\2\65\u00dd\3\2\2\2\67\u00df\3\2\2"+
		"\29\u00e1\3\2\2\2;\u00e3\3\2\2\2=\u00e5\3\2\2\2?\u00e7\3\2\2\2A\u00f1"+
		"\3\2\2\2C\u0100\3\2\2\2E\u010b\3\2\2\2G\u010d\3\2\2\2I\u010f\3\2\2\2K"+
		"L\7g\2\2LM\7x\2\2MN\7c\2\2NO\7n\2\2O\4\3\2\2\2PQ\7+\2\2Q\6\3\2\2\2RS\7"+
		"\60\2\2S\b\3\2\2\2TU\7.\2\2U\n\3\2\2\2VW\7*\2\2W\f\3\2\2\2XY\7I\2\2YZ"+
		"\7t\2\2Z[\7c\2\2[\\\7r\2\2\\]\7j\2\2]\16\3\2\2\2^_\7E\2\2_`\7q\2\2`a\7"+
		"o\2\2ab\7o\2\2bc\7g\2\2cd\7p\2\2de\7v\2\2e\20\3\2\2\2fg\7i\2\2gh\7t\2"+
		"\2hi\7c\2\2ij\7r\2\2jk\7j\2\2k\22\3\2\2\2lm\7R\2\2mn\7c\2\2no\7t\2\2o"+
		"p\7g\2\2pq\7p\2\2qr\7v\2\2r\24\3\2\2\2st\7V\2\2tu\7k\2\2uv\7o\2\2vw\7"+
		"g\2\2w\26\3\2\2\2xy\7r\2\2yz\7q\2\2z{\7t\2\2{|\7v\2\2|\30\3\2\2\2}~\7"+
		"/\2\2~\177\7@\2\2\177\32\3\2\2\2\u0080\u0081\7u\2\2\u0081\u0082\7v\2\2"+
		"\u0082\u0083\7c\2\2\u0083\u0084\7v\2\2\u0084\u0085\7g\2\2\u0085\34\3\2"+
		"\2\2\u0086\u0087\7u\2\2\u0087\u0088\7w\2\2\u0088\u0089\7d\2\2\u0089\u008a"+
		"\7l\2\2\u008a\u008b\7w\2\2\u008b\u008c\7p\2\2\u008c\u008d\7e\2\2\u008d"+
		"\u008e\7v\2\2\u008e\u008f\7k\2\2\u008f\u0090\7x\2\2\u0090\u0091\7g\2\2"+
		"\u0091\u0092\7\"\2\2\u0092\u0093\7p\2\2\u0093\u0094\7q\2\2\u0094\u0095"+
		"\7f\2\2\u0095\u0096\7g\2\2\u0096\36\3\2\2\2\u0097\u0098\7p\2\2\u0098\u0099"+
		"\7q\2\2\u0099\u009a\7f\2\2\u009a\u009b\7g\2\2\u009b \3\2\2\2\u009c\u009d"+
		"\7u\2\2\u009d\u009e\7w\2\2\u009e\u009f\7d\2\2\u009f\u00a0\7l\2\2\u00a0"+
		"\u00a1\7w\2\2\u00a1\u00a2\7p\2\2\u00a2\u00a3\7e\2\2\u00a3\u00a4\7v\2\2"+
		"\u00a4\"\3\2\2\2\u00a5\u00a6\7d\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7i"+
		"\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7p\2\2\u00aa$\3\2\2\2\u00ab\u00ac"+
		"\7g\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7f\2\2\u00ae&\3\2\2\2\u00af\u00b0"+
		"\7\61\2\2\u00b0\u00b1\7\61\2\2\u00b1\u00b5\3\2\2\2\u00b2\u00b4\n\2\2\2"+
		"\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6"+
		"\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00b9\b\24\2\2"+
		"\u00b9(\3\2\2\2\u00ba\u00bb\7\61\2\2\u00bb\u00bc\7,\2\2\u00bc\u00c0\3"+
		"\2\2\2\u00bd\u00bf\13\2\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c3\3\2\2\2\u00c2\u00c0\3\2"+
		"\2\2\u00c3\u00c4\7,\2\2\u00c4\u00c5\7\61\2\2\u00c5\u00c7\3\2\2\2\u00c6"+
		"\u00c8\5-\27\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\u00ca\b\25\3\2\u00ca*\3\2\2\2\u00cb\u00cd\t\3\2\2\u00cc\u00cb"+
		"\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00d0\3\2\2\2\u00d0\u00d1\b\26\4\2\u00d1,\3\2\2\2\u00d2\u00d4\7\17\2"+
		"\2\u00d3\u00d2\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6"+
		"\7\f\2\2\u00d6.\3\2\2\2\u00d7\u00d8\7~\2\2\u00d8\60\3\2\2\2\u00d9\u00da"+
		"\7-\2\2\u00da\62\3\2\2\2\u00db\u00dc\7/\2\2\u00dc\64\3\2\2\2\u00dd\u00de"+
		"\7,\2\2\u00de\66\3\2\2\2\u00df\u00e0\7\61\2\2\u00e08\3\2\2\2\u00e1\u00e2"+
		"\7\'\2\2\u00e2:\3\2\2\2\u00e3\u00e4\7]\2\2\u00e4<\3\2\2\2\u00e5\u00e6"+
		"\7_\2\2\u00e6>\3\2\2\2\u00e7\u00eb\7$\2\2\u00e8\u00ea\13\2\2\2\u00e9\u00e8"+
		"\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00ec\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec"+
		"\u00ee\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00ef\7$\2\2\u00ef@\3\2\2\2\u00f0"+
		"\u00f2\5I%\2\u00f1\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2"+
		"\2\u00f3\u00f4\3\2\2\2\u00f4\u00fb\3\2\2\2\u00f5\u00f7\7\60\2\2\u00f6"+
		"\u00f8\5I%\2\u00f7\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00f7\3\2\2"+
		"\2\u00f9\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f5\3\2\2\2\u00fb\u00fc"+
		"\3\2\2\2\u00fcB\3\2\2\2\u00fd\u0101\5E#\2\u00fe\u0101\5G$\2\u00ff\u0101"+
		"\5I%\2\u0100\u00fd\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2\2\2\u0101"+
		"\u0108\3\2\2\2\u0102\u0107\5E#\2\u0103\u0107\5G$\2\u0104\u0107\5I%\2\u0105"+
		"\u0107\7a\2\2\u0106\u0102\3\2\2\2\u0106\u0103\3\2\2\2\u0106\u0104\3\2"+
		"\2\2\u0106\u0105\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109D\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u010c\4c|\2\u010c"+
		"F\3\2\2\2\u010d\u010e\4C\\\2\u010eH\3\2\2\2\u010f\u0110\4\62;\2\u0110"+
		"J\3\2\2\2\17\2\u00b5\u00c0\u00c7\u00ce\u00d3\u00eb\u00f3\u00f9\u00fb\u0100"+
		"\u0106\u0108";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}