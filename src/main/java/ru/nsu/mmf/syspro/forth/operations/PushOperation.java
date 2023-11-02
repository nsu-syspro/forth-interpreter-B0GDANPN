package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Interpreter;
import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.NoSuchElementException;

public final class PushOperation implements Operation {
    private final int number;
    public PushOperation(String line){
        this.number=Integer.parseInt(line);
    }
    @Override
    public void apply(Interpreter interpreter) {
        interpreter.push(number);
    }
}
