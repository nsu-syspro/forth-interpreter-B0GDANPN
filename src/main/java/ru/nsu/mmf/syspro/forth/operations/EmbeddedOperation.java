package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Context;
import ru.nsu.mmf.syspro.forth.exceptions.EmptyStackException;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

public class EmbeddedOperation extends Operation {
    private String command;
    public EmbeddedOperation(String command){
        this.command=command;
    }
    @Override
    public void apply(Context context) {
        switch (command) {
            case "dup":
                try {
                    int top = (int) context.stack.peek();
                    context.stack.add(top);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "drop":
                try {
                    context.stack.pop();
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case ".":
                try {
                    System.out.print(context.stack.pop());
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "swap":
                try {
                    int r = (int) context.stack.pop();
                    int l = (int) context.stack.pop();
                    context.stack.add(r);
                    context.stack.add(l);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "rot":
                try {
                    int third = (int) context.stack.pop();
                    int second = (int) context.stack.pop();
                    int first = (int) context.stack.pop();
                    context.stack.add(third);
                    context.stack.add(first);
                    context.stack.add(second);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "emit":
                try {
                    int number = (int) context.stack.pop();
                    System.out.print((char) number);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "cr":
                System.out.println();
                break;
            case "exit":
                context.exit = true;
                break;
            case "over":
                try {
                    int r = (int) context.stack.pop();
                    int l = (int) context.stack.pop();
                    context.stack.add(l);
                    context.stack.add(r);
                    context.stack.add(l);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
        }
    }
}
