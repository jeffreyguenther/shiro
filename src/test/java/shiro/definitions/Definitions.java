/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.definitions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Methods to help test definition objects
 * @author jeffreyguenther
 */
public class Definitions {
    /**
     * Load a definition 
     * @param def
     * @param c
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static String loadDef(String def, Class c) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(c.getResource(def).getFile()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        String newLine = "\n";
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append(newLine);
        }
        return sb.toString().trim();
    }
}
