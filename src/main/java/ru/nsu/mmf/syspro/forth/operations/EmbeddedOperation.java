package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.NoSuchElementException;

public final class EmbeddedOperation implements Operation {
    private final String command;
    public EmbeddedOperation(String command){
        this.command=command;
    }
    @Override
    public void apply(Context context) {
        switch (command) {
            case "dup":
                try {
                    int top = context.stack.peek();
                    context.stack.add(top);
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "drop":
                try {
                    context.stack.pop();
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case ".":
                try {
                    context.printer.print(Integer.toString(context.stack.pop()));
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "swap":
                try {
                    int r = context.stack.pop();
                    int l = context.stack.pop();
                    context.stack.add(r);
                    context.stack.add(l);
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "rot":
                try {
                    int third = context.stack.pop();
                    int second = context.stack.pop();
                    int first = context.stack.pop();
                    context.stack.add(third);
                    context.stack.add(first);
                    context.stack.add(second);
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "emit":
                try {
                    int number = context.stack.pop();
                    context.printer.print(Character.toString((char)number));
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "cr":
                context.printer.print("\n");
                break;
            case "exit":
                context.exit = true;
                break;
            case "over":
                try {
                    int r = context.stack.pop();
                    int l = context.stack.pop();
                    context.stack.add(l);
                    context.stack.add(r);
                    context.stack.add(l);
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
        }
    }
}
