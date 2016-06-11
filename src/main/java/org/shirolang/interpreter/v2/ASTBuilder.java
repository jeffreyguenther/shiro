package org.shirolang.interpreter.v2;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.shirolang.interpreter.ShiroBaseListener;
import org.shirolang.interpreter.ShiroParser;
import org.shirolang.interpreter.ast.*;
import org.shirolang.values.SegmentType;

import java.util.*;
import java.util.stream.Collectors;

import static org.shirolang.interpreter.ast.BinaryOperator.*;
import static org.shirolang.interpreter.ast.UnaryOperator.*;

/**
 * Walks the ParseTree to build the AST
 */
public class ASTBuilder extends ShiroBaseListener {
    private Program p;
    private ParseTreeProperty<Expression> expressions;
    private Stack<OptionSelection> activations;
    private StateDefinition stateDef;
    private GraphDefinition graph = null;
    private Stack<NodeDefinition> nodes;

    public ASTBuilder() {
        p = new Program();
        expressions = new ParseTreeProperty<>();
        activations = new Stack<>();
        nodes = new Stack<>();
    }

    @Override
    public void exitIncludeStmt(ShiroParser.IncludeStmtContext ctx) {
        String importedFile = ctx.STRING_LITERAL().getText().replaceAll("\"", "");
        p.add(new IncludeStatement(importedFile));
    }

    @Override
    public void enterGraphDecl(ShiroParser.GraphDeclContext ctx) {
        GraphDefinition graph = new GraphDefinition(ctx.IDENT().getText());
        this.graph = graph;
        p.add(graph);
    }

    @Override
    public void exitGraphDecl(ShiroParser.GraphDeclContext ctx) {
        this.graph = null;
    }

    @Override
    public void enterNodeDecl(ShiroParser.NodeDeclContext ctx) {
        String name = ctx.MFNAME().getText();
        String defaultOption = "";

        NodeDefinition def;
        if (ctx.optionSelector() != null) {
            defaultOption = ctx.optionSelector().IDENT().getText();
            def = new NodeDefinition(name, defaultOption);
        } else {
            def = new NodeDefinition(name);
        }

        if (nodes.size() >= 1) {
            NodeDefinition parent = nodes.peek();
            parent.add(def);
        }

        nodes.push(def);
    }

    @Override
    public void exitNodeDecl(ShiroParser.NodeDeclContext ctx) {
        if (nodes.size() == 1) {
            p.add(nodes.pop());
        } else {
            nodes.pop();
        }
    }

    @Override
    public void exitPortDecl(ShiroParser.PortDeclContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx.funcDecl().fullyQualifiedType());
        String name = ctx.funcDecl().name.getText();

        String activeOption = "";
        if (ctx.funcDecl().activeObject != null) {
            activeOption = ctx.funcDecl().activeObject.getText();
        }

        String access = "";
        if(ctx.accessModifier() != null){
            access = ctx.accessModifier().getText();
        }

        NodeDefinition node = nodes.peek();

        if (!activeOption.isEmpty()) {
            node.add(new PortDefinition(ctx.OPTION() != null, determineAccess(access), new FunctionDefinition(type, name, activeOption)));
        } else {
            node.add(new PortDefinition(ctx.OPTION() != null, determineAccess(access), new FunctionDefinition(type, name)));
        }
    }

    @Override
    public void exitPortDeclInit(ShiroParser.PortDeclInitContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx.funcDeclInit().fullyQualifiedType());
        String name = ctx.funcDeclInit().name.getText();

        String activeOption = "";
        if (ctx.funcDeclInit().activeObject != null) {
            activeOption = ctx.funcDeclInit().activeObject.getText();
        }

        String access = "";
        if(ctx.accessModifier() != null){
            access = ctx.accessModifier().getText();
        }

        List<Expression> arglist = new ArrayList<>();
        Map<String, Expression> argMap = new HashMap<>();

        if (ctx.funcDeclInit().arguments() != null) {
            if (ctx.funcDeclInit().arguments().argList() != null) {
                arglist.addAll(create(ctx.funcDeclInit().arguments().argList()));
            } else if (ctx.funcDeclInit().arguments().argMap() != null) {
                argMap.putAll(create(ctx.funcDeclInit().arguments().argMap()));
            }
        }

        NodeDefinition node = nodes.peek();

        if (!arglist.isEmpty()) {
            if (!activeOption.isEmpty()) {
                node.add(new PortDefinition(ctx.OPTION() != null, determineAccess(access), new FunctionDefinition(type, name, activeOption, arglist)));
            } else {
                node.add(new PortDefinition(ctx.OPTION() != null, determineAccess(access), new FunctionDefinition(type, name, arglist)));
            }
        } else if (!argMap.isEmpty()) {
            if (!activeOption.isEmpty()) {
                node.add(new PortDefinition(ctx.OPTION() != null, determineAccess(access), new FunctionDefinition(type, name, activeOption, argMap)));
            } else {
                node.add(new PortDefinition(ctx.OPTION() != null, determineAccess(access), new FunctionDefinition(type, name, argMap)));
            }
        } else {
            if (!activeOption.isEmpty()) {
                node.add(new PortDefinition(ctx.OPTION() != null, determineAccess(access), new FunctionDefinition(type, name, activeOption)));
            } else {
                node.add(new PortDefinition(ctx.OPTION() != null, determineAccess(access), new FunctionDefinition(type, name)));
            }
        }
    }

    @Override
    public void exitPortAssignment(ShiroParser.PortAssignmentContext ctx) {
        List<Expression> argList = new ArrayList<>();
        Map<String, Expression> argMap = new HashMap<>();

        if (ctx.arguments() != null) {
            if (ctx.arguments().argList() != null) {
                argList.addAll(create(ctx.arguments().argList()));
            } else if (ctx.arguments().argMap() != null) {
                argMap.putAll(create(ctx.arguments().argMap()));
            }
        }

        Path path = createPath(ctx.path());

        if (inGraph()) {
            if (!argList.isEmpty()) {
                graph.add(new PortAssignment(path, argList));
            } else if (!argMap.isEmpty()) {
                graph.add(new PortAssignment(path, argMap));
            }
        } else if (inNode()) {
            NodeDefinition n = nodes.peek();
            if (!argList.isEmpty()) {
                n.add(new PortAssignment(path, argList));
            } else if (!argMap.isEmpty()) {
                n.add(new PortAssignment(path, argMap));
            }
        }
    }

    @Override
    public void exitFuncDeclInit(ShiroParser.FuncDeclInitContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx.fullyQualifiedType());
        String name = ctx.name.getText();

        String activeOption = "";

        if (ctx.activeObject != null) {
            activeOption = ctx.activeObject.getText();
        }

        List<Expression> arglist = new ArrayList<>();
        Map<String, Expression> argMap = new HashMap<>();

        if (ctx.arguments() != null) {
            if (ctx.arguments().argList() != null) {
                arglist.addAll(create(ctx.arguments().argList()));
            } else if (ctx.arguments().argMap() != null) {
                argMap.putAll(create(ctx.arguments().argMap()));
            }
        }

        if (inGraph()) {
            if (!arglist.isEmpty()) {
                if (!activeOption.isEmpty()) {
                    graph.add(new FunctionDefinition(type, name, activeOption, arglist));
                } else {
                    graph.add(new FunctionDefinition(type, name, arglist));
                }
            } else if (!argMap.isEmpty()) {
                if (!activeOption.isEmpty()) {
                    graph.add(new FunctionDefinition(type, name, activeOption, argMap));
                } else {
                    graph.add(new FunctionDefinition(type, name, argMap));
                }
            } else {
                if (!activeOption.isEmpty()) {
                    graph.add(new FunctionDefinition(type, name, activeOption));
                } else {
                    graph.add(new FunctionDefinition(type, name));
                }
            }
        }
    }

    @Override
    public void exitFuncDecl(ShiroParser.FuncDeclContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx.fullyQualifiedType());
        String name = ctx.name.getText();

        String activeOption = "";

        if (ctx.activeObject != null) {
            activeOption = ctx.activeObject.getText();
        }

        if (inGraph()) {
            if (!activeOption.isEmpty()) {
                graph.add(new FunctionDefinition(type, name, activeOption));
            } else {
                graph.add(new FunctionDefinition(type, name));
            }
        }
    }

    @Override
    public void enterStateDecl(ShiroParser.StateDeclContext ctx) {
        String name = ctx.stateName().getText();
        String graph = "";
        if (ctx.stateGraphSelection().IDENT() != null) {
            graph = ctx.stateGraphSelection().IDENT().getText();
        }

        stateDef = new StateDefinition(name, graph);
        p.add(stateDef);
    }

    @Override
    public void exitOptionSelection(ShiroParser.OptionSelectionContext ctx) {
        OptionSelection activation = new OptionSelection(ctx.nodeName.getText(), ctx.activeObject.getText());

        if (activations.empty()) {
            stateDef.getOptions().add(activation);
        } else {
            OptionSelection parent = activations.peek();
            parent.getSelections().add(activation);
        }
    }

    @Override
    public void enterNestedOptionSelection(ShiroParser.NestedOptionSelectionContext ctx) {
        OptionSelection activation = new OptionSelection(ctx.nodeName.getText(), ctx.activeObject.getText());

        if (activations.empty()) {
            stateDef.getOptions().add(activation);
        } else {
            OptionSelection parent = activations.peek();
            parent.getSelections().add(activation);
        }
        activations.push(activation);
    }

    @Override
    public void exitNestedOptionSelection(ShiroParser.NestedOptionSelectionContext ctx) {
        activations.pop();
    }

    @Override
    public void enterAnonymousGraphStmt(ShiroParser.AnonymousGraphStmtContext ctx) {
        if(!p.hasDefaultGraph()){
            GraphDefinition graph = new GraphDefinition();
            this.graph = graph;
            p.add(graph);
        }else{
            this.graph = p.getDefaultGraph();
        }
    }

    @Override
    public void exitAnonExpr(ShiroParser.AnonExprContext ctx) {
        p.getDefaultGraph().add(get(ctx.expr()));
    }

    @Override
    public void exitPath(ShiroParser.PathContext ctx) {
        expressions.put(ctx, createPathExpression(ctx));
    }

    @Override
    public void exitAddExpr(ShiroParser.AddExprContext ctx) {
        BinaryOperator op;

        if (ctx.MINUS_OP() != null) {
            op = BinaryOperator.SUBTRACT;
        } else {
            op = BinaryOperator.ADD;
        }

        expressions.put(ctx, new BinaryOperation(get(ctx.expr(0)), op, get(ctx.expr(1))));
    }

    @Override
    public void exitMultExpr(ShiroParser.MultExprContext ctx) {
        BinaryOperator op;

        if (ctx.MULT_OP() != null) {
            op = MULTIPLY;
        } else if (ctx.DIV_OP() != null) {
            op = DIVIDE;
        } else {
            op = BinaryOperator.MOD;
        }

        expressions.put(ctx, new BinaryOperation(get(ctx.expr(0)), op, get(ctx.expr(1))));
    }

    @Override
    public void exitAndExpr(ShiroParser.AndExprContext ctx) {
        BinaryOperator op = BinaryOperator.AND;
        expressions.put(ctx, new BinaryOperation(get(ctx.expr(0)), op, get(ctx.expr(1))));
    }

    @Override
    public void exitOrExpr(ShiroParser.OrExprContext ctx) {
        BinaryOperator op = BinaryOperator.OR;
        expressions.put(ctx, new BinaryOperation(get(ctx.expr(0)), op, get(ctx.expr(1))));
    }

    @Override
    public void exitComparisonExpr(ShiroParser.ComparisonExprContext ctx) {
        BinaryOperator op;

        if (ctx.GT() != null) {
            op = GREATER_THAN;
        } else if (ctx.GTE() != null) {
            op = GREATER_THAN_OR_EQUAL;
        } else if (ctx.LT() != null) {
            op = LESS_THAN;
        } else {
            op = LESS_THAN_OR_EQUAL;
        }

        expressions.put(ctx, new BinaryOperation(get(ctx.expr(0)), op, get(ctx.expr(1))));
    }

    @Override
    public void exitEqualityExpr(ShiroParser.EqualityExprContext ctx) {
        BinaryOperator op;

        if (ctx.EQ() != null) {
            op = EQUAL;
        } else {
            op = NOT_EQUAL;
        }

        expressions.put(ctx, new BinaryOperation(get(ctx.expr(0)), op, get(ctx.expr(1))));
    }

    @Override
    public void exitParensExpr(ShiroParser.ParensExprContext ctx) {
        expressions.put(ctx, new UnaryOperation(PARENS, get(ctx.expr())));
    }

    @Override
    public void exitNotExpr(ShiroParser.NotExprContext ctx) {
        expressions.put(ctx, new UnaryOperation(NOT, get(ctx.expr())));
    }

    @Override
    public void exitNegExpr(ShiroParser.NegExprContext ctx) {
        expressions.put(ctx, new UnaryOperation(NEGATE, get(ctx.expr())));
    }

    @Override
    public void exitTypeExpr(ShiroParser.TypeExprContext ctx) {
        expressions.put(ctx, createFullyQualifiedType(ctx.fullyQualifiedType()));
    }

    @Override
    public void exitInlineFuncCall(ShiroParser.InlineFuncCallContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx.funcCall().fullyQualifiedType());

        String activeOption = "";

        if (ctx.funcCall().activeObject != null) {
            activeOption = ctx.funcCall().activeObject.getText();
        }

        List<Expression> arglist = new ArrayList<>();
        Map<String, Expression> argMap = new HashMap<>();

        if (ctx.funcCall().arguments() != null) {
            if (ctx.funcCall().arguments().argList() != null) {
                arglist.addAll(create(ctx.funcCall().arguments().argList()));
            } else if (ctx.funcCall().arguments().argMap() != null) {
                argMap.putAll(create(ctx.funcCall().arguments().argMap()));
            }
        }

        if (!arglist.isEmpty()) {
            if (!activeOption.isEmpty()) {
                expressions.put(ctx, new FunctionCall(type, activeOption, arglist));
            } else {
                expressions.put(ctx, new FunctionCall(type, arglist));
            }
        } else if (!argMap.isEmpty()) {
            if (!activeOption.isEmpty()) {
                expressions.put(ctx, new FunctionCall(type, activeOption, argMap));
            } else {
                expressions.put(ctx, new FunctionCall(type, argMap));
            }
        } else {
            if (!activeOption.isEmpty()) {
                expressions.put(ctx, new FunctionCall(type, activeOption));
            } else {
                expressions.put(ctx, new FunctionCall(type));
            }
        }
    }

    @Override
    public void exitAnonRefExpr(ShiroParser.AnonRefExprContext ctx) {
        expressions.put(ctx, create(ctx.anonymousRef().reference()));
    }

    @Override
    public void exitStringExpr(ShiroParser.StringExprContext ctx) {
        String literalString = ctx.STRING_LITERAL().getText();

        // Remove the quotes from around the string literal
        String value = literalString.substring(1, literalString.length() - 1);
        expressions.put(ctx, Literal.asString(value));
    }

    @Override
    public void exitNumExpr(ShiroParser.NumExprContext ctx) {
        String number = ctx.NUMBER().getText();

        Expression expr;
        // Detect if the number is a double
        if (number.contains(".")) {
            expr = Literal.asDouble(Double.parseDouble(number));
        } else {
            expr = Literal.asInteger(Integer.parseInt(number));
        }

        expressions.put(ctx, expr);
    }

    @Override
    public void exitBoolExpr(ShiroParser.BoolExprContext ctx) {
        String value = ctx.BOOLEAN_LITERAL().getText();
        expressions.put(ctx, Literal.asBoolean(Boolean.parseBoolean(value)));
    }

    private Expression createPathExpression(ShiroParser.PathContext ctx) {
        return Literal.asPath(createPath(ctx));
    }

    private Path createPath(ShiroParser.PathContext ctx) {
        Path p = new Path();
        ctx.segments.stream().map(this::createSegment).forEach(p::addSegment);

        if (ctx.REF() != null) {
            p.makeReference();
        }

        if (ctx.SELECT() != null) {
            p.makeSelector();
        }

        return p;
    }

    private PathSegment createSegment(ShiroParser.PathSegmentContext ctx) {
        if (ctx.IDENT() != null) {
            return new PathSegment(ctx.IDENT().getText());
        }

        if (ctx.INPUTS() != null) {
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();
            if (pathIndex.index.getType() == ShiroParser.NUMBER) {
                int i = Integer.parseInt(pathIndex.NUMBER().getText());
                return new PathSegment(SegmentType.INPUT, i);
            } else if (pathIndex.index.getType() == ShiroParser.IDENT) {
                String key = pathIndex.IDENT().getText();
                return new PathSegment(SegmentType.INPUT, key);
            }
        }

        if (ctx.OUTPUTS() != null) {
            ShiroParser.PathIndexContext pathIndex = ctx.pathIndex();
            if (pathIndex.index.getType() == ShiroParser.NUMBER) {
                int i = Integer.parseInt(pathIndex.NUMBER().getText());
                return new PathSegment(SegmentType.OUTPUT, i);
            } else if (pathIndex.index.getType() == ShiroParser.IDENT) {
                String key = pathIndex.IDENT().getText();
                return new PathSegment(SegmentType.OUTPUT, key);
            }
        }

        return null; // should never reach here.
    }

    private Reference create(ShiroParser.ReferenceContext ctx) {
        Reference r;

        String type = convertFullyQualifiedTypeToString(ctx.fullyQualifiedType());
        String activeOption = "";
        String outputSelector = "";

        List<Expression> arglist = new ArrayList<>();
        Map<String, Expression> argMap = new HashMap<>();

        if (ctx.activeObject != null) {
            activeOption = ctx.activeObject.getText();
        }

        if (ctx.outputSelector() != null) {
            if (ctx.outputSelector().IDENT() != null) {
                outputSelector = ctx.outputSelector().IDENT().getText();
            } else if (ctx.outputSelector().NUMBER() != null) {
                outputSelector = ctx.outputSelector().NUMBER().getText();
            }
        }

        if (ctx.arguments() != null) {
            if (ctx.arguments().argList() != null) {
                arglist.addAll(create(ctx.arguments().argList()));
            } else if (ctx.arguments().argMap() != null) {
                argMap.putAll(create(ctx.arguments().argMap()));
            }
        }

        if (!arglist.isEmpty()) {
            if (!activeOption.isEmpty() && !outputSelector.isEmpty()) {
                r = new Reference(type, activeOption, arglist, outputSelector);
            } else if (!activeOption.isEmpty()) {
                r = new Reference(type, activeOption, arglist);
            } else if (!outputSelector.isEmpty()) {
                r = new Reference(type, arglist, outputSelector);
            } else {
                r = new Reference(type, arglist);
            }
        } else if (!argMap.isEmpty()) {
            if (!activeOption.isEmpty() && !outputSelector.isEmpty()) {
                r = new Reference(type, activeOption, argMap, outputSelector);
            } else if (!activeOption.isEmpty()) {
                r = new Reference(type, activeOption, argMap);
            } else if (!outputSelector.isEmpty()) {
                r = new Reference(type, argMap, outputSelector);
            } else {
                r = new Reference(type, argMap);
            }
        } else {
            if (!activeOption.isEmpty() && !outputSelector.isEmpty()) {
                r = new Reference(type, activeOption, outputSelector);
            } else if (!activeOption.isEmpty()) {
                r = new Reference(type, activeOption);
            } else if (!outputSelector.isEmpty()) {
                r = new Reference(type, outputSelector);
            } else {
                r = new Reference(type);
            }
        }

        return r;
    }

    private List<Expression> create(ShiroParser.ArgListContext ctx) {
        return ctx.arg().stream()
                .map(ShiroParser.ArgContext::expr)
                .map(this::get)
                .collect(Collectors.toList());
    }

    private Map<String, Expression> create(ShiroParser.ArgMapContext ctx) {
        Map<String, Expression> argMap = new HashMap<>();
        List<String> keys = ctx.keys.stream().map(Token::getText).collect(Collectors.toList());
        List<Expression> values = ctx.values.stream()
                .map(ShiroParser.ArgContext::expr)
                .map(this::get)
                .collect(Collectors.toList());

        for (int i = 0; i < keys.size(); i++) {
            argMap.put(keys.get(i), values.get(i));
        }
        return argMap;
    }

    private String convertFullyQualifiedTypeToString(ShiroParser.FullyQualifiedTypeContext ctx) {
        return ctx.types.stream().map(Token::getText).collect(Collectors.joining("."));
    }

    private Expression createFullyQualifiedType(ShiroParser.FullyQualifiedTypeContext ctx) {
        String type = convertFullyQualifiedTypeToString(ctx);
        return new FullyQualifiedType(type);
    }

    private Access determineAccess(String access) {
        switch (access) {
            case "input":
                return Access.READWRITE;
            case "output":
                return Access.READ;
            default:
                return Access.INTERNAL;
        }
    }

    private Expression get(ParseTree t) {
        if (expressions.get(t) == null) {
            return expressions.get(t.getChild(0));
        }
        return expressions.get(t);
    }

    private boolean inGraph() {
        return graph != null;
    }

    private boolean inNode() {
        return this.graph == null && !nodes.empty();
    }

    public Program getProgram() {
        return p;
    }
}
