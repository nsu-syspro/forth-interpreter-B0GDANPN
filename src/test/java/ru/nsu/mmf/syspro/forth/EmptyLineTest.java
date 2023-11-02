package ru.nsu.mmf.syspro.forth;

import org.junit.Test;

import java.io.PrintStream;

public class EmptyLineTest {
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
        interpreter.interpret("");
    }
}
