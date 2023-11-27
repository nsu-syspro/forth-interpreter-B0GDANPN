package ru.nsu.mmf.syspro.forth.operation;

import ru.nsu.mmf.syspro.forth.Interpreter;

public final class PushOperation implements Operation {

    private final int number;

    public PushOperation(int number) {
        this.number = number;
    }

    @Override
    public void apply(Interpreter interpreter) {
        interpreter.push(number);
    }
}
