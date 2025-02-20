package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.operation.*;

public class Interpreter {
    private final Context context;

    public void push(int number) {
        context.push(number);
    }

    public int pop() {
        return context.pop();
    }

    public int top() {
        return context.peek();
    }


    public void print(String line) {
        context.appendLine(line);
    }

    public Interpreter(Appendable printer) {
        this.context = new Context(printer);
    }

    public void stopInterpreter() {
        context.setState(true);
    }

    public boolean isExit() {
        return context.getState();
    }

    public boolean interpret(String... lines) {
        for (String line : lines) {
            Parser parser = new Parser(this, line);
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