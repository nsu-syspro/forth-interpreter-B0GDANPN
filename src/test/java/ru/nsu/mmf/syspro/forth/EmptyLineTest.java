package ru.nsu.mmf.syspro.forth;

import org.junit.Test;


public class EmptyLineTest {
    @Test
    public void empty() {
        StringBuilder sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("");
    }
}
