package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter(Printable printer);
        String line = scanner.nextLine();
        while (!interpreter.context.exit && !line.isEmpty()) {
            try {
                interpreter.interpret(line);
            } catch (InterpreterException e) {
                interpreter.context.printer.print(e.getMessage()+'\n');
                //System.err.println(e.getMessage());
            }
            line = scanner.nextLine();
        }
    }
}