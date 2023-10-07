package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.exceptions.EmptyStackException;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

public class ArithmeticOperation extends Operation {
    private String command;
    public ArithmeticOperation(String command){
        this.command=command;
    }
    @Override
    public void apply(Context context) {
        int l, r;
        try {
            r = (int) context.stack.pop();
            l = (int) context.stack.pop();
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
