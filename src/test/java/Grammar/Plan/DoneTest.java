package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoneTest {
    @Test
    public void doneTest() throws SyntaxError, EvalError {
        Game g = new Game(100,100);
        Done d = new Done();
        assertFalse(d.eval(g));
    }
}