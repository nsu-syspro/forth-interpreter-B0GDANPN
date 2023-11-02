package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.InterpreterException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {//TODO try use sealed classes
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        //TODO use Appendable printstream
        Printable printer =new Printable() {//
            @Override
            public void print(String s){
                System.out.print(s);
            }
        };
        Interpreter interpreter = new Interpreter(printer);
        try {
            String line = scanner.nextLine();
            while (!interpreter.interpret(line)){
                line = scanner.nextLine();
            }
        } catch (InterpreterException e) {
            interpreter.print(e.getMessage()+'\n');
        }
    }
}