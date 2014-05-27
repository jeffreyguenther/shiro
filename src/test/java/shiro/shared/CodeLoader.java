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
import shiro.ShiroRuntime;
import shiro.interpreter.ShiroLexer;
import shiro.interpreter.ShiroParser;

/**
 *
 * @author jeffreyguenther
 */
public class CodeLoader {
    public ShiroRuntime setupPSystem() throws IOException {
        ShiroRuntime pSystem = new ShiroRuntime();
        pSystem.loadDefaultDefinitions();
        return pSystem;
    }

    public ShiroRuntime setupPSystemWithSubjuncts() throws IOException, URISyntaxException {
        ShiroRuntime pSystem = new ShiroRuntime();
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
