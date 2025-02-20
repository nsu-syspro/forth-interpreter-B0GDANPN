package ru.nsu.mmf.syspro.forth;

import java.io.IOException;
import java.util.ArrayDeque;

public class Context {
    private boolean finishedState = false;
    private final ArrayDeque<Integer> stack = new ArrayDeque<>();
    private final Appendable printer;

    public Context(Appendable printer){
        this.printer=printer;
    }
    public void push(int number){
        stack.addLast(number);
    }
    public int pop(){
        return stack.removeLast();
    }
    public int peek(){
        return stack.peek();
    }
    public void appendLine(String line) {
        try {
            printer.append(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setState(boolean state){
        finishedState=state;
    }
    public boolean getState(){
        return finishedState;
    }
}
