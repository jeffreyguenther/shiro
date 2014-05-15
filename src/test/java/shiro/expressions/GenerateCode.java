package shiro.expressions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import shiro.Node;
import shiro.PathNotFoundException;
import shiro.Port;
import shiro.definitions.PortType;
import shiro.SubjunctiveParametricSystem;
import shiro.drawing.ProjectAmoebaController;
import shiro.functions.ValueMFunc;

/**
 *
 * @author jeffreyguenther
 */
public class GenerateCode {
    @Test
    public void outputNumber(){
       Number n = new Number(12.3);
       Assert.assertEquals("should match", "12.3", n.toCode());
    }
    
    @Test
    public void outputPath(){
        List<String> l = new ArrayList<>();
        l.add("x");
        Path intIndex = new Path(null, l, 0);
        Assert.assertEquals("should match", "x[0]", intIndex.toCode());
        
        Path stringIndex = new Path(null, l, "point");
        Assert.assertEquals("should match", "x[\"point\"]", stringIndex.toCode());
    }
    
    @Test
    public void outputSimpleAdd(){
        Number n = new Number(12.3);
        Number n2 = new Number (235.3);
        Add add = new Add(n, n2);
        Assert.assertEquals("both args should match", "12.3 + 235.3", add.toCode());
    }
    
    @Test
    public void outputSimpleSubtract(){
        Number n = new Number(12.3);
        Number n2 = new Number (235.3);
        Subtract sub = new Subtract(n, n2);
        Assert.assertEquals("both args should match", "12.3 - 235.3", sub.toCode());
    }
    
    @Test
    public void outputSimpleDivide(){
        Number n = new Number(12.3);
        Number n2 = new Number (235.3);
        Divide div = new Divide(n, n2);
        Assert.assertEquals("both args should match", "12.3 / 235.3", div.toCode());
    }
    
    @Test
    public void outputSimpleMultiply(){
        Number n = new Number(12.3);
        Number n2 = new Number (235.3);
        Multiply mult = new Multiply(n, n2);
        Assert.assertEquals("should match", "12.3 * 235.3", mult.toCode());
    }
    
    @Test
    public void outputPort(){
        Port p = new Port("Point.x", new ValueMFunc());
        Assert.assertEquals("should match", "port x Value", p.toCode());
        
        Number n = new Number(12.3);
        Number n2 = new Number (235.3);
        Multiply mult = new Multiply(n, n2);
        List<Expression> exps = new ArrayList<>();
        exps.add(mult);
        
        Port p2 = new Port("Point.point", exps, new ValueMFunc());
        Assert.assertEquals("should match", "port point Value(12.3 * 235.3)", p2.toCode());
        
        Port p3 = new Port("Point.update", exps, new ValueMFunc());
        p3.setPortType(PortType.Evaluated);
        Assert.assertEquals("should match", "eval update Value(12.3 * 235.3)", p3.toCode());
    }
    
    @Test
    public void intializeEditGenerateCode() throws IOException{
        SubjunctiveParametricSystem pSystem = new SubjunctiveParametricSystem();
        pSystem.loadDefaultDefinitions();
        Node p1 = createPoint(pSystem, 100, 50);
        Node p2 = createPoint(pSystem, 200, 50);
        createLine(pSystem, p1, p2);
        
        pSystem.writeCode(new File("/Users/jeffreyguenther/Desktop/intializeEdit.sro"));
    }
    
    private Node createPoint(SubjunctiveParametricSystem model, double x, double y){
        shiro.Node node = model.createNode("Point");
        Path pathX = Path.createPathForPort(node, "x");
        Path pathY = Path.createPathForPort(node, "y");

        // Parse the expression into an Expression object
        Expression xExpr = model.parseExpression(node, x + "");
        Expression yExpr = model.parseExpression(node, y + "");

//        try {
//            model.setPortExpression(pathX, xExpr);
//            model.setPortExpression(pathY, yExpr);
//
//        } catch (PathNotFoundException ex) {
//            Logger.getLogger(ProjectAmoebaController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return node;
    }
    
    private Node createLine(SubjunctiveParametricSystem model, Node p1, Node p2){
        shiro.Node node = model.createNode("Line");
        Path pathX = Path.createPathForPort(node, "p1");
        Path pathY = Path.createPathForPort(node, "p2");
        
        // Parse the expression into an Expression object
        Expression p1Expr = model.parseExpression(p1, p1.getName() + ".point[0]");
        Expression p2Expr = model.parseExpression(p2, p2.getName() + ".point[0]");

//        try {
//            model.setPortExpression(pathX, p1Expr);
//            model.setPortExpression(pathY, p2Expr);
//
//        } catch (PathNotFoundException ex) {
//            Logger.getLogger(ProjectAmoebaController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return node;
    }
}
