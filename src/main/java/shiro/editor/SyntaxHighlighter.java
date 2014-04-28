//package shiro.editor;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import shiro.interpreter.ShiroBaseListener;
//import shiro.interpreter.ShiroParser.GraphDeclContext;
//import shiro.interpreter.ShiroParser.MfCallContext;
//import shiro.interpreter.ShiroParser.NodeProductionContext;
//import shiro.interpreter.ShiroParser.NodestmtContext;
//import shiro.interpreter.ShiroParser.PathContext;
//import shiro.interpreter.ShiroParser.PortDeclContext;
//import shiro.interpreter.ShiroParser.SNodeContext;
//import shiro.interpreter.ShiroParser.SubjunctDeclNodeProdContext;
//
//import org.antlr.v4.runtime.Token;
//import org.antlr.v4.runtime.tree.TerminalNode;
//
//public class SyntaxHighlighter extends ShiroBaseListener {
//	private Set<StyleInfo> styles;
//
//	public SyntaxHighlighter() {
//		styles = new HashSet<StyleInfo>();
//	}
//
//	/**
//	 * @return the styles
//	 */
//	public Set<StyleInfo> getStyles() {
//		return styles;
//	}
//
//	@Override
//	public void enterNodestmt(NodestmtContext ctx) {
//		Token nodeName = ctx.IDENT().getSymbol();
//		styles.add(createIdent(nodeName));
//
//		Token begin = ctx.BEGIN().getSymbol();
//		styles.add(createBeginEnd(begin));
//
//		Token end = ctx.END().getSymbol();
//		styles.add(createBeginEnd(end));
//	}
//
//	@Override
//	public void enterPortDecl(PortDeclContext ctx) {
//		Token mfName = ctx.mfName().IDENT().getSymbol();
//		styles.add(createMF(mfName));
//	}
//
//	@Override
//	public void enterMfCall(MfCallContext ctx) {
//		Token mfName = ctx.mfName().IDENT().getSymbol();
//		styles.add(createMF(mfName));
//	}
//
//	@Override
//	public void enterPath(PathContext ctx) {
//
//		if (ctx.LSQUARE() != null) {
//			Token lsquare = ctx.LSQUARE().getSymbol();
//			styles.add(createSquareBracket(lsquare));
//		}
//
//		if (ctx.RSQUARE() != null) {
//			Token rsquare = ctx.RSQUARE().getSymbol();
//			styles.add(createSquareBracket(rsquare));
//		}
//	}
//
//	@Override
//	public void enterSNode(SNodeContext ctx) {
//		Token name = ctx.nodeName;
//		styles.add(createIdent(name));
//
//		Token begin = ctx.BEGIN().getSymbol();
//		styles.add(createBeginEnd(begin));
//
//		Token end = ctx.END().getSymbol();
//		styles.add(createBeginEnd(end));
//	}
//
//	@Override
//	public void enterSubjunctDeclNodeProd(SubjunctDeclNodeProdContext ctx) {
//		Token begin = ctx.BEGIN().getSymbol();
//		styles.add(createBeginEnd(begin));
//
//		Token end = ctx.END().getSymbol();
//		styles.add(createBeginEnd(end));
//
//		Token node = ctx.nodeName;
//		styles.add(createIdent(node));
//
//		node = ctx.newName;
//		styles.add(createIdent(node));
//	}
//
//	@Override
//	public void enterGraphDecl(GraphDeclContext ctx) {
//		Token begin = ctx.BEGIN().getSymbol();
//		styles.add(createBeginEnd(begin));
//
//		Token end = ctx.END().getSymbol();
//		styles.add(createBeginEnd(end));
//
//		Token node = ctx.IDENT().getSymbol();
//		styles.add(createIdent(node));
//	}
//
//	@Override
//	public void enterNodeProduction(NodeProductionContext ctx) {
//		PathContext path = ctx.path();
//
//		if (!path.IDENT().isEmpty()) {
//			for (TerminalNode ident : path.IDENT()) {
//				Token t = ident.getSymbol();
//				styles.add(createIdent(t));
//			}
//		}
//
//		if (!ctx.PROD_OP().isEmpty()) {
//			for (TerminalNode prodOp : ctx.PROD_OP()) {
//				Token t = prodOp.getSymbol();
//				styles.add(createProd(t));
//			}
//		}
//	}
//
//	private StyleInfo createIdent(Token ident) {
//		return new StyleInfo(ident.getStartIndex(), ident.getStopIndex(),
//				"ident");
//	}
//
//	private StyleInfo createBeginEnd(Token beginEnd) {
//		return new StyleInfo(beginEnd.getStartIndex(), beginEnd.getStopIndex(),
//				"begin-end");
//	}
//
//	private StyleInfo createMF(Token mfName) {
//		return new StyleInfo(mfName.getStartIndex(), mfName.getStopIndex(),
//				"mf");
//	}
//
//	private StyleInfo createSquareBracket(Token bracket) {
//		return new StyleInfo(bracket.getStartIndex(), bracket.getStopIndex(),
//				"bracket");
//	}
//
//	private StyleInfo createProd(Token production) {
//		return new StyleInfo(production.getStartIndex(),
//				production.getStopIndex(), "production");
//	}
//}