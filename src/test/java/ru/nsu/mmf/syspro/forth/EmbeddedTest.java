package ru.nsu.mmf.syspro.forth;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.PrintStream;

public class EmbeddedTest {
    private StringBuilder sb=new StringBuilder();
    @Test
    public void emit(){
        sb=new StringBuilder();
        PrintStream printer=new PrintStream(System.out){
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        interpreter.interpret("65");
        interpreter.interpret("emit");
        TestCase.assertEquals("\nA\n",sb.toString());
    }
    @Test
    public void cr(){
        sb=new StringBuilder();
        PrintStream printer=new PrintStream(System.out){
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        interpreter.interpret("cr");
        TestCase.assertEquals("\n\n",sb.toString());
    }
}
