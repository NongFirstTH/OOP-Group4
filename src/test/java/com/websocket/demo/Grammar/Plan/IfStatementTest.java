package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.GamePlay.GameFactory;
import com.websocket.demo.GamePlay.Player;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Expression.Expression;
import com.websocket.demo.Grammar.Expression.IntLit;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;
import static com.websocket.demo.Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class IfStatementTest {

    @Test
    public void testEval() throws EvalError {
        Plan planTrue = new NoStatement();
        Plan planFalse = new Done();

        Expression exprPos = new IntLit(1);
        Expression exprZero = new IntLit(0);
        Expression exprNeg = new IntLit(-1);

        assertTrue(new IfStatement(exprPos, planTrue, planFalse).eval(null));
        assertFalse(new IfStatement(exprZero, planTrue, planFalse).eval(null));
        assertFalse(new IfStatement(exprNeg, planTrue, planFalse).eval(null));
    }

    void assertTestEvalEffects(int currow, int curcol, Plan s) throws EvalError, SyntaxError {

        GameFactory factory = new GameFactory();
        Game g = factory.newGame1P(3, 3);

        s.eval(g);

        Player p = g.getPlayer();

        assertEquals(currow, p.getCurrow());
        assertEquals(curcol, p.getCurcol());
    }

    @Test
    public void testEvalEffects() throws EvalError, SyntaxError {
        Plan moveL = new MoveCommand(upleft);
        Plan moveR = new MoveCommand(upright);

        Expression exprPos = new IntLit(1);
        Expression exprZero = new IntLit(0);
        Expression exprNeg = new IntLit(-1);


        assertTestEvalEffects(3, 2, new IfStatement(exprPos, moveL ,moveR));
        assertTestEvalEffects(3, 4, new IfStatement(exprZero, moveL ,moveR));
        assertTestEvalEffects(3, 4, new IfStatement(exprNeg, moveL ,moveR));
    }
}