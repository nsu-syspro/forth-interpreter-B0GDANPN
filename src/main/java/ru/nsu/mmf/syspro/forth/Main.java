package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.Scanner;

public class Main {//TODO try use sealed classes

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Interpreter interpreter = new Interpreter(System.out);
        boolean state = false;
        while (!state) {
            String line = scanner.nextLine();
            try {
                state = interpreter.interpret(line);
            } catch (InterpreterException e) {
                interpreter.print(e.getMessage() + '\n');
            }
            if (state) {
                break;
            }

        }
    }
}