package ru.nsu.mmf.syspro.forth;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

import org.junit.Test;
import ru.nsu.mmf.syspro.forth.operation.*;
import ru.nsu.mmf.syspro.forth.parser.Parser;

import java.util.ArrayList;
import java.util.Arrays;

public class ParserTest {
    @Test
    public void parseEmpty() {
        Parser parser = new Parser();

        parser.parseLine(null, "");
        assertNull(parser.nextOperation());
    }


    @Test
    public void parseType() {
        String arg = "1 2 + cr 10 >";
        Parser parser = new Parser();
        parser.parseLine(null, arg);
        ArrayList<Operation> operations = new ArrayList<>(Arrays.asList(
                new PushOperation(1),
                new PushOperation(2),
                new ArithmeticOperation("+"),
                new EmbeddedOperation("cr"),
                new PushOperation(10),
                new LogicOperation(">")));
        for (Operation operation : operations) {
            assertEquals(parser.nextOperation().getClass(), operation.getClass());
        }
    }
}
