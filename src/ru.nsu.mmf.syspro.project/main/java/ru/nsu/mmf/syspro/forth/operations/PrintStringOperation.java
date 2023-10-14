package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Context;

public class PrintStringOperation extends Operation {
    private final int start,end;
    public PrintStringOperation(int start,int end){
        this.start=start;
        this.end=end;
    }
    @Override
    public void apply(Context context) {
        for (int i=start;i<=end-1;i++){
            System.out.print(context.commands[i]+' ');
        }
        String endingStr=context.commands[end];
        System.out.print(endingStr.substring(0, endingStr.length() - 1));
    }
}
