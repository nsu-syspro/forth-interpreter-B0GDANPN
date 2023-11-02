package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Interpreter;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.NoSuchElementException;

public final class EmbeddedOperation implements Operation {
    private final String command;
    public EmbeddedOperation(String command){
        this.command=command;
    }
    @Override
    public void apply(Interpreter interpreter) {
        switch (command) {
            case "dup":
                try {
                    int top = interpreter.top();
                    interpreter.push(top);
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "drop":
                try {
                    interpreter.pop();
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case ".":
                try {
                    interpreter.print(Integer.toString(interpreter.pop()));
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "swap":
                try {
                    int r = interpreter.pop();
                    int l = interpreter.pop();
                    interpreter.push(r);
                    interpreter.push(l);
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "rot":
                try {
                    int third =interpreter.pop();
                    int second = interpreter.pop();
                    int first = interpreter.pop();
                    interpreter.push(third);
                    interpreter.push(first);
                    interpreter.push(second);
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "emit":
                try {
                    int number = interpreter.pop();
                    interpreter.print(Character.toString((char)number));
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "cr":
                interpreter.print("\n");
                break;
            case "exit":
                interpreter.setExit(true);
                break;
            case "over":
                try {
                    int r =interpreter.pop();
                    int l = interpreter.pop();
                    interpreter.push(l);
                    interpreter.push(r);
                    interpreter.push(l);
                } catch (NoSuchElementException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
        }
    }
}
