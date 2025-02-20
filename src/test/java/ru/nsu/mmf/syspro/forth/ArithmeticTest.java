package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class ArithmeticTest {
    private StringBuilder sb;

    @Test
    public void checkPlus() {
        sb = new StringBuilder();

        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("1 2 +", ".");
        assertEquals("3", sb.toString());
    }

    @Test
    public void checkMinus() {
        sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("1 2 -", ".");
        assertEquals("-1", sb.toString());
    }

    @Test
    public void checkMul() {
        sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("1 0 *", ".");
        assertEquals("0", sb.toString());
    }
}
