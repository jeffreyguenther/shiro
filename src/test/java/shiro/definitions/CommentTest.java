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
    
    private Comment inline, block;
    private static final String INLINE_COMMENT = "Hello world";
    private static final String BLOCK_COMMENT = "I'm a\nlong comment.";
    
    @Before
    public void setup(){
        inline = new Comment(Comment.Type.INLINE, INLINE_COMMENT);
        block = new Comment(Comment.Type.BLOCK, BLOCK_COMMENT);
    }
    
    @Test
    public void getters(){
        Assert.assertEquals("should be \"Hello world\"", INLINE_COMMENT, inline.getContent());
        Assert.assertEquals("should be INLINE comment", Comment.Type.INLINE, inline.getType());
        Assert.assertEquals("should have only one line", 1, inline.getLines().size());
        Assert.assertEquals("line should match content", inline.getContent(), inline.getLines().get(0));
        
        Assert.assertEquals("should be \"Hello world\"", BLOCK_COMMENT, block.getContent());
        Assert.assertEquals("should be BLOCK comment", Comment.Type.BLOCK, block.getType());
        Assert.assertEquals("should have multiple lines", 2, block.getLines().size());
    }
    
    @Test
    public void toCode(){
        Assert.assertEquals("should be an INLINE comment", "// Hello world", inline.toCode());
        Assert.assertEquals("should be a BLOCK comment", "/*\n* I'm a\n* long comment.\n*/", block.toCode());
    }
}
