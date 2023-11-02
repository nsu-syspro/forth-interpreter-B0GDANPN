package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;
import ru.nsu.mmf.syspro.forth.operations.*;
import ru.nsu.mmf.syspro.forth.parser.Selector;

import java.io.PrintStream;
import java.util.ArrayDeque;

public class Interpreter {
    private boolean exit = false;
    private ArrayDeque<Integer> stack=new ArrayDeque<>();
    public void push(Integer number){
        stack.push(number);
    }
    public void pop(Integer number){
        stack.removeLast();
    }
    private final PrintStream printer;
    public void print(String line){
        printer.print(line);
    }
    public Interpreter(PrintStream printer) {
        this.printer=printer;    }

    public boolean isExit(){
        return exit;
    }

    public boolean interpret(String line) {
        if( line.isEmpty()){
            return true;
        }
        //TODO interpreter do only interpretrer funcs
        //TODO Don`t save commands in context
        //TODO create parser, thar returned next command(and type) or number(command push)
        context.commands = line.split("\\s+");
        for (int i = 0; i < context.commands.length && !isExit(); i++) {
            if (isNumeric(context.commands[i])) {
                stack.push(Integer.parseInt(context.commands[i]));
                continue;
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
        printer.print("\n");
        return isExit();
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