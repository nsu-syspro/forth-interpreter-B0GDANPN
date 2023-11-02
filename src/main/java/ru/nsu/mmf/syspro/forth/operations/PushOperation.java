package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.NoSuchElementException;

public class PushOperation {
    private final int number;
    public PushOperation(String line){
        this.number=Integer.parseInt(line);
    }
    @Override
    public void apply(Context context) {
        context.stack.push(number);
    }
}
