package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.operation.*;
import ru.nsu.mmf.syspro.forth.parser.Parser;

import java.io.IOException;
import java.util.ArrayDeque;

public class Interpreter {
    private boolean exit = false;
    private final ArrayDeque<Integer> stack = new ArrayDeque<>();

    public void push(Integer number) {
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
        exit = true;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean interpret(String line) {
        Parser parser = new Parser();
        parser.parseLine(line);
        Operation operation = parser.nextOperation();
        while (operation != null) {
            operation.apply(this);
            operation = parser.nextOperation();
        }
        return isExit();
    }
}