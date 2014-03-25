/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro;

/**
 * Tags a class as being able to generate shiro code to represent itself.
 * @author jeffreyguenther
 */
public interface Codeable {
    public String toCode();
}
