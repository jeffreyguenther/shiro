// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Shiro.g4 by ANTLR 4.2
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
		BEGIN=17, END=18, OR_OP=19, PLUS_OP=20, MINUS_OP=21, MULT_OP=22, DIV_OP=23, 
		MOD_OP=24, LSQUARE=25, RSQUARE=26, STRING_LITERAL=27, NUMBER=28, IDENT=29, 
		WS=30, COMMENT=31, LINE_COMMENT=32, NEWLINE=33;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'eval'", "'port'", "'Comment'", "'Graph'", "'Time'", "'graph'", "'Parent'", 
		"'('", "')'", "','", "'.'", "PROD_OP", "'state'", "'subjunctive node'", 
		"'node'", "'subjunct'", "'begin'", "'end'", "'|'", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'['", "']'", "STRING_LITERAL", "NUMBER", "IDENT", "WS", 
		"COMMENT", "LINE_COMMENT", "NEWLINE"
	};
	public static final String[] ruleNames = {
		"T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", 
		"T__1", "T__0", "PROD_OP", "STATE", "SUBJ_NODE", "NODE", "SUBJUNCT", "BEGIN", 
		"END", "OR_OP", "PLUS_OP", "MINUS_OP", "MULT_OP", "DIV_OP", "MOD_OP", 
		"LSQUARE", "RSQUARE", "STRING_LITERAL", "NUMBER", "IDENT", "WS", "COMMENT", 
		"LINE_COMMENT", "NEWLINE", "LCLETTER", "UCLETTER", "DIGIT"
	};


	public static final int WHITESPACE = 1;
	public static final int COMMENTS = 2;


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
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 29: WS_action((RuleContext)_localctx, actionIndex); break;

		case 30: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 31: LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: _channel = WHITESPACE; break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = WHITESPACE; break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: _channel = COMMENTS; break;
		}
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2#\u0111\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33"+
		"\3\34\3\34\7\34\u00c2\n\34\f\34\16\34\u00c5\13\34\3\34\3\34\3\35\6\35"+
		"\u00ca\n\35\r\35\16\35\u00cb\3\35\3\35\6\35\u00d0\n\35\r\35\16\35\u00d1"+
		"\5\35\u00d4\n\35\3\36\3\36\3\36\5\36\u00d9\n\36\3\36\3\36\3\36\3\36\7"+
		"\36\u00df\n\36\f\36\16\36\u00e2\13\36\3\37\6\37\u00e5\n\37\r\37\16\37"+
		"\u00e6\3\37\3\37\3 \3 \3 \3 \7 \u00ef\n \f \16 \u00f2\13 \3 \3 \3!\3!"+
		"\3!\3!\7!\u00fa\n!\f!\16!\u00fd\13!\3!\3!\3!\3!\5!\u0103\n!\3!\3!\3\""+
		"\5\"\u0108\n\"\3\"\3\"\3#\3#\3$\3$\3%\3%\4\u00c3\u00fb\2&\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E\2"+
		"G\2I\2\3\2\4\5\2\13\13\16\16\"\"\4\2\f\f\17\17\u011c\2\3\3\2\2\2\2\5\3"+
		"\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2"+
		"\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3"+
		"\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'"+
		"\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2"+
		"?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3K\3\2\2\2\5P\3\2\2\2\7U\3\2\2\2\t]\3"+
		"\2\2\2\13c\3\2\2\2\rh\3\2\2\2\17n\3\2\2\2\21u\3\2\2\2\23w\3\2\2\2\25y"+
		"\3\2\2\2\27{\3\2\2\2\31}\3\2\2\2\33\u0080\3\2\2\2\35\u0086\3\2\2\2\37"+
		"\u0097\3\2\2\2!\u009c\3\2\2\2#\u00a5\3\2\2\2%\u00ab\3\2\2\2\'\u00af\3"+
		"\2\2\2)\u00b1\3\2\2\2+\u00b3\3\2\2\2-\u00b5\3\2\2\2/\u00b7\3\2\2\2\61"+
		"\u00b9\3\2\2\2\63\u00bb\3\2\2\2\65\u00bd\3\2\2\2\67\u00bf\3\2\2\29\u00c9"+
		"\3\2\2\2;\u00d8\3\2\2\2=\u00e4\3\2\2\2?\u00ea\3\2\2\2A\u00f5\3\2\2\2C"+
		"\u0107\3\2\2\2E\u010b\3\2\2\2G\u010d\3\2\2\2I\u010f\3\2\2\2KL\7g\2\2L"+
		"M\7x\2\2MN\7c\2\2NO\7n\2\2O\4\3\2\2\2PQ\7r\2\2QR\7q\2\2RS\7t\2\2ST\7v"+
		"\2\2T\6\3\2\2\2UV\7E\2\2VW\7q\2\2WX\7o\2\2XY\7o\2\2YZ\7g\2\2Z[\7p\2\2"+
		"[\\\7v\2\2\\\b\3\2\2\2]^\7I\2\2^_\7t\2\2_`\7c\2\2`a\7r\2\2ab\7j\2\2b\n"+
		"\3\2\2\2cd\7V\2\2de\7k\2\2ef\7o\2\2fg\7g\2\2g\f\3\2\2\2hi\7i\2\2ij\7t"+
		"\2\2jk\7c\2\2kl\7r\2\2lm\7j\2\2m\16\3\2\2\2no\7R\2\2op\7c\2\2pq\7t\2\2"+
		"qr\7g\2\2rs\7p\2\2st\7v\2\2t\20\3\2\2\2uv\7*\2\2v\22\3\2\2\2wx\7+\2\2"+
		"x\24\3\2\2\2yz\7.\2\2z\26\3\2\2\2{|\7\60\2\2|\30\3\2\2\2}~\7/\2\2~\177"+
		"\7@\2\2\177\32\3\2\2\2\u0080\u0081\7u\2\2\u0081\u0082\7v\2\2\u0082\u0083"+
		"\7c\2\2\u0083\u0084\7v\2\2\u0084\u0085\7g\2\2\u0085\34\3\2\2\2\u0086\u0087"+
		"\7u\2\2\u0087\u0088\7w\2\2\u0088\u0089\7d\2\2\u0089\u008a\7l\2\2\u008a"+
		"\u008b\7w\2\2\u008b\u008c\7p\2\2\u008c\u008d\7e\2\2\u008d\u008e\7v\2\2"+
		"\u008e\u008f\7k\2\2\u008f\u0090\7x\2\2\u0090\u0091\7g\2\2\u0091\u0092"+
		"\7\"\2\2\u0092\u0093\7p\2\2\u0093\u0094\7q\2\2\u0094\u0095\7f\2\2\u0095"+
		"\u0096\7g\2\2\u0096\36\3\2\2\2\u0097\u0098\7p\2\2\u0098\u0099\7q\2\2\u0099"+
		"\u009a\7f\2\2\u009a\u009b\7g\2\2\u009b \3\2\2\2\u009c\u009d\7u\2\2\u009d"+
		"\u009e\7w\2\2\u009e\u009f\7d\2\2\u009f\u00a0\7l\2\2\u00a0\u00a1\7w\2\2"+
		"\u00a1\u00a2\7p\2\2\u00a2\u00a3\7e\2\2\u00a3\u00a4\7v\2\2\u00a4\"\3\2"+
		"\2\2\u00a5\u00a6\7d\2\2\u00a6\u00a7\7g\2\2\u00a7\u00a8\7i\2\2\u00a8\u00a9"+
		"\7k\2\2\u00a9\u00aa\7p\2\2\u00aa$\3\2\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad"+
		"\7p\2\2\u00ad\u00ae\7f\2\2\u00ae&\3\2\2\2\u00af\u00b0\7~\2\2\u00b0(\3"+
		"\2\2\2\u00b1\u00b2\7-\2\2\u00b2*\3\2\2\2\u00b3\u00b4\7/\2\2\u00b4,\3\2"+
		"\2\2\u00b5\u00b6\7,\2\2\u00b6.\3\2\2\2\u00b7\u00b8\7\61\2\2\u00b8\60\3"+
		"\2\2\2\u00b9\u00ba\7\'\2\2\u00ba\62\3\2\2\2\u00bb\u00bc\7]\2\2\u00bc\64"+
		"\3\2\2\2\u00bd\u00be\7_\2\2\u00be\66\3\2\2\2\u00bf\u00c3\7$\2\2\u00c0"+
		"\u00c2\13\2\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c4\3"+
		"\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6"+
		"\u00c7\7$\2\2\u00c78\3\2\2\2\u00c8\u00ca\5I%\2\u00c9\u00c8\3\2\2\2\u00ca"+
		"\u00cb\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00d3\3\2"+
		"\2\2\u00cd\u00cf\7\60\2\2\u00ce\u00d0\5I%\2\u00cf\u00ce\3\2\2\2\u00d0"+
		"\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2"+
		"\2\2\u00d3\u00cd\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4:\3\2\2\2\u00d5\u00d9"+
		"\5E#\2\u00d6\u00d9\5G$\2\u00d7\u00d9\5I%\2\u00d8\u00d5\3\2\2\2\u00d8\u00d6"+
		"\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9\u00e0\3\2\2\2\u00da\u00df\5E#\2\u00db"+
		"\u00df\5G$\2\u00dc\u00df\5I%\2\u00dd\u00df\7a\2\2\u00de\u00da\3\2\2\2"+
		"\u00de\u00db\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df\u00e2"+
		"\3\2\2\2\u00e0\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1<\3\2\2\2\u00e2"+
		"\u00e0\3\2\2\2\u00e3\u00e5\t\2\2\2\u00e4\u00e3\3\2\2\2\u00e5\u00e6\3\2"+
		"\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8"+
		"\u00e9\b\37\2\2\u00e9>\3\2\2\2\u00ea\u00eb\7\61\2\2\u00eb\u00ec\7\61\2"+
		"\2\u00ec\u00f0\3\2\2\2\u00ed\u00ef\n\3\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2"+
		"\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f3\u00f4\b \3\2\u00f4@\3\2\2\2\u00f5\u00f6\7\61\2\2"+
		"\u00f6\u00f7\7,\2\2\u00f7\u00fb\3\2\2\2\u00f8\u00fa\13\2\2\2\u00f9\u00f8"+
		"\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc"+
		"\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\7,\2\2\u00ff\u0100\7\61"+
		"\2\2\u0100\u0102\3\2\2\2\u0101\u0103\5C\"\2\u0102\u0101\3\2\2\2\u0102"+
		"\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\b!\4\2\u0105B\3\2\2\2\u0106"+
		"\u0108\7\17\2\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109\3"+
		"\2\2\2\u0109\u010a\7\f\2\2\u010aD\3\2\2\2\u010b\u010c\4c|\2\u010cF\3\2"+
		"\2\2\u010d\u010e\4C\\\2\u010eH\3\2\2\2\u010f\u0110\4\62;\2\u0110J\3\2"+
		"\2\2\17\2\u00c3\u00cb\u00d1\u00d3\u00d8\u00de\u00e0\u00e6\u00f0\u00fb"+
		"\u0102\u0107\5\3\37\2\3 \3\3!\4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}