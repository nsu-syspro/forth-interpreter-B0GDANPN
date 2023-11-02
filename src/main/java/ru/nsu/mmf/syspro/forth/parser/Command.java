package ru.nsu.mmf.syspro.forth.parser;

import ru.nsu.mmf.syspro.forth.operations.TypeOperation;

public class Command {
    public final TypeOperation typeOperation;
    public final String context;
    public Command(TypeOperation type,String line){
        typeOperation=type;
        context=line;
    }
}
