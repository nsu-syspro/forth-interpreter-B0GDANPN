package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.exceptions.EmptyStackException;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

public class LogicOperation extends Operation {
    private final String command;
    public LogicOperation(String command){
        this.command=command;
    }
    @Override
    public void apply(Context context) {
        int l, r;
        try {
            r = context.stack.pop();
            l = context.stack.pop();
            context.stack.add(l);
            context.stack.add(r);
        } catch (EmptyStackException e) {
            throw new InterpreterException("Not enough numbers on the stack");
        }
        boolean res = switch (command) {
            case ">" -> l > r;
            case "<" -> l < r;
            default -> l == r;
        };
        context.stack.add(res ? 1 : 0);
    }
}
