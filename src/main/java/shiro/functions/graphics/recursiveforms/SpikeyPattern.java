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
 * Defines a spikey space filling motif
 * @author jeffreyguenther
 */
public class SpikeyPattern extends Motif{
    
    public SpikeyPattern() {
        super(ShapeFactory.createSpikey());
        createTransforms();
    }
    
    private void createTransforms(){
        Transform t = new Translate();
        transforms.add(t);

        Rotate r1 = new Rotate(-90, 0, 0);
        Scale s1 = new Scale(0.4838709677, 0.4838709677, 0, 0);
        transforms.add(r1.createConcatenation(s1));

        Translate r2 = new Translate(0, -75);
        Scale s2 = new Scale(0.52, 0.52, 0, 0);
        transforms.add(r2.createConcatenation(s2));

        Rotate r3 = new Rotate(-134.5, 0, 0);
        Scale s3 = new Scale(0.6839411289, 0.6839411289, 0, 0);
        Translate t3 = new Translate(155, 0);
        transforms.add(t3.createConcatenation(r3.createConcatenation(s3)));
    }
}
