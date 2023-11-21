package ru.nsu.mmf.syspro.forth;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;

public class ParserTest {
    private StringBuilder sb;

    @Test
    public void parseEmpty() {
        sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("","","","","");
        assertEquals("", sb.toString());
    }

    @Test
    public void parseInvalid() {
        sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("A","3",".","cr","B");
        assertEquals("3\n", sb.toString());
    }
}
