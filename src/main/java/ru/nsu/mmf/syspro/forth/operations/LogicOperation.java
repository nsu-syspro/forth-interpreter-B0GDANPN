package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Interpreter;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.NoSuchElementException;

public final class LogicOperation implements Operation {
    private final String command;
    public LogicOperation(String command){
        this.command=command;
    }
    @Override
    public void apply(Interpreter interpreter) {
        int l, r;
        try {
            r = interpreter.pop();
            l = interpreter.pop();
            interpreter.push(l);
            interpreter.push(r);
        } catch (NoSuchElementException e) {
            throw new InterpreterException("Not enough numbers on the stack");
        }
        boolean res = switch (command) {
            case ">" -> l > r;
            case "<" -> l < r;
            default -> l == r;
        };
        interpreter.push(res ? 1 : 0);
    }
}
