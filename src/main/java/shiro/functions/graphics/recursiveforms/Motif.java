/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics.recursiveforms;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.transform.Transform;

/**
 * Provides a base class for the development of motifs.
 * @author jeffreyguenther
 */
public abstract class Motif {
    protected List<Transform> transforms;
    protected ShapeFactory shapeFactory;

    public Motif(ShapeFactory factory) {
        transforms = new ArrayList<>();
        shapeFactory = factory;
    }
    
    public List<Transform> getTransforms() {
        return transforms;
    }

    public ShapeFactory getShapeFactory() {
        return shapeFactory;
    }
}
