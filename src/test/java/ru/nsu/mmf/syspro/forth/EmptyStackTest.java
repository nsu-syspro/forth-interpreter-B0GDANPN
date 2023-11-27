package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class EmptyStackTest {

    @Test
    public void empty() {
        StringBuilder sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        try {
            interpreter.interpret(".");
        } catch (InterpreterException e) {
            assertEquals("Not enough numbers on the stack", e.getMessage());
        }
    }
}
