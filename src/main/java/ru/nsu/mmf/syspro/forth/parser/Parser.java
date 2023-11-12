package ru.nsu.mmf.syspro.forth.parser;

import ru.nsu.mmf.syspro.forth.operation.*;

import java.util.ArrayList;

public class Parser {

    private final ArrayList<Operation> operations = new ArrayList<>();

    private Integer it = 0;

    public void parseLine(String line) {
        String[] tmp = line.split("\\s+");
        for (int i = 0; i < tmp.length; i++) {
            TypeOperation typeOperation = choose(tmp[i]);
            Operation operation = switch (typeOperation) {
                case LOGIC -> new LogicOperation(tmp[i]);
                case PUSH -> new PushOperation(Integer.parseInt(tmp[i]));
                case PRINT_STRING -> {//TODO union . and print_string
                    int j = i + 1;
                    int prev_i = i + 1;
                    while (tmp[j].charAt(tmp[j].length() - 1) != '"') {
                        j++;
                    }
                    i = j;
                    yield new PrintStringOperation(tmp, prev_i, j);
                }
                case ARITHMETIC -> new ArithmeticOperation(tmp[i]);
                case EMBEDDED -> new EmbeddedOperation(tmp[i]);
                default -> new InvalidOperation();
            };
            operations.add(operation);
        }
    }

    public Operation nextOperation() {
        if (it == operations.size()) {
            it = 0;
            operations.clear();
            return null;
        }
        it++;
        return operations.get(it - 1);
    }

    private boolean isEmbeddedOperation(String command) {
        return command.equals("dup") || command.equals("drop") || command.equals(".")
                || command.equals("swap") || command.equals("rot")
                || command.equals("emit") || command.equals("cr")
                || command.equals("exit") || command.equals("over");
    }

    private boolean isArithmeticOperation(String command) {
        return command.equals("*") || command.equals("+") || command.equals("-")
                || command.equals("/") || command.equals("mod");
    }

    private boolean isLogicOperation(String command) {
        return command.equals(">") || command.equals("=") || command.equals("<");
    }

    private boolean isPrintString(String command) {
        return command.equals(".\"");
    }

    private boolean isNumeric(String command) {
        if (command == null) {
            return false;
        }
        try {
            Integer.parseInt(command);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public TypeOperation choose(String command) {
        if (isPrintString(command)) {
            return TypeOperation.PRINT_STRING;
        } else if (isNumeric(command)) {
            return TypeOperation.PUSH;
        } else if (isArithmeticOperation(command)) {
            return TypeOperation.ARITHMETIC;
        } else if (isEmbeddedOperation(command)) {
            return TypeOperation.EMBEDDED;
        } else if (isLogicOperation(command)) {
            return TypeOperation.LOGIC;
        }
        return TypeOperation.NULL;
    }
}