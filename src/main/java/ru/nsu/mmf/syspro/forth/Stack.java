package ru.nsu.mmf.syspro.forth;

import ru.nsu.mmf.syspro.forth.exceptions.EmptyStackException;

import java.util.*;

public class Stack<T> {
    private final ArrayList<T> A;

    private int top = -1;

    public Stack() {
        this.A = new ArrayList<T>();
    }

    public void add(T X) {
        top++;
        A.add(X);
    }

    public T peek() {
        if (top == -1) {
            throw new ru.nsu.mmf.syspro.forth.exceptions.EmptyStackException();
        } else {
            return A.get(top);
        }
    }

    public T pop() {
        if (top == -1) {
            throw new EmptyStackException();
        } else {
            T res=A.get(top);
            top--;
            return res;
        }
    }

}
