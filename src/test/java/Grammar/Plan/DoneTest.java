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
        Territory t = new Territory(100, 100);
        Player p = new Player(null, 100,1,1,100,t);
        Game g = new Game(p.getRow(),p.getCol());
        Done done = new Done();
        assertFalse(done.eval(g));
    }
}