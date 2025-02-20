package ru.nsu.mmf.syspro.forth.operation;

import ru.nsu.mmf.syspro.forth.Interpreter;
import ru.nsu.mmf.syspro.forth.InterpreterException;

import java.util.NoSuchElementException;

public final class PrintStringOperation implements Operation {

    private final String text;

    public PrintStringOperation(String str) {

        text = str;
    }

    @Override
    public void apply(Interpreter interpreter) {
        if (text == null) {
            try {
                interpreter.print(Integer.toString(interpreter.pop()));
            } catch (NoSuchElementException e) {
                throw new InterpreterException("Not enough numbers on the stack");
            }
        }
        else {
            interpreter.print(text);
        }
    }
}
