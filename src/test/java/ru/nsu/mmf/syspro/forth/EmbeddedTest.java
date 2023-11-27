package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class EmbeddedTest {
    @Test
    public void emit() {
        StringBuilder sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("65", "emit");
        assertEquals("A", sb.toString());
    }

    @Test
    public void cr() {
        StringBuilder sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("cr");
        assertEquals("\n", sb.toString());
    }
}
