package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;
import ru.nsu.mmf.syspro.forth.operations.*;
import ru.nsu.mmf.syspro.forth.parser.Parser;
import ru.nsu.mmf.syspro.forth.parser.Selector;

import java.io.PrintStream;
import java.util.ArrayDeque;

public class Interpreter {
    private boolean exit = false;
    private ArrayDeque<Integer> stack = new ArrayDeque<>();

    public void push(Integer number) {
        stack.addLast(number);
    }

    public int pop() {
        return stack.removeLast();
    }

    public int top() {
        return stack.peek();
    }

    private final PrintStream printer;

    public void print(String line) {
        printer.print(line);
    }

    public Interpreter(PrintStream printer) {
        this.printer = printer;
    }
    public void setExit(boolean flag){
        exit=flag;
    }
    public boolean isExit() {
        return exit;
    }

    public boolean interpret(String line) {
        if (line.isEmpty()) {
            return false;
        }
        Parser parser = new Parser(line);
        Operation operation = parser.getOperation();
        while (operation != null) {
            operation.apply(this);
            operation = parser.getOperation();
        }
        printer.print("\n");
        return isExit();
    }
}