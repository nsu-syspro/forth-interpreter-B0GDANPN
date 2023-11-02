package ru.nsu.mmf.syspro.forth;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Context {//TODO change visibility with private and make method
    public ArrayDeque<Integer> stack=new ArrayDeque<>();
    public String[] commands;
    public final Printable printer;
    public boolean exit = false;
    public Context(Printable printer){
        this.printer=printer;
    }
}