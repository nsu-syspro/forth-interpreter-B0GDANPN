package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.operation.*;
import ru.nsu.mmf.syspro.forth.parser.Parser;

import java.io.IOException;
import java.util.ArrayDeque;

public class Interpreter {
    private boolean finishedState = false;
    private final ArrayDeque<Integer> stack = new ArrayDeque<>();

    public void push(int number) {
        stack.addLast(number);
    }

    public int pop() {
        return stack.removeLast();
    }

    public int top() {
        return stack.peek();
    }

    private final Appendable printer;

    public void print(String line) {
        try {
            printer.append(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Interpreter(Appendable printer) {
        this.printer = printer;
    }

    public void stopInterpreter() {
        finishedState = true;
    }

    public boolean isExit() {
        return finishedState;
    }

    public boolean interpret(String... lines) {
        Parser parser = new Parser();
        for (String line : lines) {
            parser.parseLine(this, line);
            Operation operation = parser.nextOperation();
            while (operation != null && !isExit()) {
                operation.apply(this);
                operation = parser.nextOperation();
            }
            if (isExit()) {
                return true;
            }
        }
        return false;
    }
}