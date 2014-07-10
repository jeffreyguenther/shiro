/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics.recursiveforms;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;

/**
 *
 * @author jeffreyguenther
 */
public class RecursiveFormRenderer {
    private final Motif motif;
    private final List<Shape> shapes;
    private final boolean renderAllGens;
    private final int depth;

    public RecursiveFormRenderer(Motif m, int depth) {
        motif = m;
        renderAllGens = false;
        this.depth = depth;
        
        shapes = new ArrayList<>();
        render(m.getTransforms(), new Affine(), depth, renderAllGens);
    }

    public RecursiveFormRenderer(Motif m, int depth, boolean allGens) {
        this.motif = m;
        this.shapes = new ArrayList<>();
        this.renderAllGens = allGens;
        this.depth = depth;
        
        render(m.getTransforms(), new Affine(), depth, renderAllGens);
    }

    public List<Shape> getShapes() {
        return shapes;
    }
    
    private void render(List<Transform> displayList, Transform currentTransform, 
            int depth, boolean allGens){
        if (depth >= 1) {
            for (Transform t : displayList) {
                if (t.isIdentity()) {
                    if (allGens || depth == 1) {
                        // replace with shape factory
                        Shape s = motif.getShapeFactory().createShape();
                        s.getTransforms().add(currentTransform);
                        shapes.add(s);
                    }
                }else{
                    Transform newTransform = currentTransform.createConcatenation(t);
                    render(displayList, newTransform, depth - 1, allGens);
                }
            }
        }
    }
    
}
