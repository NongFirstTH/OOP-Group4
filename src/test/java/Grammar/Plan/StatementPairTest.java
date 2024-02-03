package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Opponent;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatementPairTest {
    @Test
    public void TestEval() throws EvalError {
        Plan IsTrue = new NoStatement();
        Plan IsFalse = new Done();
        assertTrue(new StatementPair(IsTrue, IsTrue).eval(null, null));
        assertFalse(new StatementPair(IsTrue, IsFalse).eval(null, null));
        assertFalse(new StatementPair(IsFalse, IsTrue).eval(null, null));
        assertFalse(new StatementPair(IsFalse, IsFalse).eval(null, null));
    }
}