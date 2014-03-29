/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jeffreyguenther
 */
public class CommentTest {
    
    private Comment c, c2;
    
    @Before
    public void setup(){
        c = new Comment(Comment.Type.INLINE, "Hello world");
        c2 = new Comment(Comment.Type.BLOCK, "I'm a\nlong comment.");
    }
    
    @Test
    public void getters(){
        Assert.assertEquals("should be \"Hello world\"", "Hello world", c.getContent());
        Assert.assertEquals("should be INLINE comment", Comment.Type.INLINE, c.getType());
        
        Assert.assertEquals("should be \"Hello world\"", "I'm a\nlong comment.", c2.getContent());
        Assert.assertEquals("should be BLOCK comment", Comment.Type.BLOCK, c2.getType());
    }
    
    @Test
    public void toCode(){
        Assert.assertEquals("should be an INLINE comment", "// Hello world", c.toCode());
        Assert.assertEquals("should be a BLOCK comment", "/*\n* I'm a\n* long comment.\n*/", c2.toCode());
        System.out.println(c2.toCode());
    }
}
