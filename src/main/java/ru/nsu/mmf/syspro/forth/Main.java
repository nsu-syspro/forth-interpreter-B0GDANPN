package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter();
        while (!interpreter.context.exit) {
            String line = scanner.nextLine();
            try {
                interpreter.interpret(line);
            } catch (InterpreterException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}