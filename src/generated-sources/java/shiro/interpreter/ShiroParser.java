// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Shiro.g4 by ANTLR 4.1
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
public class ShiroParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__10=1, T__9=2, T__8=3, T__7=4, T__6=5, T__5=6, T__4=7, T__3=8, T__2=9, 
		T__1=10, T__0=11, PROD_OP=12, STATE=13, SUBJ_NODE=14, NODE=15, SUBJUNCT=16, 
		BEGIN=17, END=18, COMMENT=19, LINE_COMMENT=20, WS=21, NEWLINE=22, OR_OP=23, 
		PLUS_OP=24, MINUS_OP=25, MULT_OP=26, DIV_OP=27, MOD_OP=28, LSQUARE=29, 
		RSQUARE=30, STRING_LITERAL=31, NUMBER=32, IDENT=33;
	public static final String[] tokenNames = {
		"<INVALID>", "'eval'", "')'", "'.'", "','", "'('", "'Graph'", "'Comment'", 
		"'graph'", "'Parent'", "'Time'", "'port'", "'->'", "'state'", "'subjunctive node'", 
		"'node'", "'subjunct'", "'begin'", "'end'", "COMMENT", "LINE_COMMENT", 
		"WS", "NEWLINE", "'|'", "'+'", "'-'", "'*'", "'/'", "'%'", "'['", "']'", 
		"STRING_LITERAL", "NUMBER", "IDENT"
	};
	public static final int
		RULE_shiro = 0, RULE_statement = 1, RULE_statestmt = 2, RULE_stateHeader = 3, 
		RULE_stateTimeStmt = 4, RULE_stateCommentStmt = 5, RULE_stateParentStmt = 6, 
		RULE_stateGraphStmt = 7, RULE_stateName = 8, RULE_time = 9, RULE_comment = 10, 
		RULE_stateParent = 11, RULE_stateGraph = 12, RULE_nodestmt = 13, RULE_nodeType = 14, 
		RULE_nodeInternal = 15, RULE_sNode = 16, RULE_subjunctDeclNodeProd = 17, 
		RULE_subjunctDecl = 18, RULE_subjunctSelector = 19, RULE_graphDecl = 20, 
		RULE_graphLine = 21, RULE_activeSelector = 22, RULE_nodeProduction = 23, 
		RULE_activation = 24, RULE_portAssignment = 25, RULE_portDecl = 26, RULE_portDeclInit = 27, 
		RULE_portstmt = 28, RULE_portName = 29, RULE_portType = 30, RULE_mfCall = 31, 
		RULE_mfName = 32, RULE_mfparams = 33, RULE_path = 34, RULE_pathIndex = 35, 
		RULE_expr = 36;
	public static final String[] ruleNames = {
		"shiro", "statement", "statestmt", "stateHeader", "stateTimeStmt", "stateCommentStmt", 
		"stateParentStmt", "stateGraphStmt", "stateName", "time", "comment", "stateParent", 
		"stateGraph", "nodestmt", "nodeType", "nodeInternal", "sNode", "subjunctDeclNodeProd", 
		"subjunctDecl", "subjunctSelector", "graphDecl", "graphLine", "activeSelector", 
		"nodeProduction", "activation", "portAssignment", "portDecl", "portDeclInit", 
		"portstmt", "portName", "portType", "mfCall", "mfName", "mfparams", "path", 
		"pathIndex", "expr"
	};

	@Override
	public String getGrammarFileName() { return "Shiro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public ShiroParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ShiroContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ShiroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterShiro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitShiro(this);
		}
	}

	public final ShiroContext shiro() throws RecognitionException {
		ShiroContext _localctx = new ShiroContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_shiro);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74); statement();
				}
				}
				setState(77); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 8) | (1L << STATE) | (1L << SUBJ_NODE) | (1L << NODE) | (1L << SUBJUNCT) | (1L << NEWLINE))) != 0) );
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

	public static class StatementContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public NodestmtContext nodestmt() {
			return getRuleContext(NodestmtContext.class,0);
		}
		public StatestmtContext statestmt() {
			return getRuleContext(StatestmtContext.class,0);
		}
		public SNodeContext sNode() {
			return getRuleContext(SNodeContext.class,0);
		}
		public GraphDeclContext graphDecl() {
			return getRuleContext(GraphDeclContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(84);
			switch (_input.LA(1)) {
			case NODE:
			case SUBJUNCT:
				enterOuterAlt(_localctx, 1);
				{
				setState(79); nodestmt();
				}
				break;
			case STATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(80); statestmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 3);
				{
				setState(81); graphDecl();
				}
				break;
			case SUBJ_NODE:
				enterOuterAlt(_localctx, 4);
				{
				setState(82); sNode();
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 5);
				{
				setState(83); match(NEWLINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class StatestmtContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public StateHeaderContext stateHeader() {
			return getRuleContext(StateHeaderContext.class,0);
		}
		public StateNameContext stateName() {
			return getRuleContext(StateNameContext.class,0);
		}
		public TerminalNode STATE() { return getToken(ShiroParser.STATE, 0); }
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public StatestmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statestmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStatestmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStatestmt(this);
		}
	}

	public final StatestmtContext statestmt() throws RecognitionException {
		StatestmtContext _localctx = new StatestmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statestmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); match(STATE);
			setState(87); stateName();
			setState(88); match(BEGIN);
			setState(89); match(NEWLINE);
			setState(90); stateHeader();
			setState(91); match(END);
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

	public static class StateHeaderContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public StateCommentStmtContext stateCommentStmt(int i) {
			return getRuleContext(StateCommentStmtContext.class,i);
		}
		public ActivationContext activation(int i) {
			return getRuleContext(ActivationContext.class,i);
		}
		public List<StateCommentStmtContext> stateCommentStmt() {
			return getRuleContexts(StateCommentStmtContext.class);
		}
		public List<StateParentStmtContext> stateParentStmt() {
			return getRuleContexts(StateParentStmtContext.class);
		}
		public StateGraphStmtContext stateGraphStmt(int i) {
			return getRuleContext(StateGraphStmtContext.class,i);
		}
		public List<StateGraphStmtContext> stateGraphStmt() {
			return getRuleContexts(StateGraphStmtContext.class);
		}
		public List<StateTimeStmtContext> stateTimeStmt() {
			return getRuleContexts(StateTimeStmtContext.class);
		}
		public StateParentStmtContext stateParentStmt(int i) {
			return getRuleContext(StateParentStmtContext.class,i);
		}
		public List<ActivationContext> activation() {
			return getRuleContexts(ActivationContext.class);
		}
		public StateTimeStmtContext stateTimeStmt(int i) {
			return getRuleContext(StateTimeStmtContext.class,i);
		}
		public StateHeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateHeader; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStateHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStateHeader(this);
		}
	}

	public final StateHeaderContext stateHeader() throws RecognitionException {
		StateHeaderContext _localctx = new StateHeaderContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stateHeader);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(99);
				switch (_input.LA(1)) {
				case 10:
					{
					setState(93); stateTimeStmt();
					}
					break;
				case 7:
					{
					setState(94); stateCommentStmt();
					}
					break;
				case 9:
					{
					setState(95); stateParentStmt();
					}
					break;
				case 6:
					{
					setState(96); stateGraphStmt();
					}
					break;
				case IDENT:
					{
					setState(97); activation();
					}
					break;
				case NEWLINE:
					{
					setState(98); match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 9) | (1L << 10) | (1L << NEWLINE) | (1L << IDENT))) != 0) );
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

	public static class StateTimeStmtContext extends ParserRuleContext {
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public StateTimeStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateTimeStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStateTimeStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStateTimeStmt(this);
		}
	}

	public final StateTimeStmtContext stateTimeStmt() throws RecognitionException {
		StateTimeStmtContext _localctx = new StateTimeStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_stateTimeStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(10);
			setState(104); time();
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

	public static class StateCommentStmtContext extends ParserRuleContext {
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public StateCommentStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateCommentStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStateCommentStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStateCommentStmt(this);
		}
	}

	public final StateCommentStmtContext stateCommentStmt() throws RecognitionException {
		StateCommentStmtContext _localctx = new StateCommentStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stateCommentStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106); match(7);
			setState(107); comment();
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

	public static class StateParentStmtContext extends ParserRuleContext {
		public StateParentContext stateParent() {
			return getRuleContext(StateParentContext.class,0);
		}
		public StateParentStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateParentStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStateParentStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStateParentStmt(this);
		}
	}

	public final StateParentStmtContext stateParentStmt() throws RecognitionException {
		StateParentStmtContext _localctx = new StateParentStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_stateParentStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109); match(9);
			setState(110); stateParent();
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

	public static class StateGraphStmtContext extends ParserRuleContext {
		public StateGraphContext stateGraph() {
			return getRuleContext(StateGraphContext.class,0);
		}
		public StateGraphStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateGraphStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStateGraphStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStateGraphStmt(this);
		}
	}

	public final StateGraphStmtContext stateGraphStmt() throws RecognitionException {
		StateGraphStmtContext _localctx = new StateGraphStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_stateGraphStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); match(6);
			setState(113); stateGraph();
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

	public static class StateNameContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public StateNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStateName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStateName(this);
		}
	}

	public final StateNameContext stateName() throws RecognitionException {
		StateNameContext _localctx = new StateNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); match(IDENT);
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

	public static class TimeContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(ShiroParser.STRING_LITERAL, 0); }
		public TimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitTime(this);
		}
	}

	public final TimeContext time() throws RecognitionException {
		TimeContext _localctx = new TimeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_time);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117); match(STRING_LITERAL);
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

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(ShiroParser.STRING_LITERAL, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); match(STRING_LITERAL);
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

	public static class StateParentContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public StateParentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateParent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStateParent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStateParent(this);
		}
	}

	public final StateParentContext stateParent() throws RecognitionException {
		StateParentContext _localctx = new StateParentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_stateParent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121); match(IDENT);
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

	public static class StateGraphContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public StateGraphContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateGraph; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStateGraph(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStateGraph(this);
		}
	}

	public final StateGraphContext stateGraph() throws RecognitionException {
		StateGraphContext _localctx = new StateGraphContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stateGraph);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(IDENT);
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

	public static class NodestmtContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public NodeInternalContext nodeInternal() {
			return getRuleContext(NodeInternalContext.class,0);
		}
		public NodeTypeContext nodeType() {
			return getRuleContext(NodeTypeContext.class,0);
		}
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public ActiveSelectorContext activeSelector() {
			return getRuleContext(ActiveSelectorContext.class,0);
		}
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public NodestmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodestmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterNodestmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitNodestmt(this);
		}
	}

	public final NodestmtContext nodestmt() throws RecognitionException {
		NodestmtContext _localctx = new NodestmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_nodestmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125); nodeType();
			setState(126); match(IDENT);
			setState(131);
			_la = _input.LA(1);
			if (_la==LSQUARE) {
				{
				setState(127); match(LSQUARE);
				setState(128); activeSelector();
				setState(129); match(RSQUARE);
				}
			}

			setState(133); match(BEGIN);
			setState(134); match(NEWLINE);
			setState(135); nodeInternal();
			setState(136); match(END);
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

	public static class NodeTypeContext extends ParserRuleContext {
		public TerminalNode SUBJUNCT() { return getToken(ShiroParser.SUBJUNCT, 0); }
		public TerminalNode NODE() { return getToken(ShiroParser.NODE, 0); }
		public NodeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterNodeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitNodeType(this);
		}
	}

	public final NodeTypeContext nodeType() throws RecognitionException {
		NodeTypeContext _localctx = new NodeTypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_nodeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			_la = _input.LA(1);
			if ( !(_la==NODE || _la==SUBJUNCT) ) {
			_errHandler.recoverInline(this);
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

	public static class NodeInternalContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public PortstmtContext portstmt(int i) {
			return getRuleContext(PortstmtContext.class,i);
		}
		public List<PortstmtContext> portstmt() {
			return getRuleContexts(PortstmtContext.class);
		}
		public NodeProductionContext nodeProduction(int i) {
			return getRuleContext(NodeProductionContext.class,i);
		}
		public List<PortAssignmentContext> portAssignment() {
			return getRuleContexts(PortAssignmentContext.class);
		}
		public List<NodeProductionContext> nodeProduction() {
			return getRuleContexts(NodeProductionContext.class);
		}
		public PortAssignmentContext portAssignment(int i) {
			return getRuleContext(PortAssignmentContext.class,i);
		}
		public NodeInternalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeInternal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterNodeInternal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitNodeInternal(this);
		}
	}

	public final NodeInternalContext nodeInternal() throws RecognitionException {
		NodeInternalContext _localctx = new NodeInternalContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_nodeInternal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(144);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(140); nodeProduction();
					}
					break;

				case 2:
					{
					setState(141); portAssignment();
					}
					break;

				case 3:
					{
					setState(142); portstmt();
					}
					break;

				case 4:
					{
					setState(143); match(NEWLINE);
					}
					break;
				}
				}
				setState(146); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 11) | (1L << NEWLINE) | (1L << IDENT))) != 0) );
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

	public static class SNodeContext extends ParserRuleContext {
		public Token nodeName;
		public Token selectedSubjunct;
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public SubjunctDeclNodeProdContext subjunctDeclNodeProd(int i) {
			return getRuleContext(SubjunctDeclNodeProdContext.class,i);
		}
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public SubjunctDeclContext subjunctDecl(int i) {
			return getRuleContext(SubjunctDeclContext.class,i);
		}
		public TerminalNode RSQUARE() { return getToken(ShiroParser.RSQUARE, 0); }
		public List<SubjunctDeclNodeProdContext> subjunctDeclNodeProd() {
			return getRuleContexts(SubjunctDeclNodeProdContext.class);
		}
		public TerminalNode LSQUARE() { return getToken(ShiroParser.LSQUARE, 0); }
		public TerminalNode SUBJ_NODE() { return getToken(ShiroParser.SUBJ_NODE, 0); }
		public List<SubjunctDeclContext> subjunctDecl() {
			return getRuleContexts(SubjunctDeclContext.class);
		}
		public List<TerminalNode> IDENT() { return getTokens(ShiroParser.IDENT); }
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public SNodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sNode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterSNode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitSNode(this);
		}
	}

	public final SNodeContext sNode() throws RecognitionException {
		SNodeContext _localctx = new SNodeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_sNode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148); match(SUBJ_NODE);
			setState(149); ((SNodeContext)_localctx).nodeName = match(IDENT);
			setState(150); match(LSQUARE);
			setState(151); ((SNodeContext)_localctx).selectedSubjunct = match(IDENT);
			setState(152); match(RSQUARE);
			setState(153); match(BEGIN);
			setState(154); match(NEWLINE);
			setState(158); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(158);
				switch (_input.LA(1)) {
				case IDENT:
					{
					setState(155); subjunctDeclNodeProd();
					}
					break;
				case NODE:
				case SUBJUNCT:
					{
					setState(156); subjunctDecl();
					}
					break;
				case NEWLINE:
					{
					setState(157); match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NODE) | (1L << SUBJUNCT) | (1L << NEWLINE) | (1L << IDENT))) != 0) );
			setState(162); match(END);
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

	public static class SubjunctDeclNodeProdContext extends ParserRuleContext {
		public Token nodeName;
		public Token newName;
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public TerminalNode PROD_OP() { return getToken(ShiroParser.PROD_OP, 0); }
		public NodeInternalContext nodeInternal() {
			return getRuleContext(NodeInternalContext.class,0);
		}
		public List<TerminalNode> IDENT() { return getTokens(ShiroParser.IDENT); }
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public SubjunctDeclNodeProdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subjunctDeclNodeProd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterSubjunctDeclNodeProd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitSubjunctDeclNodeProd(this);
		}
	}

	public final SubjunctDeclNodeProdContext subjunctDeclNodeProd() throws RecognitionException {
		SubjunctDeclNodeProdContext _localctx = new SubjunctDeclNodeProdContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_subjunctDeclNodeProd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); ((SubjunctDeclNodeProdContext)_localctx).nodeName = match(IDENT);
			setState(165); match(PROD_OP);
			setState(166); ((SubjunctDeclNodeProdContext)_localctx).newName = match(IDENT);
			setState(167); match(BEGIN);
			setState(168); match(NEWLINE);
			setState(169); nodeInternal();
			setState(170); match(END);
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

	public static class SubjunctDeclContext extends ParserRuleContext {
		public NodestmtContext nodestmt() {
			return getRuleContext(NodestmtContext.class,0);
		}
		public SubjunctDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subjunctDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterSubjunctDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitSubjunctDecl(this);
		}
	}

	public final SubjunctDeclContext subjunctDecl() throws RecognitionException {
		SubjunctDeclContext _localctx = new SubjunctDeclContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_subjunctDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172); nodestmt();
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

	public static class SubjunctSelectorContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public SubjunctSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subjunctSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterSubjunctSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitSubjunctSelector(this);
		}
	}

	public final SubjunctSelectorContext subjunctSelector() throws RecognitionException {
		SubjunctSelectorContext _localctx = new SubjunctSelectorContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_subjunctSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); match(IDENT);
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

	public static class GraphDeclContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public GraphLineContext graphLine(int i) {
			return getRuleContext(GraphLineContext.class,i);
		}
		public List<GraphLineContext> graphLine() {
			return getRuleContexts(GraphLineContext.class);
		}
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public GraphDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterGraphDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitGraphDecl(this);
		}
	}

	public final GraphDeclContext graphDecl() throws RecognitionException {
		GraphDeclContext _localctx = new GraphDeclContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_graphDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176); match(8);
			setState(177); match(IDENT);
			setState(178); match(BEGIN);
			setState(179); match(NEWLINE);
			setState(181); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(180); graphLine();
				}
				}
				setState(183); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NEWLINE || _la==IDENT );
			setState(185); match(END);
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

	public static class GraphLineContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public PortAssignmentContext portAssignment() {
			return getRuleContext(PortAssignmentContext.class,0);
		}
		public NodeProductionContext nodeProduction() {
			return getRuleContext(NodeProductionContext.class,0);
		}
		public GraphLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_graphLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterGraphLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitGraphLine(this);
		}
	}

	public final GraphLineContext graphLine() throws RecognitionException {
		GraphLineContext _localctx = new GraphLineContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_graphLine);
		try {
			setState(190);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(187); nodeProduction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(188); portAssignment();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(189); match(NEWLINE);
				}
				break;
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

	public static class ActiveSelectorContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public ActiveSelectorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activeSelector; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterActiveSelector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitActiveSelector(this);
		}
	}

	public final ActiveSelectorContext activeSelector() throws RecognitionException {
		ActiveSelectorContext _localctx = new ActiveSelectorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_activeSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192); match(IDENT);
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

	public static class NodeProductionContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public List<TerminalNode> PROD_OP() { return getTokens(ShiroParser.PROD_OP); }
		public ActivationContext activation(int i) {
			return getRuleContext(ActivationContext.class,i);
		}
		public TerminalNode PROD_OP(int i) {
			return getToken(ShiroParser.PROD_OP, i);
		}
		public List<ActivationContext> activation() {
			return getRuleContexts(ActivationContext.class);
		}
		public NodeProductionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeProduction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterNodeProduction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitNodeProduction(this);
		}
	}

	public final NodeProductionContext nodeProduction() throws RecognitionException {
		NodeProductionContext _localctx = new NodeProductionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_nodeProduction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194); path();
			setState(197); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(195); match(PROD_OP);
				setState(196); activation();
				}
				}
				setState(199); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PROD_OP );
			setState(201); match(NEWLINE);
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

	public static class ActivationContext extends ParserRuleContext {
		public Token nodeName;
		public Token activeObject;
		public TerminalNode RSQUARE() { return getToken(ShiroParser.RSQUARE, 0); }
		public TerminalNode LSQUARE() { return getToken(ShiroParser.LSQUARE, 0); }
		public List<TerminalNode> IDENT() { return getTokens(ShiroParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public ActivationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_activation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterActivation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitActivation(this);
		}
	}

	public final ActivationContext activation() throws RecognitionException {
		ActivationContext _localctx = new ActivationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_activation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203); ((ActivationContext)_localctx).nodeName = match(IDENT);
			setState(207);
			_la = _input.LA(1);
			if (_la==LSQUARE) {
				{
				setState(204); match(LSQUARE);
				setState(205); ((ActivationContext)_localctx).activeObject = match(IDENT);
				setState(206); match(RSQUARE);
				}
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

	public static class PortAssignmentContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public MfparamsContext mfparams() {
			return getRuleContext(MfparamsContext.class,0);
		}
		public PortAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPortAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPortAssignment(this);
		}
	}

	public final PortAssignmentContext portAssignment() throws RecognitionException {
		PortAssignmentContext _localctx = new PortAssignmentContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_portAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209); path();
			setState(210); match(5);
			setState(211); mfparams();
			setState(212); match(2);
			setState(213); match(NEWLINE);
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

	public static class PortDeclContext extends ParserRuleContext {
		public MfNameContext mfName() {
			return getRuleContext(MfNameContext.class,0);
		}
		public PortTypeContext portType() {
			return getRuleContext(PortTypeContext.class,0);
		}
		public PortNameContext portName() {
			return getRuleContext(PortNameContext.class,0);
		}
		public PortDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPortDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPortDecl(this);
		}
	}

	public final PortDeclContext portDecl() throws RecognitionException {
		PortDeclContext _localctx = new PortDeclContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_portDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215); portType();
			setState(216); portName();
			setState(217); mfName();
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

	public static class PortDeclInitContext extends ParserRuleContext {
		public PortTypeContext portType() {
			return getRuleContext(PortTypeContext.class,0);
		}
		public MfCallContext mfCall() {
			return getRuleContext(MfCallContext.class,0);
		}
		public PortNameContext portName() {
			return getRuleContext(PortNameContext.class,0);
		}
		public PortDeclInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portDeclInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPortDeclInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPortDeclInit(this);
		}
	}

	public final PortDeclInitContext portDeclInit() throws RecognitionException {
		PortDeclInitContext _localctx = new PortDeclInitContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_portDeclInit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219); portType();
			setState(220); portName();
			setState(221); mfCall();
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

	public static class PortstmtContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public PortDeclContext portDecl() {
			return getRuleContext(PortDeclContext.class,0);
		}
		public PortDeclInitContext portDeclInit() {
			return getRuleContext(PortDeclInitContext.class,0);
		}
		public PortstmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portstmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPortstmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPortstmt(this);
		}
	}

	public final PortstmtContext portstmt() throws RecognitionException {
		PortstmtContext _localctx = new PortstmtContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_portstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(223); portDecl();
				}
				break;

			case 2:
				{
				setState(224); portDeclInit();
				}
				break;
			}
			setState(227); match(NEWLINE);
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

	public static class PortNameContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public PortNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPortName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPortName(this);
		}
	}

	public final PortNameContext portName() throws RecognitionException {
		PortNameContext _localctx = new PortNameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_portName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229); match(IDENT);
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

	public static class PortTypeContext extends ParserRuleContext {
		public PortTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_portType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPortType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPortType(this);
		}
	}

	public final PortTypeContext portType() throws RecognitionException {
		PortTypeContext _localctx = new PortTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_portType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			_la = _input.LA(1);
			if ( !(_la==1 || _la==11) ) {
			_errHandler.recoverInline(this);
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

	public static class MfCallContext extends ParserRuleContext {
		public MfNameContext mfName() {
			return getRuleContext(MfNameContext.class,0);
		}
		public MfparamsContext mfparams() {
			return getRuleContext(MfparamsContext.class,0);
		}
		public MfCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mfCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterMfCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitMfCall(this);
		}
	}

	public final MfCallContext mfCall() throws RecognitionException {
		MfCallContext _localctx = new MfCallContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_mfCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233); mfName();
			setState(234); match(5);
			setState(235); mfparams();
			setState(236); match(2);
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

	public static class MfNameContext extends ParserRuleContext {
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public MfNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mfName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterMfName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitMfName(this);
		}
	}

	public final MfNameContext mfName() throws RecognitionException {
		MfNameContext _localctx = new MfNameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_mfName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238); match(IDENT);
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

	public static class MfparamsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MfparamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mfparams; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterMfparams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitMfparams(this);
		}
	}

	public final MfparamsContext mfparams() throws RecognitionException {
		MfparamsContext _localctx = new MfparamsContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_mfparams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240); expr(0);
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(241); match(4);
				setState(242); expr(0);
				}
				}
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class PathContext extends ParserRuleContext {
		public TerminalNode RSQUARE() { return getToken(ShiroParser.RSQUARE, 0); }
		public PathIndexContext pathIndex() {
			return getRuleContext(PathIndexContext.class,0);
		}
		public TerminalNode LSQUARE() { return getToken(ShiroParser.LSQUARE, 0); }
		public List<TerminalNode> IDENT() { return getTokens(ShiroParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPath(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_path);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(248); match(IDENT);
			}
			setState(253);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(249); match(3);
					setState(250); match(IDENT);
					}
					} 
				}
				setState(255);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(260);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(256); match(LSQUARE);
				setState(257); pathIndex();
				setState(258); match(RSQUARE);
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
		public TerminalNode STRING_LITERAL() { return getToken(ShiroParser.STRING_LITERAL, 0); }
		public TerminalNode NUMBER() { return getToken(ShiroParser.NUMBER, 0); }
		public PathIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPathIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPathIndex(this);
		}
	}

	public final PathIndexContext pathIndex() throws RecognitionException {
		PathIndexContext _localctx = new PathIndexContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_pathIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
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
		public TerminalNode OR_OP() { return getToken(ShiroParser.OR_OP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OrExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterOrExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitOrExp(this);
		}
	}
	public static class NumberExpContext extends ExprContext {
		public TerminalNode NUMBER() { return getToken(ShiroParser.NUMBER, 0); }
		public NumberExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterNumberExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitNumberExp(this);
		}
	}
	public static class MultExpContext extends ExprContext {
		public TerminalNode MULT_OP() { return getToken(ShiroParser.MULT_OP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MOD_OP() { return getToken(ShiroParser.MOD_OP, 0); }
		public TerminalNode DIV_OP() { return getToken(ShiroParser.DIV_OP, 0); }
		public MultExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterMultExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitMultExp(this);
		}
	}
	public static class AddExpContext extends ExprContext {
		public TerminalNode PLUS_OP() { return getToken(ShiroParser.PLUS_OP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MINUS_OP() { return getToken(ShiroParser.MINUS_OP, 0); }
		public AddExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterAddExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitAddExp(this);
		}
	}
	public static class PathExpContext extends ExprContext {
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public PathExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterPathExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitPathExp(this);
		}
	}

	public final ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
		ExprContext _prevctx = _localctx;
		int _startState = 72;
		enterRecursionRule(_localctx, RULE_expr);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			switch (_input.LA(1)) {
			case IDENT:
				{
				_localctx = new PathExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(265); path();
				}
				break;
			case NUMBER:
				{
				_localctx = new NumberExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(266); match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(280);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(278);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new OrExpContext(new ExprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(269);
						if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
						setState(270); match(OR_OP);
						setState(271); expr(6);
						}
						break;

					case 2:
						{
						_localctx = new MultExpContext(new ExprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(272);
						if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
						setState(273);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT_OP) | (1L << DIV_OP) | (1L << MOD_OP))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(274); expr(5);
						}
						break;

					case 3:
						{
						_localctx = new AddExpContext(new ExprContext(_parentctx, _parentState, _p));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(275);
						if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
						setState(276);
						_la = _input.LA(1);
						if ( !(_la==PLUS_OP || _la==MINUS_OP) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(277); expr(4);
						}
						break;
					}
					} 
				}
				setState(282);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
		case 36: return expr_sempred((ExprContext)_localctx, predIndex);
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
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3#\u011e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\6\2N\n\2\r\2\16\2O\3\3\3\3\3\3"+
		"\3\3\3\3\5\3W\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\6\5f\n\5\r\5\16\5g\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\5\17\u0086\n\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\6\21\u0093\n\21\r\21\16\21\u0094\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\6\22\u00a1\n\22\r\22\16\22\u00a2\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\6\26\u00b8\n\26\r\26\16\26\u00b9\3\26\3\26\3\27\3\27\3\27\5\27\u00c1"+
		"\n\27\3\30\3\30\3\31\3\31\3\31\6\31\u00c8\n\31\r\31\16\31\u00c9\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\5\32\u00d2\n\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\5\36\u00e4\n\36\3\36"+
		"\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3#\3#\3#\7#\u00f6\n#\f#\16"+
		"#\u00f9\13#\3$\3$\3$\7$\u00fe\n$\f$\16$\u0101\13$\3$\3$\3$\3$\5$\u0107"+
		"\n$\3%\3%\3&\3&\3&\5&\u010e\n&\3&\3&\3&\3&\3&\3&\3&\3&\3&\7&\u0119\n&"+
		"\f&\16&\u011c\13&\3&\2\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&("+
		"*,.\60\62\64\668:<>@BDFHJ\2\7\3\2\21\22\4\2\3\3\r\r\3\2!\"\3\2\34\36\3"+
		"\2\32\33\u0118\2M\3\2\2\2\4V\3\2\2\2\6X\3\2\2\2\be\3\2\2\2\ni\3\2\2\2"+
		"\fl\3\2\2\2\16o\3\2\2\2\20r\3\2\2\2\22u\3\2\2\2\24w\3\2\2\2\26y\3\2\2"+
		"\2\30{\3\2\2\2\32}\3\2\2\2\34\177\3\2\2\2\36\u008c\3\2\2\2 \u0092\3\2"+
		"\2\2\"\u0096\3\2\2\2$\u00a6\3\2\2\2&\u00ae\3\2\2\2(\u00b0\3\2\2\2*\u00b2"+
		"\3\2\2\2,\u00c0\3\2\2\2.\u00c2\3\2\2\2\60\u00c4\3\2\2\2\62\u00cd\3\2\2"+
		"\2\64\u00d3\3\2\2\2\66\u00d9\3\2\2\28\u00dd\3\2\2\2:\u00e3\3\2\2\2<\u00e7"+
		"\3\2\2\2>\u00e9\3\2\2\2@\u00eb\3\2\2\2B\u00f0\3\2\2\2D\u00f2\3\2\2\2F"+
		"\u00fa\3\2\2\2H\u0108\3\2\2\2J\u010d\3\2\2\2LN\5\4\3\2ML\3\2\2\2NO\3\2"+
		"\2\2OM\3\2\2\2OP\3\2\2\2P\3\3\2\2\2QW\5\34\17\2RW\5\6\4\2SW\5*\26\2TW"+
		"\5\"\22\2UW\7\30\2\2VQ\3\2\2\2VR\3\2\2\2VS\3\2\2\2VT\3\2\2\2VU\3\2\2\2"+
		"W\5\3\2\2\2XY\7\17\2\2YZ\5\22\n\2Z[\7\23\2\2[\\\7\30\2\2\\]\5\b\5\2]^"+
		"\7\24\2\2^\7\3\2\2\2_f\5\n\6\2`f\5\f\7\2af\5\16\b\2bf\5\20\t\2cf\5\62"+
		"\32\2df\7\30\2\2e_\3\2\2\2e`\3\2\2\2ea\3\2\2\2eb\3\2\2\2ec\3\2\2\2ed\3"+
		"\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2h\t\3\2\2\2ij\7\f\2\2jk\5\24\13\2"+
		"k\13\3\2\2\2lm\7\t\2\2mn\5\26\f\2n\r\3\2\2\2op\7\13\2\2pq\5\30\r\2q\17"+
		"\3\2\2\2rs\7\b\2\2st\5\32\16\2t\21\3\2\2\2uv\7#\2\2v\23\3\2\2\2wx\7!\2"+
		"\2x\25\3\2\2\2yz\7!\2\2z\27\3\2\2\2{|\7#\2\2|\31\3\2\2\2}~\7#\2\2~\33"+
		"\3\2\2\2\177\u0080\5\36\20\2\u0080\u0085\7#\2\2\u0081\u0082\7\37\2\2\u0082"+
		"\u0083\5.\30\2\u0083\u0084\7 \2\2\u0084\u0086\3\2\2\2\u0085\u0081\3\2"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\23\2\2\u0088"+
		"\u0089\7\30\2\2\u0089\u008a\5 \21\2\u008a\u008b\7\24\2\2\u008b\35\3\2"+
		"\2\2\u008c\u008d\t\2\2\2\u008d\37\3\2\2\2\u008e\u0093\5\60\31\2\u008f"+
		"\u0093\5\64\33\2\u0090\u0093\5:\36\2\u0091\u0093\7\30\2\2\u0092\u008e"+
		"\3\2\2\2\u0092\u008f\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095!\3\2\2\2"+
		"\u0096\u0097\7\20\2\2\u0097\u0098\7#\2\2\u0098\u0099\7\37\2\2\u0099\u009a"+
		"\7#\2\2\u009a\u009b\7 \2\2\u009b\u009c\7\23\2\2\u009c\u00a0\7\30\2\2\u009d"+
		"\u00a1\5$\23\2\u009e\u00a1\5&\24\2\u009f\u00a1\7\30\2\2\u00a0\u009d\3"+
		"\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\24"+
		"\2\2\u00a5#\3\2\2\2\u00a6\u00a7\7#\2\2\u00a7\u00a8\7\16\2\2\u00a8\u00a9"+
		"\7#\2\2\u00a9\u00aa\7\23\2\2\u00aa\u00ab\7\30\2\2\u00ab\u00ac\5 \21\2"+
		"\u00ac\u00ad\7\24\2\2\u00ad%\3\2\2\2\u00ae\u00af\5\34\17\2\u00af\'\3\2"+
		"\2\2\u00b0\u00b1\7#\2\2\u00b1)\3\2\2\2\u00b2\u00b3\7\n\2\2\u00b3\u00b4"+
		"\7#\2\2\u00b4\u00b5\7\23\2\2\u00b5\u00b7\7\30\2\2\u00b6\u00b8\5,\27\2"+
		"\u00b7\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba"+
		"\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\7\24\2\2\u00bc+\3\2\2\2\u00bd"+
		"\u00c1\5\60\31\2\u00be\u00c1\5\64\33\2\u00bf\u00c1\7\30\2\2\u00c0\u00bd"+
		"\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1-\3\2\2\2\u00c2"+
		"\u00c3\7#\2\2\u00c3/\3\2\2\2\u00c4\u00c7\5F$\2\u00c5\u00c6\7\16\2\2\u00c6"+
		"\u00c8\5\62\32\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00c7\3"+
		"\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\7\30\2\2\u00cc"+
		"\61\3\2\2\2\u00cd\u00d1\7#\2\2\u00ce\u00cf\7\37\2\2\u00cf\u00d0\7#\2\2"+
		"\u00d0\u00d2\7 \2\2\u00d1\u00ce\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\63\3"+
		"\2\2\2\u00d3\u00d4\5F$\2\u00d4\u00d5\7\7\2\2\u00d5\u00d6\5D#\2\u00d6\u00d7"+
		"\7\4\2\2\u00d7\u00d8\7\30\2\2\u00d8\65\3\2\2\2\u00d9\u00da\5> \2\u00da"+
		"\u00db\5<\37\2\u00db\u00dc\5B\"\2\u00dc\67\3\2\2\2\u00dd\u00de\5> \2\u00de"+
		"\u00df\5<\37\2\u00df\u00e0\5@!\2\u00e09\3\2\2\2\u00e1\u00e4\5\66\34\2"+
		"\u00e2\u00e4\58\35\2\u00e3\u00e1\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e5"+
		"\3\2\2\2\u00e5\u00e6\7\30\2\2\u00e6;\3\2\2\2\u00e7\u00e8\7#\2\2\u00e8"+
		"=\3\2\2\2\u00e9\u00ea\t\3\2\2\u00ea?\3\2\2\2\u00eb\u00ec\5B\"\2\u00ec"+
		"\u00ed\7\7\2\2\u00ed\u00ee\5D#\2\u00ee\u00ef\7\4\2\2\u00efA\3\2\2\2\u00f0"+
		"\u00f1\7#\2\2\u00f1C\3\2\2\2\u00f2\u00f7\5J&\2\u00f3\u00f4\7\6\2\2\u00f4"+
		"\u00f6\5J&\2\u00f5\u00f3\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2"+
		"\2\u00f7\u00f8\3\2\2\2\u00f8E\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00ff"+
		"\7#\2\2\u00fb\u00fc\7\5\2\2\u00fc\u00fe\7#\2\2\u00fd\u00fb\3\2\2\2\u00fe"+
		"\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0106\3\2"+
		"\2\2\u0101\u00ff\3\2\2\2\u0102\u0103\7\37\2\2\u0103\u0104\5H%\2\u0104"+
		"\u0105\7 \2\2\u0105\u0107\3\2\2\2\u0106\u0102\3\2\2\2\u0106\u0107\3\2"+
		"\2\2\u0107G\3\2\2\2\u0108\u0109\t\4\2\2\u0109I\3\2\2\2\u010a\u010b\b&"+
		"\1\2\u010b\u010e\5F$\2\u010c\u010e\7\"\2\2\u010d\u010a\3\2\2\2\u010d\u010c"+
		"\3\2\2\2\u010e\u011a\3\2\2\2\u010f\u0110\6&\2\3\u0110\u0111\7\31\2\2\u0111"+
		"\u0119\5J&\2\u0112\u0113\6&\3\3\u0113\u0114\t\5\2\2\u0114\u0119\5J&\2"+
		"\u0115\u0116\6&\4\3\u0116\u0117\t\6\2\2\u0117\u0119\5J&\2\u0118\u010f"+
		"\3\2\2\2\u0118\u0112\3\2\2\2\u0118\u0115\3\2\2\2\u0119\u011c\3\2\2\2\u011a"+
		"\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011bK\3\2\2\2\u011c\u011a\3\2\2\2"+
		"\26OVeg\u0085\u0092\u0094\u00a0\u00a2\u00b9\u00c0\u00c9\u00d1\u00e3\u00f7"+
		"\u00ff\u0106\u010d\u0118\u011a";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}