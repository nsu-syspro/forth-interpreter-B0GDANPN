package ru.nsu.mmf.syspro.forth;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.PrintStream;

public class ArithmeticTest {
    private StringBuilder sb=new StringBuilder();
    @Test
    public void checkPlus(){
        sb=new StringBuilder();
        PrintStream printer=new PrintStream(System.out){
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        interpreter.interpret("1 2 +");
        interpreter.interpret(".");
        TestCase.assertEquals("\n3\n",sb.toString());
    }
    @Test
    public void checkMinus(){
        sb=new StringBuilder();
        PrintStream printer=new PrintStream(System.out){
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        interpreter.interpret("1 2 -");
        interpreter.interpret(".");
        TestCase.assertEquals("\n-1\n",sb.toString());
    }
    @Test
    public void checkMul(){
        sb=new StringBuilder();
        PrintStream printer=new PrintStream(System.out){
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        interpreter.interpret("1 0 *");
        interpreter.interpret(".");
        TestCase.assertEquals("\n0\n",sb.toString());
    }
}
