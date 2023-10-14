package ru.nsu.mmf.syspro.forth;

public class Context {
    public Stack stack = new Stack();
    public String[] commands;
    public final Printable printer;
    public boolean exit = false;
    public Context(Printable printer){
        this.printer=printer;
    }
}