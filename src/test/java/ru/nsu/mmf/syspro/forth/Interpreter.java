package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;
import ru.nsu.mmf.syspro.forth.operations.*;

public class Interpreter {
    public Interpreter(Printable printer) {
        this.context = new Context(printer);
    }

    public Context context;

    public void interpret(String line) {
        if (line.isEmpty()) return;
        //TODO interpreter do only interpretrer funcs
        //TODO Don`t save commands in context
        //TODO create parser, thar returned next command(and type) or number(command push)
        context.commands = line.split("\\s+");
        for (int i = 0; i < context.commands.length && !context.exit; i++) {
            if (isNumeric(context.commands[i])) {
                context.stack.add(Integer.parseInt(context.commands[i]));
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
        context.printer.print("\n");
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