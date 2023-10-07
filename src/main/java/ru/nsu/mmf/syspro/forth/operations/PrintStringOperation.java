package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Context;

public class PrintStringOperation extends Operation {
    private final String command;
    public PrintStringOperation(String command){
        this.command=command;
    }
    @Override
    public void apply(Context context) {
        System.out.print(command.substring(0, command.length() - 1));
    }
}
