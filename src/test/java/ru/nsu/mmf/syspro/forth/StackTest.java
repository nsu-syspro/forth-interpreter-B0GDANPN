package ru.nsu.mmf.syspro.forth;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.PrintStream;
//Чистый код // соверешенный код

public class StackTest {
    private StringBuilder sb=new StringBuilder();
    @Test
    public void check(){
        sb=new StringBuilder();
        PrintStream printer=new PrintStream(System.out){
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        interpreter.interpret("1 2 3");
        interpreter.interpret("4 5");
        interpreter.interpret(". . . . .");
        TestCase.assertEquals("\n\n54321\n",sb.toString());
    }
}
