package ru.nsu.mmf.syspro.forth.operation;

import ru.nsu.mmf.syspro.forth.Interpreter;

public sealed interface Operation permits ArithmeticOperation, EmbeddedOperation, LogicOperation, PrintStringOperation, PushOperation, InvalidOperation {

    void apply(Interpreter interpreter);
}
