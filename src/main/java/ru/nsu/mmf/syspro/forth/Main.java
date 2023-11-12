package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.Scanner;

public class Main {//TODO try use sealed classes

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Appendable printer = new Appendable() {
            @Override
            public Appendable append(CharSequence csq) {
                System.out.print(csq);
                return null;
            }

            @Override
            public Appendable append(CharSequence csq, int start, int end) {
                return null;
            }

            @Override
            public Appendable append(char c) {
                return null;
            }
        };
        Interpreter interpreter = new Interpreter(printer);
        try {
            String line = scanner.nextLine();
            while (!interpreter.interpret(line)) {
                line = scanner.nextLine();
            }
        } catch (InterpreterException e) {
            interpreter.print(e.getMessage() + '\n');
        }
    }
}