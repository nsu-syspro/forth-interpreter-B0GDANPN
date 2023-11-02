package ru.nsu.mmf.syspro.forth.operations;

public sealed interface Operation permits ArithmeticOperation, EmbeddedOperation, LogicOperation, PrintStringOperation,PushOperation {
    void apply(Context context);
}
