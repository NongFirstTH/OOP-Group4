package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Plan.NoStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class NoStatementTest {
    @Test
    public void testEval() throws EvalError {
        assertFalse(new NoStatement().eval(null));
    }
}