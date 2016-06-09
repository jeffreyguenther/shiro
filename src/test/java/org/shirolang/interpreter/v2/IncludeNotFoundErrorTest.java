package org.shirolang.interpreter.v2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IncludeNotFoundErrorTest {
    @Test
    public void canCreate(){
        IncludeNotFoundError error = new IncludeNotFoundError("geom.sro");
        assertEquals("geom.sro cannot be found.", error.getMessage());
    }
}
