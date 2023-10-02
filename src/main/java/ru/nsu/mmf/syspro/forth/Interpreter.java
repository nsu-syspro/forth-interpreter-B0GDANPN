package ru.nsu.mmf.syspro.forth;

import java.util.EmptyStackException;
import java.util.Stack;

public class Interpreter {
    Stack<Integer> stack=new Stack<>();

    boolean exit = false;

    public void interpret(String line) {
        String[] commands = line.split("\\s+");
        for (int i=0;i<commands.length;i++) {
            if (isPrintString(commands[i])){
                doPrintString(commands[i+1]);
                i++;
                continue;
            }
            if (isNumeric(commands[i])) {
                stack.add(Integer.parseInt(commands[i]));
                continue;
            }
            if (isArithmeticOperator(commands[i])) {
                doArithmeticOperation(commands[i]);
                continue;
            }
            if (isEmbeddedOperator(commands[i])) {
                doEmbeddedOperation(commands[i]);
                continue;
            }
            if (isLogicOperator(commands[i])) {
                doLogicOperator(commands[i]);
            }
        }
    }
    private void doPrintString(String command){
        System.out.print(command.substring(0, command.length() - 1));
    }
    private void doLogicOperator(String command) {
        int l, r;
        try {
            r = stack.pop();
            l = stack.pop();
        } catch (EmptyStackException e) {
            throw new InterpreterException("Not enough numbers on the stack");
        }
        boolean res = switch (command) {
            case ">" -> l > r;
            case "<" -> l < r;
            default -> l == r;
        };
        stack.add(res ? 1 : 0);
    }
    private void doEmbeddedOperation(String command) {
        switch (command) {
            case "dup":
                try {
                    int top = stack.peek();
                    stack.add(top);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "drop":
                try {
                    stack.pop();
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case ".":
                try {
                    System.out.print(stack.pop());
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "swap":
                try {
                    int r = stack.pop();
                    int l = stack.pop();
                    stack.add(r);
                    stack.add(l);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "rot":
                try {
                    int third = stack.pop();
                    int second = stack.pop();
                    int first = stack.pop();
                    stack.add(third);
                    stack.add(first);
                    stack.add(second);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "emit":
                try {
                    int number = stack.pop();
                    System.out.print((char) number);
                } catch (EmptyStackException e) {
                    throw new InterpreterException("Not enough numbers on the stack");
                }
                break;
            case "cr":
                System.out.println();
                break;
            case "exit":
                this.exit = true;
                break;
        }
    }

    private void doArithmeticOperation(String command) {
        int l, r;
        try {
            r = stack.pop();
            l = stack.pop();
        } catch (EmptyStackException e) {
            throw new InterpreterException("Not enough numbers on the stack");
        }
        if ((command.equals("mod") || command.equals("/")) && r == 0) {
            throw new InterpreterException("Error: division by zero");
        }
        int res = switch (command) {
            case "+" -> l + r;
            case "-" -> l - r;
            case "*" -> l * r;
            case "/" -> l / r;
            default -> l % r;
        };
        stack.add(res);
    }

    private boolean isPrintString(String command){
        return command.equals(".\"");
    }
    private boolean isEmbeddedOperator(String command) {
        return command.equals("dup") || command.equals("drop") || command.equals(".")
                || command.equals("swap") || command.equals("rot")
                || command.equals("emit") || command.equals("cr")
                || command.equals("exit");
    }

    private boolean isArithmeticOperator(String command) {
        return command.equals("*") || command.equals("+") || command.equals("-")
                || command.equals("/") || command.equals("mod");
    }

    private boolean isLogicOperator(String command) {
        return command.equals(">") || command.equals("=") || command.equals("<");
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
}