package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.EmptyStackException;

import java.util.*;

public class Stack {
    private final ArrayList<Integer> A;

    private int top = -1;

    public Stack() {
        this.A = new ArrayList<Integer>();
    }

    public void add(int X) {
        top++;
        if (A.size()-1>=top){
            A.set(top,X);
        }
        else{
            A.add(X);
        }
    }

    public int peek() {
        if (top == -1) {
            throw new ru.nsu.mmf.syspro.forth.exceptions.EmptyStackException();
        } else {
            return A.get(top);
        }
    }

    public int pop() {
        if (top == -1) {
            throw new EmptyStackException();
        } else {
            int res = A.get(top);
            top--;
            return res;
        }
    }

}
