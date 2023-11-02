package ru.nsu.mmf.syspro.forth;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.PrintStream;

public class PrintStringTest {
    private StringBuilder sb=new StringBuilder();
    @Test
    public void print(){
        sb=new StringBuilder();
        PrintStream printer=new PrintStream(System.out){
            @Override
            public void print(String line) {
                sb.append(line);
            }
        };
        Interpreter interpreter= new Interpreter(printer);
        interpreter.interpret(".\" Foo\"");
        interpreter.interpret(".\" Foo boo\"");//
        TestCase.assertEquals("Foo\nFoo boo\n",sb.toString());
    }
}
