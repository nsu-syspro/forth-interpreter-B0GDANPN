package ru.nsu.mmf.syspro.forth.parser;

import ru.nsu.mmf.syspro.forth.operations.*;

public class Selector {
    public TypeOperation choose(String command){
        if (isPrintString(command)) {
            return TypeOperation.PRINT_STRING;
        }
        else if (isNumeric(command)){
            return TypeOperation.PUSH;
        }
        else if (isArithmeticOperation(command)) {
            return TypeOperation.ARITHMETIC;
        }
        else if (isEmbeddedOperation(command)) {
            return TypeOperation.EMBEDDED;
        }
        else if (isLogicOperation(command)){
            return TypeOperation.LOGIC;
        }
        return TypeOperation.NULL;
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
    private boolean isEmbeddedOperation(String command) {
        return command.equals("dup") || command.equals("drop") || command.equals(".")
                || command.equals("swap") || command.equals("rot")
                || command.equals("emit") || command.equals("cr")
                || command.equals("exit") ||command.equals("over");
    }

    private boolean isArithmeticOperation(String command) {
        return command.equals("*") || command.equals("+") || command.equals("-")
                || command.equals("/") || command.equals("mod");
    }

    private boolean isLogicOperation(String command) {
        return command.equals(">") || command.equals("=") || command.equals("<");
    }
}
