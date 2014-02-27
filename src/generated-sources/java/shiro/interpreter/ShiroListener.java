// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Shiro.g4 by ANTLR 4.2
package shiro.interpreter;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShiroParser}.
 */
public interface ShiroListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShiroParser#stateGraphStmt}.
	 * @param ctx the parse tree
	 */
	void enterStateGraphStmt(@NotNull ShiroParser.StateGraphStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#stateGraphStmt}.
	 * @param ctx the parse tree
	 */
	void exitStateGraphStmt(@NotNull ShiroParser.StateGraphStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#stateParent}.
	 * @param ctx the parse tree
	 */
	void enterStateParent(@NotNull ShiroParser.StateParentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#stateParent}.
	 * @param ctx the parse tree
	 */
	void exitStateParent(@NotNull ShiroParser.StateParentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#mfName}.
	 * @param ctx the parse tree
	 */
	void enterMfName(@NotNull ShiroParser.MfNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#mfName}.
	 * @param ctx the parse tree
	 */
	void exitMfName(@NotNull ShiroParser.MfNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#AddExp}.
	 * @param ctx the parse tree
	 */
	void enterAddExp(@NotNull ShiroParser.AddExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#AddExp}.
	 * @param ctx the parse tree
	 */
	void exitAddExp(@NotNull ShiroParser.AddExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#subjunctSelector}.
	 * @param ctx the parse tree
	 */
	void enterSubjunctSelector(@NotNull ShiroParser.SubjunctSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#subjunctSelector}.
	 * @param ctx the parse tree
	 */
	void exitSubjunctSelector(@NotNull ShiroParser.SubjunctSelectorContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#stateCommentStmt}.
	 * @param ctx the parse tree
	 */
	void enterStateCommentStmt(@NotNull ShiroParser.StateCommentStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#stateCommentStmt}.
	 * @param ctx the parse tree
	 */
	void exitStateCommentStmt(@NotNull ShiroParser.StateCommentStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#portName}.
	 * @param ctx the parse tree
	 */
	void enterPortName(@NotNull ShiroParser.PortNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#portName}.
	 * @param ctx the parse tree
	 */
	void exitPortName(@NotNull ShiroParser.PortNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#nodeProduction}.
	 * @param ctx the parse tree
	 */
	void enterNodeProduction(@NotNull ShiroParser.NodeProductionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#nodeProduction}.
	 * @param ctx the parse tree
	 */
	void exitNodeProduction(@NotNull ShiroParser.NodeProductionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(@NotNull ShiroParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(@NotNull ShiroParser.PathContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#PathExp}.
	 * @param ctx the parse tree
	 */
	void enterPathExp(@NotNull ShiroParser.PathExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#PathExp}.
	 * @param ctx the parse tree
	 */
	void exitPathExp(@NotNull ShiroParser.PathExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#OrExp}.
	 * @param ctx the parse tree
	 */
	void enterOrExp(@NotNull ShiroParser.OrExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#OrExp}.
	 * @param ctx the parse tree
	 */
	void exitOrExp(@NotNull ShiroParser.OrExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#stateName}.
	 * @param ctx the parse tree
	 */
	void enterStateName(@NotNull ShiroParser.StateNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#stateName}.
	 * @param ctx the parse tree
	 */
	void exitStateName(@NotNull ShiroParser.StateNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#stateHeader}.
	 * @param ctx the parse tree
	 */
	void enterStateHeader(@NotNull ShiroParser.StateHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#stateHeader}.
	 * @param ctx the parse tree
	 */
	void exitStateHeader(@NotNull ShiroParser.StateHeaderContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#nodeInternal}.
	 * @param ctx the parse tree
	 */
	void enterNodeInternal(@NotNull ShiroParser.NodeInternalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#nodeInternal}.
	 * @param ctx the parse tree
	 */
	void exitNodeInternal(@NotNull ShiroParser.NodeInternalContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull ShiroParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull ShiroParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#subjunctDeclNodeProd}.
	 * @param ctx the parse tree
	 */
	void enterSubjunctDeclNodeProd(@NotNull ShiroParser.SubjunctDeclNodeProdContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#subjunctDeclNodeProd}.
	 * @param ctx the parse tree
	 */
	void exitSubjunctDeclNodeProd(@NotNull ShiroParser.SubjunctDeclNodeProdContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#mfparams}.
	 * @param ctx the parse tree
	 */
	void enterMfparams(@NotNull ShiroParser.MfparamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#mfparams}.
	 * @param ctx the parse tree
	 */
	void exitMfparams(@NotNull ShiroParser.MfparamsContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#graphDecl}.
	 * @param ctx the parse tree
	 */
	void enterGraphDecl(@NotNull ShiroParser.GraphDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#graphDecl}.
	 * @param ctx the parse tree
	 */
	void exitGraphDecl(@NotNull ShiroParser.GraphDeclContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#stateGraph}.
	 * @param ctx the parse tree
	 */
	void enterStateGraph(@NotNull ShiroParser.StateGraphContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#stateGraph}.
	 * @param ctx the parse tree
	 */
	void exitStateGraph(@NotNull ShiroParser.StateGraphContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#statestmt}.
	 * @param ctx the parse tree
	 */
	void enterStatestmt(@NotNull ShiroParser.StatestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#statestmt}.
	 * @param ctx the parse tree
	 */
	void exitStatestmt(@NotNull ShiroParser.StatestmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull ShiroParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull ShiroParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#MultExp}.
	 * @param ctx the parse tree
	 */
	void enterMultExp(@NotNull ShiroParser.MultExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#MultExp}.
	 * @param ctx the parse tree
	 */
	void exitMultExp(@NotNull ShiroParser.MultExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#graphLine}.
	 * @param ctx the parse tree
	 */
	void enterGraphLine(@NotNull ShiroParser.GraphLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#graphLine}.
	 * @param ctx the parse tree
	 */
	void exitGraphLine(@NotNull ShiroParser.GraphLineContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#pathIndex}.
	 * @param ctx the parse tree
	 */
	void enterPathIndex(@NotNull ShiroParser.PathIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#pathIndex}.
	 * @param ctx the parse tree
	 */
	void exitPathIndex(@NotNull ShiroParser.PathIndexContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#stateParentStmt}.
	 * @param ctx the parse tree
	 */
	void enterStateParentStmt(@NotNull ShiroParser.StateParentStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#stateParentStmt}.
	 * @param ctx the parse tree
	 */
	void exitStateParentStmt(@NotNull ShiroParser.StateParentStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#portDecl}.
	 * @param ctx the parse tree
	 */
	void enterPortDecl(@NotNull ShiroParser.PortDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#portDecl}.
	 * @param ctx the parse tree
	 */
	void exitPortDecl(@NotNull ShiroParser.PortDeclContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#mfCall}.
	 * @param ctx the parse tree
	 */
	void enterMfCall(@NotNull ShiroParser.MfCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#mfCall}.
	 * @param ctx the parse tree
	 */
	void exitMfCall(@NotNull ShiroParser.MfCallContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#nodeType}.
	 * @param ctx the parse tree
	 */
	void enterNodeType(@NotNull ShiroParser.NodeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#nodeType}.
	 * @param ctx the parse tree
	 */
	void exitNodeType(@NotNull ShiroParser.NodeTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#sNode}.
	 * @param ctx the parse tree
	 */
	void enterSNode(@NotNull ShiroParser.SNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#sNode}.
	 * @param ctx the parse tree
	 */
	void exitSNode(@NotNull ShiroParser.SNodeContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#subjunctDecl}.
	 * @param ctx the parse tree
	 */
	void enterSubjunctDecl(@NotNull ShiroParser.SubjunctDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#subjunctDecl}.
	 * @param ctx the parse tree
	 */
	void exitSubjunctDecl(@NotNull ShiroParser.SubjunctDeclContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#portstmt}.
	 * @param ctx the parse tree
	 */
	void enterPortstmt(@NotNull ShiroParser.PortstmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#portstmt}.
	 * @param ctx the parse tree
	 */
	void exitPortstmt(@NotNull ShiroParser.PortstmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#portType}.
	 * @param ctx the parse tree
	 */
	void enterPortType(@NotNull ShiroParser.PortTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#portType}.
	 * @param ctx the parse tree
	 */
	void exitPortType(@NotNull ShiroParser.PortTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#shiro}.
	 * @param ctx the parse tree
	 */
	void enterShiro(@NotNull ShiroParser.ShiroContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#shiro}.
	 * @param ctx the parse tree
	 */
	void exitShiro(@NotNull ShiroParser.ShiroContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#nodestmt}.
	 * @param ctx the parse tree
	 */
	void enterNodestmt(@NotNull ShiroParser.NodestmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#nodestmt}.
	 * @param ctx the parse tree
	 */
	void exitNodestmt(@NotNull ShiroParser.NodestmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#stateTimeStmt}.
	 * @param ctx the parse tree
	 */
	void enterStateTimeStmt(@NotNull ShiroParser.StateTimeStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#stateTimeStmt}.
	 * @param ctx the parse tree
	 */
	void exitStateTimeStmt(@NotNull ShiroParser.StateTimeStmtContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#portDeclInit}.
	 * @param ctx the parse tree
	 */
	void enterPortDeclInit(@NotNull ShiroParser.PortDeclInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#portDeclInit}.
	 * @param ctx the parse tree
	 */
	void exitPortDeclInit(@NotNull ShiroParser.PortDeclInitContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(@NotNull ShiroParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(@NotNull ShiroParser.CommentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#time}.
	 * @param ctx the parse tree
	 */
	void enterTime(@NotNull ShiroParser.TimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#time}.
	 * @param ctx the parse tree
	 */
	void exitTime(@NotNull ShiroParser.TimeContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#activation}.
	 * @param ctx the parse tree
	 */
	void enterActivation(@NotNull ShiroParser.ActivationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#activation}.
	 * @param ctx the parse tree
	 */
	void exitActivation(@NotNull ShiroParser.ActivationContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#portAssignment}.
	 * @param ctx the parse tree
	 */
	void enterPortAssignment(@NotNull ShiroParser.PortAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#portAssignment}.
	 * @param ctx the parse tree
	 */
	void exitPortAssignment(@NotNull ShiroParser.PortAssignmentContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#NumberExp}.
	 * @param ctx the parse tree
	 */
	void enterNumberExp(@NotNull ShiroParser.NumberExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#NumberExp}.
	 * @param ctx the parse tree
	 */
	void exitNumberExp(@NotNull ShiroParser.NumberExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ShiroParser#activeSelector}.
	 * @param ctx the parse tree
	 */
	void enterActiveSelector(@NotNull ShiroParser.ActiveSelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShiroParser#activeSelector}.
	 * @param ctx the parse tree
	 */
	void exitActiveSelector(@NotNull ShiroParser.ActiveSelectorContext ctx);
}