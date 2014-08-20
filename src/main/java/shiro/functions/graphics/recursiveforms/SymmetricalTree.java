/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.graphics.recursiveforms;

import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

/**
 * Defines a symmetrical tree motif
 * @author jeffreyguenther
 */
public class SymmetricalTree extends Motif{
    private double trunkLength;
    private static double trunkWidth = 20;
    private double scaleFactor;
    private double angle;
    

    public SymmetricalTree(double trunkLength, double scaleFactor, double angle) {
        super(ShapeFactory.createTree(trunkWidth, trunkLength));
        this.trunkLength = trunkLength;
        this.scaleFactor = scaleFactor;
        this.angle = angle;
        createTransforms();
    }
    
    private void createTransforms(){
        Transform t = new Translate();
        transforms.add(t);

        Translate anchor = new Translate(trunkWidth/2, -(trunkLength -10));
        Rotate rotation = new Rotate(angle, trunkWidth/2, trunkLength);
        Scale s = new Scale(scaleFactor, scaleFactor, 0, trunkLength);

        Transform moveRight = anchor.createConcatenation(rotation.createConcatenation(s));
        transforms.add(moveRight);
        
        Rotate rotationRight = new Rotate(-angle, trunkWidth/2, trunkLength);
        Transform moveLeft = anchor.createConcatenation(rotationRight.createConcatenation(s));
        transforms.add(moveLeft);
    }
}
