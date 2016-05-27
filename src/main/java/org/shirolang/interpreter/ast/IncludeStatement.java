package org.shirolang.interpreter.ast;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * Defines an include statement
 */
public class IncludeStatement implements Codeable{
    private String file;

    public IncludeStatement(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    @Override
    public String toCode() {
        String path = GraphDefinition.class.getResource("shiro.stg").getPath();

        STGroup templates = new STGroupFile(path);
        ST code = templates.getInstanceOf("includeStmt");
        code.add("i", this);
        return code.render();
    }
}
