package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

public class EmptyTest {
    private StringBuilder sb=new StringBuilder();
    @Test
    public void empty(){
        sb=new StringBuilder();
        Printable printer=new Printable() {
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        interpreter.interpret("");
    }
}
