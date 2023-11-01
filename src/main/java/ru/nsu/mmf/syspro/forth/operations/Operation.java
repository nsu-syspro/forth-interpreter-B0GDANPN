package ru.nsu.mmf.syspro.forth.operations;

import ru.nsu.mmf.syspro.forth.Context;

public interface Operation {
    void apply(Context context);
}
