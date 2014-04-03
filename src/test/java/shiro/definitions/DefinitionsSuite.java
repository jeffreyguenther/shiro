/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author jeffreyguenther
 */
@RunWith(Suite.class)
@SuiteClasses({CommentTest.class, ProductionTest.class, NodeDefinitionTest.class, 
    PortDeclarationTest.class})
public class DefinitionsSuite {
    
}
