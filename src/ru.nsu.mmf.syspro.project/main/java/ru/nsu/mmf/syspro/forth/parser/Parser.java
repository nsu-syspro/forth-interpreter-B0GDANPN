package ru.nsu.mmf.syspro.forth.parser;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;
import ru.nsu.mmf.syspro.forth.operations.*;

import java.util.ArrayList;

public class Parser {
    private ArrayList<Operation> operations=new ArrayList<>();
    private Integer it = 0;

    public Parser(String line) {
        Selector selector = new Selector();
        String[] tmp = line.split("\\s+");
        for (int i = 0; i < tmp.length; i++) {
            TypeOperation typeOperation = selector.choose(tmp[i]);
            Operation operation = switch (typeOperation) {
                case LOGIC -> new LogicOperation(tmp[i]);
                case PUSH -> new PushOperation(tmp[i]);
                case PRINT_STRING -> {//TODO union . and print_string
                    int j = i + 1;
                    int prev_i=i+1;
                    while (tmp[j].charAt(tmp[j].length() - 1) != '"') {
                        j++;
                    }
                    i = j;
                    yield  new PrintStringOperation(tmp,prev_i, j);
                }
                case ARITHMETIC -> new ArithmeticOperation(tmp[i]);
                case EMBEDDED->new EmbeddedOperation(tmp[i]);
                default -> throw new InterpreterException("Invalid operation");
            };
            operations.add(operation);
        }
    }
    public Operation getOperation(){
        if (it==operations.size()){
            it=0;
            operations.clear();
            return null;
        }
        it++;
        return operations.get(it-1);
    }
}