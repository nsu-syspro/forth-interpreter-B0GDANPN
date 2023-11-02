package ru.nsu.mmf.syspro.forth.parser;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;
import ru.nsu.mmf.syspro.forth.operations.*;

import java.util.ArrayList;

public class Parser {
    private ArrayList<Command> ;
    private Integer it=0;

    public Parser(String line){
        Selector selector = new Selector();
        String[] tmp= line.split("\\s+");
        for (int i = 0; i < tmp.length; i++){
            if(isNumeric(tmp[i])){
                commands.add(new Command(TypeOperation.PUSH,tmp[i]));
                continue;
            }
            TypeOperation typeOperation = selector.choose(tmp[i]);
        }
    }

        Operation operation;
        Selector selector = new Selector();
        TypeOperation typeOperation = selector.choose(context.commands[i]);
        switch (typeOperation) {//TODO replace without break
            case LOGIC: {
                operation = new LogicOperation(context.commands[i]);
                break;
            }
            case PRINT_STRING: {//TODO union . and print_string
                int j = i + 1;
                while (context.commands[j].charAt(context.commands[j].length() - 1) != '"') {
                    j++;
                }
                operation = new PrintStringOperation(i + 1, j);
                i = j;
                break;
            }
            case ARITHMETIC: {
                operation = new ArithmeticOperation(context.commands[i]);
                break;
            }
            case EMBEDDED: {
                operation = new EmbeddedOperation(context.commands[i]);
                break;
            }
            default: {
                throw new InterpreterException("Invalid operation");
            }
        }
        operation.apply(context);
    }
}
