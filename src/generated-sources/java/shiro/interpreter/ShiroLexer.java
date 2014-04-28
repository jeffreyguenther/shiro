// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Shiro.g4 by ANTLR 4.2.2
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
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		USE=10, THIS=11, OPTION=12, PROD_OP=13, REFINES_OP=14, STATE=15, SUBJ_NODE=16, 
		NODE=17, SUBJUNCT=18, BEGIN=19, END=20, OR_OP=21, PLUS_OP=22, MINUS_OP=23, 
		MULT_OP=24, DIV_OP=25, MOD_OP=26, LSQUARE=27, RSQUARE=28, STRING_LITERAL=29, 
		NUMBER=30, IDENT=31, WS=32, COMMENT=33, LINE_COMMENT=34, NEWLINE=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'Graph'", "'graph'", "'eval'", "'input'", "'('", "')'", "','", "'.'", 
		"'output'", "USE", "'this'", "'option'", "'->'", "'<-'", "'state'", "'subjunctive node'", 
		"'node'", "'subjunct'", "'begin'", "'end'", "'|'", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'['", "']'", "STRING_LITERAL", "NUMBER", "IDENT", "WS", 
		"COMMENT", "LINE_COMMENT", "NEWLINE"
	};
	public static final String[] ruleNames = {
		"T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", 
		"USE", "THIS", "OPTION", "PROD_OP", "REFINES_OP", "STATE", "SUBJ_NODE", 
		"NODE", "SUBJUNCT", "BEGIN", "END", "OR_OP", "PLUS_OP", "MINUS_OP", "MULT_OP", 
		"DIV_OP", "MOD_OP", "LSQUARE", "RSQUARE", "STRING_LITERAL", "NUMBER", 
		"IDENT", "WS", "COMMENT", "LINE_COMMENT", "NEWLINE", "LCLETTER", "UCLETTER", 
		"DIGIT"
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
		case 31: WS_action((RuleContext)_localctx, actionIndex); break;

		case 32: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 33: LINE_COMMENT_action((RuleContext)_localctx, actionIndex); break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u011c\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\7\36\u00cd"+
		"\n\36\f\36\16\36\u00d0\13\36\3\36\3\36\3\37\6\37\u00d5\n\37\r\37\16\37"+
		"\u00d6\3\37\3\37\6\37\u00db\n\37\r\37\16\37\u00dc\5\37\u00df\n\37\3 \3"+
		" \3 \5 \u00e4\n \3 \3 \3 \3 \7 \u00ea\n \f \16 \u00ed\13 \3!\6!\u00f0"+
		"\n!\r!\16!\u00f1\3!\3!\3\"\3\"\3\"\3\"\7\"\u00fa\n\"\f\"\16\"\u00fd\13"+
		"\"\3\"\3\"\3#\3#\3#\3#\7#\u0105\n#\f#\16#\u0108\13#\3#\3#\3#\3#\5#\u010e"+
		"\n#\3#\3#\3$\5$\u0113\n$\3$\3$\3%\3%\3&\3&\3\'\3\'\4\u00ce\u0106\2(\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37="+
		" ?!A\"C#E$G%I\2K\2M\2\3\2\4\5\2\13\13\16\16\"\"\4\2\f\f\17\17\u0127\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2"+
		"\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2"+
		"\3O\3\2\2\2\5U\3\2\2\2\7[\3\2\2\2\t`\3\2\2\2\13f\3\2\2\2\rh\3\2\2\2\17"+
		"j\3\2\2\2\21l\3\2\2\2\23n\3\2\2\2\25u\3\2\2\2\27y\3\2\2\2\31~\3\2\2\2"+
		"\33\u0085\3\2\2\2\35\u0088\3\2\2\2\37\u008b\3\2\2\2!\u0091\3\2\2\2#\u00a2"+
		"\3\2\2\2%\u00a7\3\2\2\2\'\u00b0\3\2\2\2)\u00b6\3\2\2\2+\u00ba\3\2\2\2"+
		"-\u00bc\3\2\2\2/\u00be\3\2\2\2\61\u00c0\3\2\2\2\63\u00c2\3\2\2\2\65\u00c4"+
		"\3\2\2\2\67\u00c6\3\2\2\29\u00c8\3\2\2\2;\u00ca\3\2\2\2=\u00d4\3\2\2\2"+
		"?\u00e3\3\2\2\2A\u00ef\3\2\2\2C\u00f5\3\2\2\2E\u0100\3\2\2\2G\u0112\3"+
		"\2\2\2I\u0116\3\2\2\2K\u0118\3\2\2\2M\u011a\3\2\2\2OP\7I\2\2PQ\7t\2\2"+
		"QR\7c\2\2RS\7r\2\2ST\7j\2\2T\4\3\2\2\2UV\7i\2\2VW\7t\2\2WX\7c\2\2XY\7"+
		"r\2\2YZ\7j\2\2Z\6\3\2\2\2[\\\7g\2\2\\]\7x\2\2]^\7c\2\2^_\7n\2\2_\b\3\2"+
		"\2\2`a\7k\2\2ab\7p\2\2bc\7r\2\2cd\7w\2\2de\7v\2\2e\n\3\2\2\2fg\7*\2\2"+
		"g\f\3\2\2\2hi\7+\2\2i\16\3\2\2\2jk\7.\2\2k\20\3\2\2\2lm\7\60\2\2m\22\3"+
		"\2\2\2no\7q\2\2op\7w\2\2pq\7v\2\2qr\7r\2\2rs\7w\2\2st\7v\2\2t\24\3\2\2"+
		"\2uv\7w\2\2vw\7u\2\2wx\7g\2\2x\26\3\2\2\2yz\7v\2\2z{\7j\2\2{|\7k\2\2|"+
		"}\7u\2\2}\30\3\2\2\2~\177\7q\2\2\177\u0080\7r\2\2\u0080\u0081\7v\2\2\u0081"+
		"\u0082\7k\2\2\u0082\u0083\7q\2\2\u0083\u0084\7p\2\2\u0084\32\3\2\2\2\u0085"+
		"\u0086\7/\2\2\u0086\u0087\7@\2\2\u0087\34\3\2\2\2\u0088\u0089\7>\2\2\u0089"+
		"\u008a\7/\2\2\u008a\36\3\2\2\2\u008b\u008c\7u\2\2\u008c\u008d\7v\2\2\u008d"+
		"\u008e\7c\2\2\u008e\u008f\7v\2\2\u008f\u0090\7g\2\2\u0090 \3\2\2\2\u0091"+
		"\u0092\7u\2\2\u0092\u0093\7w\2\2\u0093\u0094\7d\2\2\u0094\u0095\7l\2\2"+
		"\u0095\u0096\7w\2\2\u0096\u0097\7p\2\2\u0097\u0098\7e\2\2\u0098\u0099"+
		"\7v\2\2\u0099\u009a\7k\2\2\u009a\u009b\7x\2\2\u009b\u009c\7g\2\2\u009c"+
		"\u009d\7\"\2\2\u009d\u009e\7p\2\2\u009e\u009f\7q\2\2\u009f\u00a0\7f\2"+
		"\2\u00a0\u00a1\7g\2\2\u00a1\"\3\2\2\2\u00a2\u00a3\7p\2\2\u00a3\u00a4\7"+
		"q\2\2\u00a4\u00a5\7f\2\2\u00a5\u00a6\7g\2\2\u00a6$\3\2\2\2\u00a7\u00a8"+
		"\7u\2\2\u00a8\u00a9\7w\2\2\u00a9\u00aa\7d\2\2\u00aa\u00ab\7l\2\2\u00ab"+
		"\u00ac\7w\2\2\u00ac\u00ad\7p\2\2\u00ad\u00ae\7e\2\2\u00ae\u00af\7v\2\2"+
		"\u00af&\3\2\2\2\u00b0\u00b1\7d\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7i\2"+
		"\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7p\2\2\u00b5(\3\2\2\2\u00b6\u00b7\7"+
		"g\2\2\u00b7\u00b8\7p\2\2\u00b8\u00b9\7f\2\2\u00b9*\3\2\2\2\u00ba\u00bb"+
		"\7~\2\2\u00bb,\3\2\2\2\u00bc\u00bd\7-\2\2\u00bd.\3\2\2\2\u00be\u00bf\7"+
		"/\2\2\u00bf\60\3\2\2\2\u00c0\u00c1\7,\2\2\u00c1\62\3\2\2\2\u00c2\u00c3"+
		"\7\61\2\2\u00c3\64\3\2\2\2\u00c4\u00c5\7\'\2\2\u00c5\66\3\2\2\2\u00c6"+
		"\u00c7\7]\2\2\u00c78\3\2\2\2\u00c8\u00c9\7_\2\2\u00c9:\3\2\2\2\u00ca\u00ce"+
		"\7$\2\2\u00cb\u00cd\13\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2"+
		"\2\2\u00d1\u00d2\7$\2\2\u00d2<\3\2\2\2\u00d3\u00d5\5M\'\2\u00d4\u00d3"+
		"\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00de\3\2\2\2\u00d8\u00da\7\60\2\2\u00d9\u00db\5M\'\2\u00da\u00d9\3\2"+
		"\2\2\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00df\3\2\2\2\u00de\u00d8\3\2\2\2\u00de\u00df\3\2\2\2\u00df>\3\2\2\2"+
		"\u00e0\u00e4\5I%\2\u00e1\u00e4\5K&\2\u00e2\u00e4\5M\'\2\u00e3\u00e0\3"+
		"\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00eb\3\2\2\2\u00e5"+
		"\u00ea\5I%\2\u00e6\u00ea\5K&\2\u00e7\u00ea\5M\'\2\u00e8\u00ea\7a\2\2\u00e9"+
		"\u00e5\3\2\2\2\u00e9\u00e6\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00e8\3\2"+
		"\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"@\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee\u00f0\t\2\2\2\u00ef\u00ee\3\2\2\2"+
		"\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3"+
		"\3\2\2\2\u00f3\u00f4\b!\2\2\u00f4B\3\2\2\2\u00f5\u00f6\7\61\2\2\u00f6"+
		"\u00f7\7\61\2\2\u00f7\u00fb\3\2\2\2\u00f8\u00fa\n\3\2\2\u00f9\u00f8\3"+
		"\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\b\"\3\2\u00ffD\3\2\2\2"+
		"\u0100\u0101\7\61\2\2\u0101\u0102\7,\2\2\u0102\u0106\3\2\2\2\u0103\u0105"+
		"\13\2\2\2\u0104\u0103\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0107\3\2\2\2"+
		"\u0106\u0104\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u010a"+
		"\7,\2\2\u010a\u010b\7\61\2\2\u010b\u010d\3\2\2\2\u010c\u010e\5G$\2\u010d"+
		"\u010c\3\2\2\2\u010d\u010e\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\b#"+
		"\4\2\u0110F\3\2\2\2\u0111\u0113\7\17\2\2\u0112\u0111\3\2\2\2\u0112\u0113"+
		"\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\7\f\2\2\u0115H\3\2\2\2\u0116"+
		"\u0117\4c|\2\u0117J\3\2\2\2\u0118\u0119\4C\\\2\u0119L\3\2\2\2\u011a\u011b"+
		"\4\62;\2\u011bN\3\2\2\2\17\2\u00ce\u00d6\u00dc\u00de\u00e3\u00e9\u00eb"+
		"\u00f1\u00fb\u0106\u010d\u0112\5\3!\2\3\"\3\3#\4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}