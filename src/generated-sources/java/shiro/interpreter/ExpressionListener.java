// Generated from /Users/jeffreyguenther/Development/shiro/src/main/java/shiro/interpreter/Expression.g4 by ANTLR 4.1
package shiro.interpreter;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParser}.
 */
public interface ExpressionListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParser#OrExp}.
	 * @param ctx the parse tree
	 */
	void enterOrExp(@NotNull ExpressionParser.OrExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#OrExp}.
	 * @param ctx the parse tree
	 */
	void exitOrExp(@NotNull ExpressionParser.OrExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull ExpressionParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull ExpressionParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExpressionParser#MultExp}.
	 * @param ctx the parse tree
	 */
	void enterMultExp(@NotNull ExpressionParser.MultExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#MultExp}.
	 * @param ctx the parse tree
	 */
	void exitMultExp(@NotNull ExpressionParser.MultExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExpressionParser#NumberExp}.
	 * @param ctx the parse tree
	 */
	void enterNumberExp(@NotNull ExpressionParser.NumberExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#NumberExp}.
	 * @param ctx the parse tree
	 */
	void exitNumberExp(@NotNull ExpressionParser.NumberExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExpressionParser#pathIndex}.
	 * @param ctx the parse tree
	 */
	void enterPathIndex(@NotNull ExpressionParser.PathIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#pathIndex}.
	 * @param ctx the parse tree
	 */
	void exitPathIndex(@NotNull ExpressionParser.PathIndexContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExpressionParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(@NotNull ExpressionParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(@NotNull ExpressionParser.PathContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExpressionParser#AddExp}.
	 * @param ctx the parse tree
	 */
	void enterAddExp(@NotNull ExpressionParser.AddExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#AddExp}.
	 * @param ctx the parse tree
	 */
	void exitAddExp(@NotNull ExpressionParser.AddExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link ExpressionParser#PathExp}.
	 * @param ctx the parse tree
	 */
	void enterPathExp(@NotNull ExpressionParser.PathExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParser#PathExp}.
	 * @param ctx the parse tree
	 */
	void exitPathExp(@NotNull ExpressionParser.PathExpContext ctx);
}