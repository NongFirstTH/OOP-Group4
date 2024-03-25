package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.Grammar.EvalError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NoStatementTest {
    @Test
    public void testEval() throws EvalError {
        assertFalse(new NoStatement().eval(null));
    }
}