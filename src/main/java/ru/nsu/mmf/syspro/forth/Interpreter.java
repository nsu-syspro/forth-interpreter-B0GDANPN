package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.operations.*;

public class Interpreter {
    public Context context=new Context();

    public void interpret(String line) {
        String[] commands = line.split("\\s+");
        for (int i = 0; i < commands.length && !context.exit; i++) {
            if (isNumeric(commands[i])) {
                context.stack.add(Integer.parseInt(commands[i]));
                continue;
            }
            Operation operation=new Operation();
            Selector selector=new Selector();
            TypeOperation typeOperation=selector.choose(commands[i]);
            switch (typeOperation){
                case LOGIC:{
                    operation= new LogicOperation(commands[i]);
                    break;
                }
                case PRINT_STRING:{
                    operation=new PrintStringOperation(commands[i+1]);
                    i++;
                    break;
                }
                case ARITHMETIC:{
                    operation= new ArithmeticOperation(commands[i]);
                    break;
                }
                case EMBEDDED:{
                    operation= new EmbeddedOperation(commands[i]);
                }
            }
            operation.apply(context);
        }
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