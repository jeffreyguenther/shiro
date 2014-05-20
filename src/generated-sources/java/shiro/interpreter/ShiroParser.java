// Generated from /Users/jeffreyguenther/Projects/Thesis/shiro/src/main/java/shiro/interpreter/Shiro.g4 by ANTLR 4.2.2
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
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		USE=10, THIS=11, OPTION=12, PROD_OP=13, REFINES_OP=14, STATE=15, SUBJ_NODE=16, 
		NODE=17, SUBJUNCT=18, BEGIN=19, END=20, OR_OP=21, PLUS_OP=22, MINUS_OP=23, 
		MULT_OP=24, DIV_OP=25, MOD_OP=26, LSQUARE=27, RSQUARE=28, STRING_LITERAL=29, 
		NUMBER=30, IDENT=31, WS=32, COMMENT=33, LINE_COMMENT=34, NEWLINE=35;
	public static final String[] tokenNames = {
		"<INVALID>", "'Graph'", "'graph'", "'eval'", "'input'", "'('", "')'", 
		"','", "'.'", "'output'", "USE", "'this'", "'option'", "'->'", "'<-'", 
		"'state'", "'subjunctive node'", "'node'", "'subjunct'", "'begin'", "'end'", 
		"'|'", "'+'", "'-'", "'*'", "'/'", "'%'", "'['", "']'", "STRING_LITERAL", 
		"NUMBER", "IDENT", "WS", "COMMENT", "LINE_COMMENT", "NEWLINE"
	};
	public static final int
		RULE_shiro = 0, RULE_useStatement = 1, RULE_statement = 2, RULE_statestmt = 3, 
		RULE_stateHeader = 4, RULE_stateGraphStmt = 5, RULE_stateName = 6, RULE_stateGraph = 7, 
		RULE_nodestmt = 8, RULE_nodeInternal = 9, RULE_subjunctDeclNodeProd = 10, 
		RULE_graphDecl = 11, RULE_graphLine = 12, RULE_activeSelector = 13, RULE_nodeProduction = 14, 
		RULE_activation = 15, RULE_portAssignment = 16, RULE_portDecl = 17, RULE_portDeclInit = 18, 
		RULE_portstmt = 19, RULE_portName = 20, RULE_portType = 21, RULE_mfCall = 22, 
		RULE_mfName = 23, RULE_mfparams = 24, RULE_expression = 25, RULE_path = 26, 
		RULE_pathIndex = 27, RULE_expr = 28;
	public static final String[] ruleNames = {
		"shiro", "useStatement", "statement", "statestmt", "stateHeader", "stateGraphStmt", 
		"stateName", "stateGraph", "nodestmt", "nodeInternal", "subjunctDeclNodeProd", 
		"graphDecl", "graphLine", "activeSelector", "nodeProduction", "activation", 
		"portAssignment", "portDecl", "portDeclInit", "portstmt", "portName", 
		"portType", "mfCall", "mfName", "mfparams", "expression", "path", "pathIndex", 
		"expr"
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
		public UseStatementContext useStatement(int i) {
			return getRuleContext(UseStatementContext.class,i);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<UseStatementContext> useStatement() {
			return getRuleContexts(UseStatementContext.class);
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
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==USE) {
				{
				{
				setState(58); useStatement();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 2) | (1L << STATE) | (1L << NODE) | (1L << NEWLINE))) != 0)) {
				{
				{
				setState(64); statement();
				}
				}
				setState(69);
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

	public static class UseStatementContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(ShiroParser.NEWLINE, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(ShiroParser.STRING_LITERAL, 0); }
		public TerminalNode USE() { return getToken(ShiroParser.USE, 0); }
		public UseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterUseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitUseStatement(this);
		}
	}

	public final UseStatementContext useStatement() throws RecognitionException {
		UseStatementContext _localctx = new UseStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_useStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70); match(USE);
			setState(71); match(STRING_LITERAL);
			setState(72); match(NEWLINE);
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
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(78);
			switch (_input.LA(1)) {
			case NODE:
				enterOuterAlt(_localctx, 1);
				{
				setState(74); nodestmt();
				}
				break;
			case STATE:
				enterOuterAlt(_localctx, 2);
				{
				setState(75); statestmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 3);
				{
				setState(76); graphDecl();
				}
				break;
			case NEWLINE:
				enterOuterAlt(_localctx, 4);
				{
				setState(77); match(NEWLINE);
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
		enterRule(_localctx, 6, RULE_statestmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); match(STATE);
			setState(81); stateName();
			setState(82); match(BEGIN);
			setState(83); match(NEWLINE);
			setState(84); stateHeader();
			setState(85); match(END);
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
		public List<ActivationContext> activation() {
			return getRuleContexts(ActivationContext.class);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public List<StateGraphStmtContext> stateGraphStmt() {
			return getRuleContexts(StateGraphStmtContext.class);
		}
		public StateGraphStmtContext stateGraphStmt(int i) {
			return getRuleContext(StateGraphStmtContext.class,i);
		}
		public ActivationContext activation(int i) {
			return getRuleContext(ActivationContext.class,i);
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
		enterRule(_localctx, 8, RULE_stateHeader);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(90);
				switch (_input.LA(1)) {
				case 1:
					{
					setState(87); stateGraphStmt();
					}
					break;
				case IDENT:
					{
					setState(88); activation();
					}
					break;
				case NEWLINE:
					{
					setState(89); match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << IDENT) | (1L << NEWLINE))) != 0) );
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
		enterRule(_localctx, 10, RULE_stateGraphStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94); match(1);
			setState(95); stateGraph();
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
		enterRule(_localctx, 12, RULE_stateName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97); match(IDENT);
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
		enterRule(_localctx, 14, RULE_stateGraph);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); match(IDENT);
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
		public TerminalNode NODE() { return getToken(ShiroParser.NODE, 0); }
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
		enterRule(_localctx, 16, RULE_nodestmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); match(NODE);
			setState(102); match(IDENT);
			setState(107);
			_la = _input.LA(1);
			if (_la==LSQUARE) {
				{
				setState(103); match(LSQUARE);
				setState(104); activeSelector();
				setState(105); match(RSQUARE);
				}
			}

			setState(109); match(BEGIN);
			setState(110); match(NEWLINE);
			setState(111); nodeInternal();
			setState(112); match(END);
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
		public List<SubjunctDeclNodeProdContext> subjunctDeclNodeProd() {
			return getRuleContexts(SubjunctDeclNodeProdContext.class);
		}
		public PortstmtContext portstmt(int i) {
			return getRuleContext(PortstmtContext.class,i);
		}
		public PortAssignmentContext portAssignment(int i) {
			return getRuleContext(PortAssignmentContext.class,i);
		}
		public NodeProductionContext nodeProduction(int i) {
			return getRuleContext(NodeProductionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public List<PortAssignmentContext> portAssignment() {
			return getRuleContexts(PortAssignmentContext.class);
		}
		public List<NodestmtContext> nodestmt() {
			return getRuleContexts(NodestmtContext.class);
		}
		public SubjunctDeclNodeProdContext subjunctDeclNodeProd(int i) {
			return getRuleContext(SubjunctDeclNodeProdContext.class,i);
		}
		public NodestmtContext nodestmt(int i) {
			return getRuleContext(NodestmtContext.class,i);
		}
		public List<NodeProductionContext> nodeProduction() {
			return getRuleContexts(NodeProductionContext.class);
		}
		public List<PortstmtContext> portstmt() {
			return getRuleContexts(PortstmtContext.class);
		}
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public List<TerminalNode> OPTION() { return getTokens(ShiroParser.OPTION); }
		public TerminalNode OPTION(int i) {
			return getToken(ShiroParser.OPTION, i);
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
		enterRule(_localctx, 18, RULE_nodeInternal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(123);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(114); portstmt();
					}
					break;

				case 2:
					{
					setState(115); subjunctDeclNodeProd();
					}
					break;

				case 3:
					{
					setState(117);
					_la = _input.LA(1);
					if (_la==OPTION) {
						{
						setState(116); match(OPTION);
						}
					}

					setState(119); nodestmt();
					}
					break;

				case 4:
					{
					setState(120); nodeProduction();
					}
					break;

				case 5:
					{
					setState(121); portAssignment();
					}
					break;

				case 6:
					{
					setState(122); match(NEWLINE);
					}
					break;
				}
				}
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 4) | (1L << 9) | (1L << THIS) | (1L << OPTION) | (1L << NODE) | (1L << IDENT) | (1L << NEWLINE))) != 0) );
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
		public Token type;
		public Token instanceName;
		public PortAssignmentContext portAssignment(int i) {
			return getRuleContext(PortAssignmentContext.class,i);
		}
		public TerminalNode OPTION() { return getToken(ShiroParser.OPTION, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ShiroParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ShiroParser.NEWLINE, i);
		}
		public TerminalNode IDENT(int i) {
			return getToken(ShiroParser.IDENT, i);
		}
		public TerminalNode BEGIN() { return getToken(ShiroParser.BEGIN, 0); }
		public TerminalNode END() { return getToken(ShiroParser.END, 0); }
		public List<PortAssignmentContext> portAssignment() {
			return getRuleContexts(PortAssignmentContext.class);
		}
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
		enterRule(_localctx, 20, RULE_subjunctDeclNodeProd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127); match(OPTION);
			setState(128); ((SubjunctDeclNodeProdContext)_localctx).type = match(IDENT);
			setState(129); match(PROD_OP);
			setState(130); ((SubjunctDeclNodeProdContext)_localctx).instanceName = match(IDENT);
			setState(131); match(BEGIN);
			setState(132); match(NEWLINE);
			setState(135); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(135);
				switch (_input.LA(1)) {
				case THIS:
				case IDENT:
					{
					setState(133); portAssignment();
					}
					break;
				case NEWLINE:
					{
					setState(134); match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THIS) | (1L << IDENT) | (1L << NEWLINE))) != 0) );
			setState(139); match(END);
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
		enterRule(_localctx, 22, RULE_graphDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141); match(2);
			setState(142); match(IDENT);
			setState(143); match(BEGIN);
			setState(144); match(NEWLINE);
			setState(146); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(145); graphLine();
				}
				}
				setState(148); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << THIS) | (1L << IDENT) | (1L << NEWLINE))) != 0) );
			setState(150); match(END);
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
		enterRule(_localctx, 24, RULE_graphLine);
		try {
			setState(155);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152); nodeProduction();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153); portAssignment();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(154); match(NEWLINE);
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
		enterRule(_localctx, 26, RULE_activeSelector);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(IDENT);
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
		enterRule(_localctx, 28, RULE_nodeProduction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159); path();
			setState(162); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(160); match(PROD_OP);
				setState(161); activation();
				}
				}
				setState(164); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PROD_OP );
			setState(166); match(NEWLINE);
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
		enterRule(_localctx, 30, RULE_activation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168); ((ActivationContext)_localctx).nodeName = match(IDENT);
			setState(172);
			_la = _input.LA(1);
			if (_la==LSQUARE) {
				{
				setState(169); match(LSQUARE);
				setState(170); ((ActivationContext)_localctx).activeObject = match(IDENT);
				setState(171); match(RSQUARE);
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
		enterRule(_localctx, 32, RULE_portAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174); path();
			setState(175); match(5);
			setState(176); mfparams();
			setState(177); match(6);
			setState(178); match(NEWLINE);
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
		public TerminalNode OPTION() { return getToken(ShiroParser.OPTION, 0); }
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
		enterRule(_localctx, 34, RULE_portDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_la = _input.LA(1);
			if (_la==OPTION) {
				{
				setState(180); match(OPTION);
				}
			}

			setState(183); portType();
			setState(184); portName();
			setState(185); mfName();
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
		public TerminalNode OPTION() { return getToken(ShiroParser.OPTION, 0); }
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
		enterRule(_localctx, 36, RULE_portDeclInit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_la = _input.LA(1);
			if (_la==OPTION) {
				{
				setState(187); match(OPTION);
				}
			}

			setState(190); portType();
			setState(191); portName();
			setState(192); mfCall();
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
		enterRule(_localctx, 38, RULE_portstmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(194); portDecl();
				}
				break;

			case 2:
				{
				setState(195); portDeclInit();
				}
				break;
			}
			setState(198); match(NEWLINE);
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
		enterRule(_localctx, 40, RULE_portName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(200); match(IDENT);
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
		enterRule(_localctx, 42, RULE_portType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 3) | (1L << 4) | (1L << 9))) != 0)) ) {
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
		enterRule(_localctx, 44, RULE_mfCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204); mfName();
			setState(205); match(5);
			setState(206); mfparams();
			setState(207); match(6);
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
		enterRule(_localctx, 46, RULE_mfName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209); match(IDENT);
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
		enterRule(_localctx, 48, RULE_mfparams);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211); expr(0);
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==7) {
				{
				{
				setState(212); match(7);
				setState(213); expr(0);
				}
				}
				setState(218);
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
		enterRule(_localctx, 50, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219); expr(0);
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
		public TerminalNode THIS() { return getToken(ShiroParser.THIS, 0); }
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
		enterRule(_localctx, 52, RULE_path);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_la = _input.LA(1);
			if ( !(_la==THIS || _la==IDENT) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(226);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(222); match(8);
					setState(223); match(IDENT);
					}
					} 
				}
				setState(228);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(233);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(229); match(LSQUARE);
				setState(230); pathIndex();
				setState(231); match(RSQUARE);
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
		enterRule(_localctx, 54, RULE_pathIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
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
	public static class StringExpContext extends ExprContext {
		public TerminalNode STRING_LITERAL() { return getToken(ShiroParser.STRING_LITERAL, 0); }
		public StringExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).enterStringExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShiroListener ) ((ShiroListener)listener).exitStringExp(this);
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
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			switch (_input.LA(1)) {
			case THIS:
			case IDENT:
				{
				_localctx = new PathExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(238); path();
				}
				break;
			case NUMBER:
				{
				_localctx = new NumberExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(239); match(NUMBER);
				}
				break;
			case STRING_LITERAL:
				{
				_localctx = new StringExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(240); match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(254);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(252);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new OrExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(243);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(244); match(OR_OP);
						setState(245); expr(7);
						}
						break;

					case 2:
						{
						_localctx = new MultExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(247);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MULT_OP) | (1L << DIV_OP) | (1L << MOD_OP))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(248); expr(6);
						}
						break;

					case 3:
						{
						_localctx = new AddExpContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(249);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(250);
						_la = _input.LA(1);
						if ( !(_la==PLUS_OP || _la==MINUS_OP) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(251); expr(5);
						}
						break;
					}
					} 
				}
				setState(256);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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
		case 28: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 6);

		case 1: return precpred(_ctx, 5);

		case 2: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3%\u0104\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\7\2>\n\2\f\2\16"+
		"\2A\13\2\3\2\7\2D\n\2\f\2\16\2G\13\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5"+
		"\4Q\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\6\6]\n\6\r\6\16\6^\3\7"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\nn\n\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\13\3\13\3\13\5\13x\n\13\3\13\3\13\3\13\3\13\6\13~\n\13\r\13"+
		"\16\13\177\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u008a\n\f\r\f\16\f\u008b"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\3\r\6\r\u0095\n\r\r\r\16\r\u0096\3\r\3\r\3\16"+
		"\3\16\3\16\5\16\u009e\n\16\3\17\3\17\3\20\3\20\3\20\6\20\u00a5\n\20\r"+
		"\20\16\20\u00a6\3\20\3\20\3\21\3\21\3\21\3\21\5\21\u00af\n\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\5\23\u00b8\n\23\3\23\3\23\3\23\3\23\3\24\5\24"+
		"\u00bf\n\24\3\24\3\24\3\24\3\24\3\25\3\25\5\25\u00c7\n\25\3\25\3\25\3"+
		"\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\32\7"+
		"\32\u00d9\n\32\f\32\16\32\u00dc\13\32\3\33\3\33\3\34\3\34\3\34\7\34\u00e3"+
		"\n\34\f\34\16\34\u00e6\13\34\3\34\3\34\3\34\3\34\5\34\u00ec\n\34\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\5\36\u00f4\n\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\7\36\u00ff\n\36\f\36\16\36\u0102\13\36\3\36\2\3:\37\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\7\4\2\5"+
		"\6\13\13\4\2\r\r!!\3\2\37 \3\2\32\34\3\2\30\31\u0108\2?\3\2\2\2\4H\3\2"+
		"\2\2\6P\3\2\2\2\bR\3\2\2\2\n\\\3\2\2\2\f`\3\2\2\2\16c\3\2\2\2\20e\3\2"+
		"\2\2\22g\3\2\2\2\24}\3\2\2\2\26\u0081\3\2\2\2\30\u008f\3\2\2\2\32\u009d"+
		"\3\2\2\2\34\u009f\3\2\2\2\36\u00a1\3\2\2\2 \u00aa\3\2\2\2\"\u00b0\3\2"+
		"\2\2$\u00b7\3\2\2\2&\u00be\3\2\2\2(\u00c6\3\2\2\2*\u00ca\3\2\2\2,\u00cc"+
		"\3\2\2\2.\u00ce\3\2\2\2\60\u00d3\3\2\2\2\62\u00d5\3\2\2\2\64\u00dd\3\2"+
		"\2\2\66\u00df\3\2\2\28\u00ed\3\2\2\2:\u00f3\3\2\2\2<>\5\4\3\2=<\3\2\2"+
		"\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@E\3\2\2\2A?\3\2\2\2BD\5\6\4\2CB\3\2\2"+
		"\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\3\3\2\2\2GE\3\2\2\2HI\7\f\2\2IJ\7\37"+
		"\2\2JK\7%\2\2K\5\3\2\2\2LQ\5\22\n\2MQ\5\b\5\2NQ\5\30\r\2OQ\7%\2\2PL\3"+
		"\2\2\2PM\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\7\3\2\2\2RS\7\21\2\2ST\5\16\b\2"+
		"TU\7\25\2\2UV\7%\2\2VW\5\n\6\2WX\7\26\2\2X\t\3\2\2\2Y]\5\f\7\2Z]\5 \21"+
		"\2[]\7%\2\2\\Y\3\2\2\2\\Z\3\2\2\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2^_\3"+
		"\2\2\2_\13\3\2\2\2`a\7\3\2\2ab\5\20\t\2b\r\3\2\2\2cd\7!\2\2d\17\3\2\2"+
		"\2ef\7!\2\2f\21\3\2\2\2gh\7\23\2\2hm\7!\2\2ij\7\35\2\2jk\5\34\17\2kl\7"+
		"\36\2\2ln\3\2\2\2mi\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7\25\2\2pq\7%\2\2qr"+
		"\5\24\13\2rs\7\26\2\2s\23\3\2\2\2t~\5(\25\2u~\5\26\f\2vx\7\16\2\2wv\3"+
		"\2\2\2wx\3\2\2\2xy\3\2\2\2y~\5\22\n\2z~\5\36\20\2{~\5\"\22\2|~\7%\2\2"+
		"}t\3\2\2\2}u\3\2\2\2}w\3\2\2\2}z\3\2\2\2}{\3\2\2\2}|\3\2\2\2~\177\3\2"+
		"\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\25\3\2\2\2\u0081\u0082\7\16"+
		"\2\2\u0082\u0083\7!\2\2\u0083\u0084\7\17\2\2\u0084\u0085\7!\2\2\u0085"+
		"\u0086\7\25\2\2\u0086\u0089\7%\2\2\u0087\u008a\5\"\22\2\u0088\u008a\7"+
		"%\2\2\u0089\u0087\3\2\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\7\26"+
		"\2\2\u008e\27\3\2\2\2\u008f\u0090\7\4\2\2\u0090\u0091\7!\2\2\u0091\u0092"+
		"\7\25\2\2\u0092\u0094\7%\2\2\u0093\u0095\5\32\16\2\u0094\u0093\3\2\2\2"+
		"\u0095\u0096\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\u0099\7\26\2\2\u0099\31\3\2\2\2\u009a\u009e\5\36\20\2\u009b"+
		"\u009e\5\"\22\2\u009c\u009e\7%\2\2\u009d\u009a\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009d\u009c\3\2\2\2\u009e\33\3\2\2\2\u009f\u00a0\7!\2\2\u00a0\35"+
		"\3\2\2\2\u00a1\u00a4\5\66\34\2\u00a2\u00a3\7\17\2\2\u00a3\u00a5\5 \21"+
		"\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7%\2\2\u00a9\37\3\2\2\2\u00aa"+
		"\u00ae\7!\2\2\u00ab\u00ac\7\35\2\2\u00ac\u00ad\7!\2\2\u00ad\u00af\7\36"+
		"\2\2\u00ae\u00ab\3\2\2\2\u00ae\u00af\3\2\2\2\u00af!\3\2\2\2\u00b0\u00b1"+
		"\5\66\34\2\u00b1\u00b2\7\7\2\2\u00b2\u00b3\5\62\32\2\u00b3\u00b4\7\b\2"+
		"\2\u00b4\u00b5\7%\2\2\u00b5#\3\2\2\2\u00b6\u00b8\7\16\2\2\u00b7\u00b6"+
		"\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\5,\27\2\u00ba"+
		"\u00bb\5*\26\2\u00bb\u00bc\5\60\31\2\u00bc%\3\2\2\2\u00bd\u00bf\7\16\2"+
		"\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1"+
		"\5,\27\2\u00c1\u00c2\5*\26\2\u00c2\u00c3\5.\30\2\u00c3\'\3\2\2\2\u00c4"+
		"\u00c7\5$\23\2\u00c5\u00c7\5&\24\2\u00c6\u00c4\3\2\2\2\u00c6\u00c5\3\2"+
		"\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c9\7%\2\2\u00c9)\3\2\2\2\u00ca\u00cb"+
		"\7!\2\2\u00cb+\3\2\2\2\u00cc\u00cd\t\2\2\2\u00cd-\3\2\2\2\u00ce\u00cf"+
		"\5\60\31\2\u00cf\u00d0\7\7\2\2\u00d0\u00d1\5\62\32\2\u00d1\u00d2\7\b\2"+
		"\2\u00d2/\3\2\2\2\u00d3\u00d4\7!\2\2\u00d4\61\3\2\2\2\u00d5\u00da\5:\36"+
		"\2\u00d6\u00d7\7\t\2\2\u00d7\u00d9\5:\36\2\u00d8\u00d6\3\2\2\2\u00d9\u00dc"+
		"\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\63\3\2\2\2\u00dc"+
		"\u00da\3\2\2\2\u00dd\u00de\5:\36\2\u00de\65\3\2\2\2\u00df\u00e4\t\3\2"+
		"\2\u00e0\u00e1\7\n\2\2\u00e1\u00e3\7!\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e6"+
		"\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00eb\3\2\2\2\u00e6"+
		"\u00e4\3\2\2\2\u00e7\u00e8\7\35\2\2\u00e8\u00e9\58\35\2\u00e9\u00ea\7"+
		"\36\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00e7\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\67\3\2\2\2\u00ed\u00ee\t\4\2\2\u00ee9\3\2\2\2\u00ef\u00f0\b\36\1\2\u00f0"+
		"\u00f4\5\66\34\2\u00f1\u00f4\7 \2\2\u00f2\u00f4\7\37\2\2\u00f3\u00ef\3"+
		"\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f2\3\2\2\2\u00f4\u0100\3\2\2\2\u00f5"+
		"\u00f6\f\b\2\2\u00f6\u00f7\7\27\2\2\u00f7\u00ff\5:\36\t\u00f8\u00f9\f"+
		"\7\2\2\u00f9\u00fa\t\5\2\2\u00fa\u00ff\5:\36\b\u00fb\u00fc\f\6\2\2\u00fc"+
		"\u00fd\t\6\2\2\u00fd\u00ff\5:\36\7\u00fe\u00f5\3\2\2\2\u00fe\u00f8\3\2"+
		"\2\2\u00fe\u00fb\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101;\3\2\2\2\u0102\u0100\3\2\2\2\32?EP\\^mw}\177\u0089"+
		"\u008b\u0096\u009d\u00a6\u00ae\u00b7\u00be\u00c6\u00da\u00e4\u00eb\u00f3"+
		"\u00fe\u0100";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}