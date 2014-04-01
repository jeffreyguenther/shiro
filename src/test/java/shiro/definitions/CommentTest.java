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
    private static final String INLINE_COMMENT = "Hello world";
    private static final String BLOCK_COMMENT = "I'm a\nlong comment.";
    
    @Before
    public void setup(){
        c = new Comment(Comment.Type.INLINE, INLINE_COMMENT);
        c2 = new Comment(Comment.Type.BLOCK, BLOCK_COMMENT);
    }
    
    @Test
    public void getters(){
        Assert.assertEquals("should be \"Hello world\"", INLINE_COMMENT, c.getContent());
        Assert.assertEquals("should be INLINE comment", Comment.Type.INLINE, c.getType());
        
        Assert.assertEquals("should be \"Hello world\"", BLOCK_COMMENT, c2.getContent());
        Assert.assertEquals("should be BLOCK comment", Comment.Type.BLOCK, c2.getType());
    }
    
    @Test
    public void toCode(){
        Assert.assertEquals("should be an INLINE comment", "// Hello world", c.toCode());
        Assert.assertEquals("should be a BLOCK comment", "/*\n* I'm a\n* long comment.\n*/", c2.toCode());
    }
}
