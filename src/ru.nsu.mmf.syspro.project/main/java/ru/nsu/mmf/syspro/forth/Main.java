package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.util.Scanner;

public class Main {//TODO try use sealed classes
    //TODO create test checking empty stack
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //TODO use Appendable printstream
        Printable printer =new Printable() {
            @Override
            public void print(String line) {
                System.out.print(line);
            }
        };
        Interpreter interpreter = new Interpreter(printer);
        try {
            String line = scanner.nextLine();//TODO need hasNextLine?
            while (interpreter.interpret(line)){
                line = scanner.nextLine();//TODO return bool exit
            }
        } catch (InterpreterException e) {
            interpreter.context.printer.print(e.getMessage()+'\n');
        }
    }
}