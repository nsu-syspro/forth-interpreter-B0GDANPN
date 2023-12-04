package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.Interpreter;
import ru.nsu.mmf.syspro.forth.operation.*;

import java.util.ArrayList;

public class Parser {
    private final ArrayList<Operation> operations = new ArrayList<>();


    private Integer it = 0;

    private void parseLine(Interpreter interpreter, String line) {
        String[] tmp = line.split("\\s+");
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].isEmpty()) {
                continue;
            }
            TypeOperation typeOperation = choose(tmp[i]);
            Operation operation = switch (typeOperation) {
                case LOGIC -> new LogicOperation(tmp[i]);
                case PUSH -> new PushOperation(Integer.parseInt(tmp[i]));
                case PRINT_STRING -> {
                    String arg = null;
                    if (tmp[i].equals(".\"")) {
                        StringBuilder sb = new StringBuilder();
                        int j = i + 1;
                        while (tmp[j].charAt(tmp[j].length() - 1) != '"') {
                            sb.append(tmp[j]);
                            sb.append(' ');
                            j++;
                        }
                        sb.append(tmp[j], 0, tmp[j].length() - 1);
                        arg = sb.toString();
                    }
                    yield new PrintStringOperation(arg);
                }
                case ARITHMETIC -> new ArithmeticOperation(tmp[i]);
                case EMBEDDED -> new EmbeddedOperation(tmp[i]);
                default -> {
                    yield null;
                }
            };
            if (operation != null) {
                operations.add(operation);
            } else {
                interpreter.print("Invalid operation: " + tmp[i] + "\n");
            }
        }
    }
    public Parser(Interpreter interpreter, String line) {

        this.parseLine(interpreter, line);

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
        return command.equals("dup") || command.equals("drop") || command.equals("swap") || command.equals("rot")
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
        return command.equals(".") || command.equals(".\"");
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

    private TypeOperation choose(String command) {
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