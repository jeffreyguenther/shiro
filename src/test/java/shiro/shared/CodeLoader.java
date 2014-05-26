/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.shared;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import shiro.Runtime;
import shiro.interpreter.ShiroLexer;
import shiro.interpreter.ShiroParser;

/**
 *
 * @author jeffreyguenther
 */
public class CodeLoader {
    public Runtime setupPSystem() throws IOException {
        Runtime pSystem = new Runtime();
        pSystem.loadDefaultDefinitions();
        return pSystem;
    }

    public Runtime setupPSystemWithSubjuncts() throws IOException, URISyntaxException {
        Runtime pSystem = new Runtime();
        URL resource = this.getClass().getResource("SimpleSubjunctiveExample.sro");
        pSystem.loadCode(Paths.get(resource.toURI()));
        return pSystem;
    }
    
    public ShiroParser parse(String file) throws IOException{
        ShiroLexer lexer = new ShiroLexer(new ANTLRInputStream(getClass()
                .getResourceAsStream(file)));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShiroParser parser = new ShiroParser(tokens);
        parser.setBuildParseTree(true);
        return parser;
    }
}
