package ru.nsu.mmf.syspro.forth;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.PrintStream;

public class ParserTest {
    private StringBuilder sb = new StringBuilder();

    @Test
    public void parseEmpty() {
        sb = new StringBuilder();
        PrintStream printer = new PrintStream(System.out) {
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter = new Interpreter(printer);
        interpreter.interpret("");
        interpreter.interpret("");
        interpreter.interpret("");
        interpreter.interpret("");
        interpreter.interpret("");
        TestCase.assertEquals("", sb.toString());
    }

    @Test
    public void parseInvalid() {
        sb = new StringBuilder();
        PrintStream printer = new PrintStream(System.out) {
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter = new Interpreter(printer);
        interpreter.interpret("A");
        interpreter.interpret("3");
        interpreter.interpret(".");
        interpreter.interpret("cr");
        interpreter.interpret("B");
        TestCase.assertEquals("3\n", sb.toString());
    }
}
