package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.NoSuchElementException;

public class ArithmeticOperation implements Operation {
    private final String command;
    public ArithmeticOperation(String command){
        this.command=command;
    }
    // TODO удалять числа со стека
    @Override
    public void apply(Context context) {
        int l, r;
        try {
            r = context.stack.removeLast();
            l = context.stack.removeLast();
        } catch (NoSuchElementException e) {
            throw new InterpreterException("Not enough numbers on the stack");
        }
        if ((command.equals("mod") || command.equals("/")) && r == 0) {//
            throw new InterpreterException("Error: division by zero");
        }
        int res = switch (command) {//TODO try use enums or classes(extend abstract)
            case "+" -> l + r;
            case "-" -> l - r;
            case "*" -> l * r;
            case "/" -> l / r;
            default -> l % r;
        };
        context.stack.add(res);
    }
}
