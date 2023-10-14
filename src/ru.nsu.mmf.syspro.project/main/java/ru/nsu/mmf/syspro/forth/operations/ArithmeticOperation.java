package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.exceptions.EmptyStackException;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

public class ArithmeticOperation extends Operation {
    private final String command;
    public ArithmeticOperation(String command){
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
        if ((command.equals("mod") || command.equals("/")) && r == 0) {
            throw new InterpreterException("Error: division by zero");
        }
        int res = switch (command) {
            case "+" -> l + r;
            case "-" -> l - r;
            case "*" -> l * r;
            case "/" -> l / r;
            default -> l % r;
        };
        context.stack.add(res);
    }
}
