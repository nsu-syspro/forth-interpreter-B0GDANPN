package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

//Чистый код // соверешенный код

public class StackTest {
    @Test
    public void check() {
        StringBuilder sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret("1 2 3", "4 5", ". . . . .");
        assertEquals("54321", sb.toString());
    }
}
