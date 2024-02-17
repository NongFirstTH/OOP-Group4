package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Expression.IntLit;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import static Grammar.Plan.Direction.upleft;
import static Grammar.Plan.Direction.upright;
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
        Game g = new Game(10, 10);
        g.addPlayer(null ,3, 3, null);

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