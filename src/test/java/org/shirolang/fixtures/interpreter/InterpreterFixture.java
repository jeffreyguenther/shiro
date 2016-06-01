package org.shirolang.fixtures.interpreter;

import org.shirolang.interpreter.InterpreterSuite;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Provides Shiro programs to use in tests
 */
public class InterpreterFixture {
    public static String fromFile(String file){
        byte[] bytes;
        try {
            URI uri = InterpreterSuite.class.getResource(file).toURI();
            bytes = Files.readAllBytes(Paths.get(uri));
            return new String(bytes);
        } catch (IOException | URISyntaxException e) {
            return "";
        }
    }

    public static String badCode(){
        return "asdfsdfs dsafsd \n890989089089080sdfsdfs89f8s908df908sd09fsf2342342";
    }
}
