package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import static Grammar.Plan.Direction.*;
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
        Game g = new Game(10, 10);
        g.addPlayer(null ,3, 3, null);

        s.eval(g);

        Player p = g.getPlayer();

        assertEquals(currow, p.getCurrow());
        assertEquals(curcol, p.getCurcol());
    }

    @Test
    public void testEvalEffects() throws EvalError, SyntaxError {
        Plan moveTrue = new MoveCommand(upleft);
        Plan moveFalse = new StatementPair(new MoveCommand(upright), new Done());

        assertTestEvalEffects(2, 1, new StatementPair(moveTrue ,moveTrue));
        assertTestEvalEffects(2, 3, new StatementPair(moveTrue, moveFalse));
        assertTestEvalEffects(3, 4, new StatementPair(moveFalse, moveTrue));
        assertTestEvalEffects(3, 4, new StatementPair(moveFalse, moveFalse));
    }
}