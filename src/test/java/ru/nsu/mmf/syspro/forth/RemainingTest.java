package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;


public class RemainingTest {

    @Test
    public void checkRemainingNumbers() {
        StringBuilder sb = new StringBuilder();
        Interpreter interpreter = new Interpreter(System.out);
        interpreter.interpret("-1 1 2 + . -1");
        try {
            while (true) {
                sb.append(interpreter.pop()).append(' ');
            }
        } catch (NoSuchElementException e) {
            assertEquals("-1 -1 ", sb.toString());
        }
    }
}
