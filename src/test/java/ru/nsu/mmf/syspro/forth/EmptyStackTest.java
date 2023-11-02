package ru.nsu.mmf.syspro.forth;

import junit.framework.TestCase;
import org.junit.Test;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.io.PrintStream;

public class EmptyStackTest {
    private StringBuilder sb=new StringBuilder();
    @Test
    public void empty(){
        sb=new StringBuilder();
        PrintStream printer=new PrintStream(System.out){
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        try {
            interpreter.interpret(".");
        }
        catch (InterpreterException e){
            TestCase.assertEquals("Not enough numbers on the stack", e.getMessage());
        }
    }
}
