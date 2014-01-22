// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Expression.g4 by ANTLR 4.1
package shiro.interpreter;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExpressionParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, OR_OP=2, PLUS_OP=3, MINUS_OP=4, MULT_OP=5, DIV_OP=6, MOD_OP=7, 
		LSQUARE=8, RSQUARE=9, STRING_LITERAL=10, NUMBER=11, IDENT=12, WS=13;
	public static final String[] tokenNames = {
		"<INVALID>", "'.'", "'|'", "'+'", "'-'", "'*'", "'/'", "'%'", "'['", "']'", 
		"STRING_LITERAL", "NUMBER", "IDENT", "WS"
	};
	public static final int
		RULE_expression = 0, RULE_path = 1, RULE_pathIndex = 2, RULE_expr = 3;
	public static final String[] ruleNames = {
		"expression", "path", "pathIndex", "expr"
	};

	@Override
	public String getGrammarFileName() { return "Expression.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8); expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathContext extends ParserRuleContext {
		public TerminalNode RSQUARE() { return getToken(ExpressionParser.RSQUARE, 0); }
		public PathIndexContext pathIndex() {
			return getRuleContext(PathIndexContext.class,0);
		}
		public TerminalNode LSQUARE() { return getToken(ExpressionParser.LSQUARE, 0); }
		public List<TerminalNode> IDENT() { return getTokens(ExpressionParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(ExpressionParser.IDENT, i);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPath(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_path);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(10); match(IDENT);
			}
			setState(15);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(11); match(1);
					setState(12); match(IDENT);
					}
					} 
				}
				setState(17);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(22);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(18); match(LSQUARE);
				setState(19); pathIndex();
				setState(20); match(RSQUARE);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PathIndexContext extends ParserRuleContext {
		public Token index;
		public TerminalNode STRING_LITERAL() { return getToken(ExpressionParser.STRING_LITERAL, 0); }
		public TerminalNode NUMBER() { return getToken(ExpressionParser.NUMBER, 0); }
		public PathIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPathIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPathIndex(this);
		}
	}

	public final PathIndexContext pathIndex() throws RecognitionException {
		PathIndexContext _localctx = new PathIndexContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pathIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			((PathIndexContext)_localctx).index = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==STRING_LITERAL || _la==NUMBER) ) {
				((PathIndexContext)_localctx).index = (Token)_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public int _p;
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
			super(parent, invokingState);
			this._p = _p;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
			this._p = ctx._p;
		}
	}
	public static class OrExpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode OR_OP() { return getToken(ExpressionParser.OR_OP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterOrExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitOrExp(this);
		}
	}
	public static class NumberExpContext extends ExprContext {
		public TerminalNode NUMBER() { return getToken(ExpressionParser.NUMBER, 0); }
		public NumberExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterNumberExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitNumberExp(this);
		}
	}
	public static class MultExpContext extends ExprContext {
		public TerminalNode MULT_OP() { return getToken(ExpressionParser.MULT_OP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MOD_OP() { return getToken(ExpressionParser.MOD_OP, 0); }
		public TerminalNode DIV_OP() { return getToken(ExpressionParser.DIV_OP, 0); }
		public MultExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterMultExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitMultExp(this);
		}
	}
	public static class AddExpContext extends ExprContext {
		public TerminalNode PLUS_OP() { return getToken(ExpressionParser.PLUS_OP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MINUS_OP() { return getToken(ExpressionParser.MINUS_OP, 0); }
		public AddExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterAddExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitAddExp(this);
		}
	}
	public static class PathExpContext extends ExprContext {
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public PathExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).enterPathExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionListener ) ((ExpressionListener)listener).exitPathExp(this);
		}
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			switch (_input.LA(1)) {
			case IDENT:
				{
				_localctx = new PathExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(27); path();
				}
				break;
			case NUMBER:
				{
				_localctx = new NumberExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(28); match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(40);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new OrExpContext(new ExprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(31);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(32); match(OR_OP);
						setState(33); expr(6);
						}
						break;

					case 2:
						{
						_localctx = new MultExpContext(new ExprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(34);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(35);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT_OP) | (1L << DIV_OP) | (1L << MOD_OP))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(36); expr(5);
						}
						break;

					case 3:
						{
						_localctx = new AddExpContext(new ExprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(37);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(38);
						_la = _input.LA(1);
						if ( !(_la==PLUS_OP || _la==MINUS_OP) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(39); expr(4);
						}
						break;
					}
					} 
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return 5 >= _localctx._p;

		case 1: return 4 >= _localctx._p;

		case 2: return 3 >= _localctx._p;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\17\60\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\3\3\7\3\20\n\3\f\3\16\3\23\13\3\3"+
		"\3\3\3\3\3\3\3\5\3\31\n\3\3\4\3\4\3\5\3\5\3\5\5\5 \n\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\7\5+\n\5\f\5\16\5.\13\5\3\5\2\6\2\4\6\b\2\5\3\2\f"+
		"\r\3\2\7\t\3\2\5\6\61\2\n\3\2\2\2\4\f\3\2\2\2\6\32\3\2\2\2\b\37\3\2\2"+
		"\2\n\13\5\b\5\2\13\3\3\2\2\2\f\21\7\16\2\2\r\16\7\3\2\2\16\20\7\16\2\2"+
		"\17\r\3\2\2\2\20\23\3\2\2\2\21\17\3\2\2\2\21\22\3\2\2\2\22\30\3\2\2\2"+
		"\23\21\3\2\2\2\24\25\7\n\2\2\25\26\5\6\4\2\26\27\7\13\2\2\27\31\3\2\2"+
		"\2\30\24\3\2\2\2\30\31\3\2\2\2\31\5\3\2\2\2\32\33\t\2\2\2\33\7\3\2\2\2"+
		"\34\35\b\5\1\2\35 \5\4\3\2\36 \7\r\2\2\37\34\3\2\2\2\37\36\3\2\2\2 ,\3"+
		"\2\2\2!\"\6\5\2\3\"#\7\4\2\2#+\5\b\5\2$%\6\5\3\3%&\t\3\2\2&+\5\b\5\2\'"+
		"(\6\5\4\3()\t\4\2\2)+\5\b\5\2*!\3\2\2\2*$\3\2\2\2*\'\3\2\2\2+.\3\2\2\2"+
		",*\3\2\2\2,-\3\2\2\2-\t\3\2\2\2.,\3\2\2\2\7\21\30\37*,";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}