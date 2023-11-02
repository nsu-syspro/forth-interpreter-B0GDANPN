package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Interpreter;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.NoSuchElementException;

public final class ArithmeticOperation implements Operation {
    private final String command;
    public ArithmeticOperation(String command){
        this.command=command;
    }
    @Override
    public void apply(Interpreter interpreter) {
        int l, r;
        try {
            r = interpreter.pop();
            l = interpreter.pop();
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
        interpreter.push(res);
    }
}
