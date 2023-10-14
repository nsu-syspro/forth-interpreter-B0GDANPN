package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Printable printer =new Printable() {
            @Override
            public void print(String line) {
                System.out.print(line);
            }
        };
        Interpreter interpreter = new Interpreter(printer);
        while (!interpreter.context.exit) {
            try {
                String line = scanner.nextLine();
                interpreter.interpret(line);
            } catch (InterpreterException e) {
                interpreter.context.printer.print(e.getMessage()+'\n');
            }
        }
    }
}