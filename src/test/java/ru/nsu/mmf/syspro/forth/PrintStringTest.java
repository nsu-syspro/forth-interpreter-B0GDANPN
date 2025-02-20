package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class PrintStringTest {

    @Test
    public void print() {
        StringBuilder sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(sb);
        interpreter.interpret(".\" Foo\"","cr",".\" Foo boo\"");//
        assertEquals("Foo\nFoo boo", sb.toString());
    }
}
