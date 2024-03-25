package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.GamePlay.Player;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import com.websocket.demo.GamePlay.GameFactory;

import static com.websocket.demo.Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class StatementPairTest {

    @Test
    public void testEvalReturns() throws EvalError {
        Plan isTrue = new NoStatement();
        Plan isFalse = new Done();
        assertTrue(new StatementPair(isTrue, isTrue).eval(null));
        assertFalse(new StatementPair(isTrue, isFalse).eval(null));
        assertFalse(new StatementPair(isFalse, isTrue).eval(null));
        assertFalse(new StatementPair(isFalse, isFalse).eval(null));
    }

    public void assertTestEvalEffects(int currow, int curcol, Plan s) throws EvalError, SyntaxError {
        GameFactory factory = new GameFactory();
        Game g = factory.newGame1P(5, 5);

        s.eval(g);

        Player p = g.getPlayer();

        assertEquals(currow, p.getCurrow());
        assertEquals(curcol, p.getCurcol());
    }

    @Test
    public void testEvalEffects() throws EvalError, SyntaxError {
        Plan moveTrue = new MoveCommand(upleft);
        Plan moveFalse = new StatementPair(new MoveCommand(upright), new Done());

        assertTestEvalEffects(4, 3, new StatementPair(moveTrue ,moveTrue));
        assertTestEvalEffects(4, 5, new StatementPair(moveTrue, moveFalse));
        assertTestEvalEffects(5, 6, new StatementPair(moveFalse, moveTrue));
        assertTestEvalEffects(5, 6, new StatementPair(moveFalse, moveFalse));
    }
}