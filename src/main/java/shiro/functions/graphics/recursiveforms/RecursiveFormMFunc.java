/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics.recursiveforms;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import shiro.ResultTuple;
import shiro.Value;
import shiro.functions.MultiFunction;

/**
 * Creates a recursive form for the given motif
 * @author jeffreyguenther
 */
public class RecursiveFormMFunc implements MultiFunction{
    private static final String NAME = "RecursiveForm";
    private static final int MOTIF = 0;
    private static final int NUM_GENS = 1; 
    private static final int ALL_GENS = 2;
    private static final int ORIGIN = 3;

    @SuppressWarnings("unchecked")
    @Override
    public ResultTuple evaluate(List<Value> arguments) {
        Value motifValue = arguments.get(MOTIF);
        Motif motif = (Motif) motifValue.getValue();
        
        Integer numberOfGens = arguments.get(NUM_GENS).getValueAsInt();
        
        Boolean allGens = arguments.get(ALL_GENS).getValueAsBoolean();
        
        Value origin = arguments.get(ORIGIN);
        Point2D point = (Point2D) origin.getValue();
        
        RecursiveFormRenderer render = new RecursiveFormRenderer(motif, numberOfGens, allGens);
        
        Group g = new Group();
        g.getChildren().addAll(render.getShapes());
        g.relocate(point.getX(), point.getY());
        
        ResultTuple result = new ResultTuple(0, new Value(g, Group.class));
        return result;
    }
    
    @Override
    public String getName() {
        return NAME;
    }
    
}
