package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoneTest {
    @Test
    public void doneTest() throws SyntaxError, EvalError {
        Game g = new Game(100,100);
        Done d = new Done();
        assertFalse(d.eval(g));
    }
}