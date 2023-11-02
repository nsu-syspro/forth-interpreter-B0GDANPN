package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Interpreter;

public final class PrintStringOperation implements Operation {
    private final int start,end;
    private final String[] text;
    public PrintStringOperation(String[] tmp,int start,int end){
        text=tmp;
        this.start=start;
        this.end=end;
    }
    @Override
    public void apply(Interpreter interpreter) {
        for (int i=start;i<=end-1;i++){
            interpreter.print(text[i]+' ');
        }
        String endingStr=text[end];
        interpreter.print(endingStr.substring(0, endingStr.length() - 1));
    }
}
