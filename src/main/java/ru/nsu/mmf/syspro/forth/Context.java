package ru.nsu.mmf.syspro.forth;

public class Context {
    public Stack stack = new Stack();
    public String[] commands;
    public boolean exit = false;
}