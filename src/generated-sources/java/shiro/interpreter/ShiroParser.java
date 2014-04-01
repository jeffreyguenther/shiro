// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Shiro.g4 by ANTLR 4.2.1
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
		BEGIN=17, END=18, OR_OP=19, PLUS_OP=20, MINUS_OP=21, MULT_OP=22, DIV_OP=23, 
		MOD_OP=24, LSQUARE=25, RSQUARE=26, STRING_LITERAL=27, NUMBER=28, IDENT=29, 
		WS=30, COMMENT=31, LINE_COMMENT=32, NEWLINE=33;
	public static final String[] tokenNames = {
		"<INVALID>", "'eval'", "'port'", "'Comment'", "'Graph'", "'Time'", "'graph'", 
		"'Parent'", "'('", "')'", "','", "'.'", "PROD_OP", "'state'", "'subjunctive node'", 
		"'node'", "'subjunct'", "'begin'", "'end'", "'|'", "'+'", "'-'", "'*'", 
		"'/'", "'%'", "'['", "']'", "STRING_LITERAL", "NUMBER", "IDENT", "WS", 
		"COMMENT", "LINE_COMMENT", "NEWLINE"
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
		RULE_mfName = 32, RULE_mfparams = 33, RULE_expression = 34, RULE_path = 35, 
		RULE_pathIndex = 36, RULE_expr = 37;
	public static final String[] ruleNames = {
		"shiro", "statement", "statestmt", "stateHeader", "stateTimeStmt", "stateCommentStmt", 
		"stateParentStmt", "stateGraphStmt", "stateName", "time", "comment", "stateParent", 
		"stateGraph", "nodestmt", "nodeType", "nodeInternal", "sNode", "subjunctDeclNodeProd", 
		"subjunctDecl", "subjunctSelector", "graphDecl", "graphLine", "activeSelector", 
		"nodeProduction", "activation", "portAssignment", "portDecl", "portDeclInit", 
		"portstmt", "portName", "portType", "mfCall", "mfName", "mfparams", "expression", 
		"path", "pathIndex", "expr"
	};

	@Override
	public String getGrammarFileName() { return "Shiro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ShiroParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ShiroContext extends ParserRuleContext {
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
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
			setState(77); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(76); statement();
				}
				}
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << STATE) | (1L << SUBJ_NODE) | (1L << NODE) | (1L << SUBJUNCT) | (1L << NEWLINE))) != 0) );
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
		public StatestmtContext statestmt() {
			return getRuleContext(StatestmtContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public NodestmtContext nodestmt() {
			return getRuleContext(NodestmtContext.class,0);
		}
		public GraphDeclContext graphDecl() {
			return getRuleContext(GraphDeclContext.class,0);
		}
		public SNodeContext sNode() {
			return getRuleContext(SNodeContext.class,0);
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
			setState(86);
			switch (_input.LA(1)) {
			case NODE:
			case SUBJUNCT:
				enterOuterAlt(_localctx, 1);
				{
				setState(81); nodestmt();
				}
				break;
			case STATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(82); statestmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 3);
				{
				setState(83); graphDecl();
				}
				break;
			case SUBJ_NODE:
				enterOuterAlt(_localctx, 4);
				{
				setState(84); sNode();
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 5);
				{
				setState(85); match(NEWLINE);
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
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public StateHeaderContext stateHeader() {
			return getRuleContext(StateHeaderContext.class,0);
		}
		public TerminalNode STATE() { return getToken(ShiroParser.STATE, 0); }
		public StateNameContext stateName() {
			return getRuleContext(StateNameContext.class,0);
		}
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
			setState(88); match(STATE);
			setState(89); stateName();
			setState(90); match(BEGIN);
			setState(91); match(NEWLINE);
			setState(92); stateHeader();
			setState(93); match(END);
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
		public List<StateCommentStmtContext> stateCommentStmt() {
			return getRuleContexts(StateCommentStmtContext.class);
		}
		public List<ActivationContext> activation() {
			return getRuleContexts(ActivationContext.class);
		}
		public List<StateParentStmtContext> stateParentStmt() {
			return getRuleContexts(StateParentStmtContext.class);
		}
		public StateTimeStmtContext stateTimeStmt(int i) {
			return getRuleContext(StateTimeStmtContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public List<StateTimeStmtContext> stateTimeStmt() {
			return getRuleContexts(StateTimeStmtContext.class);
		}
		public List<StateGraphStmtContext> stateGraphStmt() {
			return getRuleContexts(StateGraphStmtContext.class);
		}
		public StateParentStmtContext stateParentStmt(int i) {
			return getRuleContext(StateParentStmtContext.class,i);
		}
		public StateGraphStmtContext stateGraphStmt(int i) {
			return getRuleContext(StateGraphStmtContext.class,i);
		}
		public ActivationContext activation(int i) {
			return getRuleContext(ActivationContext.class,i);
		}
		public StateCommentStmtContext stateCommentStmt(int i) {
			return getRuleContext(StateCommentStmtContext.class,i);
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
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(101);
				switch (_input.LA(1)) {
				case 5:
					{
					setState(95); stateTimeStmt();
					}
					break;
				case 3:
					{
					setState(96); stateCommentStmt();
					}
					break;
				case 7:
					{
					setState(97); stateParentStmt();
					}
					break;
				case 4:
					{
					setState(98); stateGraphStmt();
					}
					break;
				case IDENT:
					{
					setState(99); activation();
					}
					break;
				case NEWLINE:
					{
					setState(100); match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 4) | (1L << 5) | (1L << 7) | (1L << IDENT) | (1L << NEWLINE))) != 0) );
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
			setState(105); match(5);
			setState(106); time();
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
			setState(108); match(3);
			setState(109); comment();
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
			setState(111); match(7);
			setState(112); stateParent();
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
			setState(114); match(4);
			setState(115); stateGraph();
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
			setState(117); match(IDENT);
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
			setState(121); match(STRING_LITERAL);
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
			setState(125); match(IDENT);
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
		public NodeInternalContext nodeInternal() {
			return getRuleContext(NodeInternalContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public NodeTypeContext nodeType() {
			return getRuleContext(NodeTypeContext.class,0);
		}
		public ActiveSelectorContext activeSelector() {
			return getRuleContext(ActiveSelectorContext.class,0);
		}
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
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
			setState(127); nodeType();
			setState(128); match(IDENT);
			setState(133);
			_la = _input.LA(1);
			if (_la==LSQUARE) {
				{
				setState(129); match(LSQUARE);
				setState(130); activeSelector();
				setState(131); match(RSQUARE);
				}
			}

			setState(135); match(BEGIN);
			setState(136); match(NEWLINE);
			setState(137); nodeInternal();
			setState(138); match(END);
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
		public TerminalNode NODE() { return getToken(ShiroParser.NODE, 0); }
		public TerminalNode SUBJUNCT() { return getToken(ShiroParser.SUBJUNCT, 0); }
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
			setState(140);
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
		public List<NodeProductionContext> nodeProduction() {
			return getRuleContexts(NodeProductionContext.class);
		}
		public PortstmtContext portstmt(int i) {
			return getRuleContext(PortstmtContext.class,i);
		}
		public PortAssignmentContext portAssignment(int i) {
			return getRuleContext(PortAssignmentContext.class,i);
		}
		public List<PortstmtContext> portstmt() {
			return getRuleContexts(PortstmtContext.class);
		}
		public NodeProductionContext nodeProduction(int i) {
			return getRuleContext(NodeProductionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public List<PortAssignmentContext> portAssignment() {
			return getRuleContexts(PortAssignmentContext.class);
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
			setState(146); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(146);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(142); nodeProduction();
					}
					break;

				case 2:
					{
					setState(143); portAssignment();
					}
					break;

				case 3:
					{
					setState(144); portstmt();
					}
					break;

				case 4:
					{
					setState(145); match(NEWLINE);
					}
					break;
				}
				}
				setState(148); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << IDENT) | (1L << NEWLINE))) != 0) );
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
		public List<SubjunctDeclNodeProdContext> subjunctDeclNodeProd() {
			return getRuleContexts(SubjunctDeclNodeProdContext.class);
		}
		public SubjunctDeclContext subjunctDecl(int i) {
			return getRuleContext(SubjunctDeclContext.class,i);
		}
		public TerminalNode LSQUARE() { return getToken(ShiroParser.LSQUARE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public List<SubjunctDeclContext> subjunctDecl() {
			return getRuleContexts(SubjunctDeclContext.class);
		}
		public List<TerminalNode> IDENT() { return getTokens(ShiroParser.IDENT); }
		public TerminalNode RSQUARE() { return getToken(ShiroParser.RSQUARE, 0); }
		public SubjunctDeclNodeProdContext subjunctDeclNodeProd(int i) {
			return getRuleContext(SubjunctDeclNodeProdContext.class,i);
		}
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public TerminalNode SUBJ_NODE() { return getToken(ShiroParser.SUBJ_NODE, 0); }
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
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
			setState(150); match(SUBJ_NODE);
			setState(151); ((SNodeContext)_localctx).nodeName = match(IDENT);
			setState(152); match(LSQUARE);
			setState(153); ((SNodeContext)_localctx).selectedSubjunct = match(IDENT);
			setState(154); match(RSQUARE);
			setState(155); match(BEGIN);
			setState(156); match(NEWLINE);
			setState(160); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(160);
				switch (_input.LA(1)) {
				case IDENT:
					{
					setState(157); subjunctDeclNodeProd();
					}
					break;
				case NODE:
				case SUBJUNCT:
					{
					setState(158); subjunctDecl();
					}
					break;
				case NEWLINE:
					{
					setState(159); match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(162); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NODE) | (1L << SUBJUNCT) | (1L << IDENT) | (1L << NEWLINE))) != 0) );
			setState(164); match(END);
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
		public NodeInternalContext nodeInternal() {
			return getRuleContext(NodeInternalContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public TerminalNode PROD_OP() { return getToken(ShiroParser.PROD_OP, 0); }
		public List<TerminalNode> IDENT() { return getTokens(ShiroParser.IDENT); }
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
			setState(166); ((SubjunctDeclNodeProdContext)_localctx).nodeName = match(IDENT);
			setState(167); match(PROD_OP);
			setState(168); ((SubjunctDeclNodeProdContext)_localctx).newName = match(IDENT);
			setState(169); match(BEGIN);
			setState(170); match(NEWLINE);
			setState(171); nodeInternal();
			setState(172); match(END);
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
			setState(174); nodestmt();
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
			setState(176); match(IDENT);
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
		public GraphLineContext graphLine(int i) {
			return getRuleContext(GraphLineContext.class,i);
		}
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public TerminalNode IDENT() { return getToken(ShiroParser.IDENT, 0); }
		public List<GraphLineContext> graphLine() {
			return getRuleContexts(GraphLineContext.class);
		}
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
			setState(178); match(6);
			setState(179); match(IDENT);
			setState(180); match(BEGIN);
			setState(181); match(NEWLINE);
			setState(183); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(182); graphLine();
				}
				}
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENT || _la==NEWLINE );
			setState(187); match(END);
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
		public NodeProductionContext nodeProduction() {
			return getRuleContext(NodeProductionContext.class,0);
		}
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public PortAssignmentContext portAssignment() {
			return getRuleContext(PortAssignmentContext.class,0);
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
			setState(192);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(189); nodeProduction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(190); portAssignment();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(191); match(NEWLINE);
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
			setState(194); match(IDENT);
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
		public List<ActivationContext> activation() {
			return getRuleContexts(ActivationContext.class);
		}
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public TerminalNode PROD_OP(int i) {
			return getToken(ShiroParser.PROD_OP, i);
		}
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public List<TerminalNode> PROD_OP() { return getTokens(ShiroParser.PROD_OP); }
		public ActivationContext activation(int i) {
			return getRuleContext(ActivationContext.class,i);
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
			setState(196); path();
			setState(199); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(197); match(PROD_OP);
				setState(198); activation();
				}
				}
				setState(201); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PROD_OP );
			setState(203); match(NEWLINE);
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
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public List<TerminalNode> IDENT() { return getTokens(ShiroParser.IDENT); }
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
			setState(205); ((ActivationContext)_localctx).nodeName = match(IDENT);
			setState(209);
			_la = _input.LA(1);
			if (_la==LSQUARE) {
				{
				setState(206); match(LSQUARE);
				setState(207); ((ActivationContext)_localctx).activeObject = match(IDENT);
				setState(208); match(RSQUARE);
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
		public MfparamsContext mfparams() {
			return getRuleContext(MfparamsContext.class,0);
		}
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
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
			setState(211); path();
			setState(212); match(8);
			setState(213); mfparams();
			setState(214); match(9);
			setState(215); match(NEWLINE);
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
		public PortNameContext portName() {
			return getRuleContext(PortNameContext.class,0);
		}
		public PortTypeContext portType() {
			return getRuleContext(PortTypeContext.class,0);
		}
		public MfNameContext mfName() {
			return getRuleContext(MfNameContext.class,0);
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
			setState(217); portType();
			setState(218); portName();
			setState(219); mfName();
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
		public PortNameContext portName() {
			return getRuleContext(PortNameContext.class,0);
		}
		public PortTypeContext portType() {
			return getRuleContext(PortTypeContext.class,0);
		}
		public MfCallContext mfCall() {
			return getRuleContext(MfCallContext.class,0);
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
			setState(221); portType();
			setState(222); portName();
			setState(223); mfCall();
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
			setState(227);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(225); portDecl();
				}
				break;

			case 2:
				{
				setState(226); portDeclInit();
				}
				break;
			}
			setState(229); match(NEWLINE);
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
			setState(231); match(IDENT);
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
			setState(233);
			_la = _input.LA(1);
			if ( !(_la==1 || _la==2) ) {
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
			setState(235); mfName();
			setState(236); match(8);
			setState(237); mfparams();
			setState(238); match(9);
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
			setState(240); match(IDENT);
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
			setState(242); expr(0);
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==10) {
				{
				{
				setState(243); match(10);
				setState(244); expr(0);
				}
				}
				setState(249);
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
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250); expr(0);
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
		public PathIndexContext pathIndex() {
			return getRuleContext(PathIndexContext.class,0);
		}
		public TerminalNode RSQUARE() { return getToken(ShiroParser.RSQUARE, 0); }
		public TerminalNode LSQUARE() { return getToken(ShiroParser.LSQUARE, 0); }
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public List<TerminalNode> IDENT() { return getTokens(ShiroParser.IDENT); }
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
		enterRule(_localctx, 70, RULE_path);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(252); match(IDENT);
			}
			setState(257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					{
					{
					setState(253); match(11);
					setState(254); match(IDENT);
					}
					} 
				}
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(264);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(260); match(LSQUARE);
				setState(261); pathIndex();
				setState(262); match(RSQUARE);
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
		enterRule(_localctx, 72, RULE_pathIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
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
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
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
	public static class OrExpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR_OP() { return getToken(ShiroParser.OR_OP, 0); }
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
	public static class AddExpContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode MINUS_OP() { return getToken(ShiroParser.MINUS_OP, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS_OP() { return getToken(ShiroParser.PLUS_OP, 0); }
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

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 74;
		enterRecursionRule(_localctx, 74, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			switch (_input.LA(1)) {
			case IDENT:
				{
				_localctx = new PathExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(269); path();
				}
				break;
			case NUMBER:
				{
				_localctx = new NumberExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(270); match(NUMBER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(284);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(282);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new OrExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(273);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(274); match(OR_OP);
						setState(275); expr(6);
						}
						break;

					case 2:
						{
						_localctx = new MultExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(276);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(277);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT_OP) | (1L << DIV_OP) | (1L << MOD_OP))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(278); expr(5);
						}
						break;

					case 3:
						{
						_localctx = new AddExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(279);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(280);
						_la = _input.LA(1);
						if ( !(_la==PLUS_OP || _la==MINUS_OP) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(281); expr(4);
						}
						break;
					}
					} 
				}
				setState(286);
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
		case 37: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 5);

		case 1: return precpred(_ctx, 4);

		case 2: return precpred(_ctx, 3);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3#\u0122\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\6\2P\n\2\r\2\16\2Q\3\3"+
		"\3\3\3\3\3\3\3\3\5\3Y\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\6\5h\n\5\r\5\16\5i\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\5\17\u0088\n\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\6\21\u0095\n\21\r\21\16\21\u0096\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\6\22\u00a3\n\22\r\22\16\22\u00a4\3\22\3\22\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\6\26\u00ba\n\26\r\26\16\26\u00bb\3\26\3\26\3\27\3\27\3\27\5"+
		"\27\u00c3\n\27\3\30\3\30\3\31\3\31\3\31\6\31\u00ca\n\31\r\31\16\31\u00cb"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\5\32\u00d4\n\32\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\5\36\u00e6\n\36"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3#\3#\3#\7#\u00f8\n"+
		"#\f#\16#\u00fb\13#\3$\3$\3%\3%\3%\7%\u0102\n%\f%\16%\u0105\13%\3%\3%\3"+
		"%\3%\5%\u010b\n%\3&\3&\3\'\3\'\3\'\5\'\u0112\n\'\3\'\3\'\3\'\3\'\3\'\3"+
		"\'\3\'\3\'\3\'\7\'\u011d\n\'\f\'\16\'\u0120\13\'\3\'\2\3L(\2\4\6\b\n\f"+
		"\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJL\2\7\3\2\21"+
		"\22\3\2\3\4\3\2\35\36\3\2\30\32\3\2\26\27\u011b\2O\3\2\2\2\4X\3\2\2\2"+
		"\6Z\3\2\2\2\bg\3\2\2\2\nk\3\2\2\2\fn\3\2\2\2\16q\3\2\2\2\20t\3\2\2\2\22"+
		"w\3\2\2\2\24y\3\2\2\2\26{\3\2\2\2\30}\3\2\2\2\32\177\3\2\2\2\34\u0081"+
		"\3\2\2\2\36\u008e\3\2\2\2 \u0094\3\2\2\2\"\u0098\3\2\2\2$\u00a8\3\2\2"+
		"\2&\u00b0\3\2\2\2(\u00b2\3\2\2\2*\u00b4\3\2\2\2,\u00c2\3\2\2\2.\u00c4"+
		"\3\2\2\2\60\u00c6\3\2\2\2\62\u00cf\3\2\2\2\64\u00d5\3\2\2\2\66\u00db\3"+
		"\2\2\28\u00df\3\2\2\2:\u00e5\3\2\2\2<\u00e9\3\2\2\2>\u00eb\3\2\2\2@\u00ed"+
		"\3\2\2\2B\u00f2\3\2\2\2D\u00f4\3\2\2\2F\u00fc\3\2\2\2H\u00fe\3\2\2\2J"+
		"\u010c\3\2\2\2L\u0111\3\2\2\2NP\5\4\3\2ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2"+
		"QR\3\2\2\2R\3\3\2\2\2SY\5\34\17\2TY\5\6\4\2UY\5*\26\2VY\5\"\22\2WY\7#"+
		"\2\2XS\3\2\2\2XT\3\2\2\2XU\3\2\2\2XV\3\2\2\2XW\3\2\2\2Y\5\3\2\2\2Z[\7"+
		"\17\2\2[\\\5\22\n\2\\]\7\23\2\2]^\7#\2\2^_\5\b\5\2_`\7\24\2\2`\7\3\2\2"+
		"\2ah\5\n\6\2bh\5\f\7\2ch\5\16\b\2dh\5\20\t\2eh\5\62\32\2fh\7#\2\2ga\3"+
		"\2\2\2gb\3\2\2\2gc\3\2\2\2gd\3\2\2\2ge\3\2\2\2gf\3\2\2\2hi\3\2\2\2ig\3"+
		"\2\2\2ij\3\2\2\2j\t\3\2\2\2kl\7\7\2\2lm\5\24\13\2m\13\3\2\2\2no\7\5\2"+
		"\2op\5\26\f\2p\r\3\2\2\2qr\7\t\2\2rs\5\30\r\2s\17\3\2\2\2tu\7\6\2\2uv"+
		"\5\32\16\2v\21\3\2\2\2wx\7\37\2\2x\23\3\2\2\2yz\7\35\2\2z\25\3\2\2\2{"+
		"|\7\35\2\2|\27\3\2\2\2}~\7\37\2\2~\31\3\2\2\2\177\u0080\7\37\2\2\u0080"+
		"\33\3\2\2\2\u0081\u0082\5\36\20\2\u0082\u0087\7\37\2\2\u0083\u0084\7\33"+
		"\2\2\u0084\u0085\5.\30\2\u0085\u0086\7\34\2\2\u0086\u0088\3\2\2\2\u0087"+
		"\u0083\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\7\23"+
		"\2\2\u008a\u008b\7#\2\2\u008b\u008c\5 \21\2\u008c\u008d\7\24\2\2\u008d"+
		"\35\3\2\2\2\u008e\u008f\t\2\2\2\u008f\37\3\2\2\2\u0090\u0095\5\60\31\2"+
		"\u0091\u0095\5\64\33\2\u0092\u0095\5:\36\2\u0093\u0095\7#\2\2\u0094\u0090"+
		"\3\2\2\2\u0094\u0091\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097!\3\2\2\2"+
		"\u0098\u0099\7\20\2\2\u0099\u009a\7\37\2\2\u009a\u009b\7\33\2\2\u009b"+
		"\u009c\7\37\2\2\u009c\u009d\7\34\2\2\u009d\u009e\7\23\2\2\u009e\u00a2"+
		"\7#\2\2\u009f\u00a3\5$\23\2\u00a0\u00a3\5&\24\2\u00a1\u00a3\7#\2\2\u00a2"+
		"\u009f\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a4\3\2"+
		"\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00a7\7\24\2\2\u00a7#\3\2\2\2\u00a8\u00a9\7\37\2\2\u00a9\u00aa\7\16\2"+
		"\2\u00aa\u00ab\7\37\2\2\u00ab\u00ac\7\23\2\2\u00ac\u00ad\7#\2\2\u00ad"+
		"\u00ae\5 \21\2\u00ae\u00af\7\24\2\2\u00af%\3\2\2\2\u00b0\u00b1\5\34\17"+
		"\2\u00b1\'\3\2\2\2\u00b2\u00b3\7\37\2\2\u00b3)\3\2\2\2\u00b4\u00b5\7\b"+
		"\2\2\u00b5\u00b6\7\37\2\2\u00b6\u00b7\7\23\2\2\u00b7\u00b9\7#\2\2\u00b8"+
		"\u00ba\5,\27\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\7\24\2\2\u00be"+
		"+\3\2\2\2\u00bf\u00c3\5\60\31\2\u00c0\u00c3\5\64\33\2\u00c1\u00c3\7#\2"+
		"\2\u00c2\u00bf\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3-"+
		"\3\2\2\2\u00c4\u00c5\7\37\2\2\u00c5/\3\2\2\2\u00c6\u00c9\5H%\2\u00c7\u00c8"+
		"\7\16\2\2\u00c8\u00ca\5\62\32\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb\3\2\2"+
		"\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce"+
		"\7#\2\2\u00ce\61\3\2\2\2\u00cf\u00d3\7\37\2\2\u00d0\u00d1\7\33\2\2\u00d1"+
		"\u00d2\7\37\2\2\u00d2\u00d4\7\34\2\2\u00d3\u00d0\3\2\2\2\u00d3\u00d4\3"+
		"\2\2\2\u00d4\63\3\2\2\2\u00d5\u00d6\5H%\2\u00d6\u00d7\7\n\2\2\u00d7\u00d8"+
		"\5D#\2\u00d8\u00d9\7\13\2\2\u00d9\u00da\7#\2\2\u00da\65\3\2\2\2\u00db"+
		"\u00dc\5> \2\u00dc\u00dd\5<\37\2\u00dd\u00de\5B\"\2\u00de\67\3\2\2\2\u00df"+
		"\u00e0\5> \2\u00e0\u00e1\5<\37\2\u00e1\u00e2\5@!\2\u00e29\3\2\2\2\u00e3"+
		"\u00e6\5\66\34\2\u00e4\u00e6\58\35\2\u00e5\u00e3\3\2\2\2\u00e5\u00e4\3"+
		"\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e8\7#\2\2\u00e8;\3\2\2\2\u00e9\u00ea"+
		"\7\37\2\2\u00ea=\3\2\2\2\u00eb\u00ec\t\3\2\2\u00ec?\3\2\2\2\u00ed\u00ee"+
		"\5B\"\2\u00ee\u00ef\7\n\2\2\u00ef\u00f0\5D#\2\u00f0\u00f1\7\13\2\2\u00f1"+
		"A\3\2\2\2\u00f2\u00f3\7\37\2\2\u00f3C\3\2\2\2\u00f4\u00f9\5L\'\2\u00f5"+
		"\u00f6\7\f\2\2\u00f6\u00f8\5L\'\2\u00f7\u00f5\3\2\2\2\u00f8\u00fb\3\2"+
		"\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00faE\3\2\2\2\u00fb\u00f9"+
		"\3\2\2\2\u00fc\u00fd\5L\'\2\u00fdG\3\2\2\2\u00fe\u0103\7\37\2\2\u00ff"+
		"\u0100\7\r\2\2\u0100\u0102\7\37\2\2\u0101\u00ff\3\2\2\2\u0102\u0105\3"+
		"\2\2\2\u0103\u0101\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u010a\3\2\2\2\u0105"+
		"\u0103\3\2\2\2\u0106\u0107\7\33\2\2\u0107\u0108\5J&\2\u0108\u0109\7\34"+
		"\2\2\u0109\u010b\3\2\2\2\u010a\u0106\3\2\2\2\u010a\u010b\3\2\2\2\u010b"+
		"I\3\2\2\2\u010c\u010d\t\4\2\2\u010dK\3\2\2\2\u010e\u010f\b\'\1\2\u010f"+
		"\u0112\5H%\2\u0110\u0112\7\36\2\2\u0111\u010e\3\2\2\2\u0111\u0110\3\2"+
		"\2\2\u0112\u011e\3\2\2\2\u0113\u0114\f\7\2\2\u0114\u0115\7\25\2\2\u0115"+
		"\u011d\5L\'\b\u0116\u0117\f\6\2\2\u0117\u0118\t\5\2\2\u0118\u011d\5L\'"+
		"\7\u0119\u011a\f\5\2\2\u011a\u011b\t\6\2\2\u011b\u011d\5L\'\6\u011c\u0113"+
		"\3\2\2\2\u011c\u0116\3\2\2\2\u011c\u0119\3\2\2\2\u011d\u0120\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011fM\3\2\2\2\u0120\u011e\3\2\2\2"+
		"\26QXgi\u0087\u0094\u0096\u00a2\u00a4\u00bb\u00c2\u00cb\u00d3\u00e5\u00f9"+
		"\u0103\u010a\u0111\u011c\u011e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}