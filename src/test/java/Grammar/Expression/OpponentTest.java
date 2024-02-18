package Grammar.Expression;
import GamePlay.Game;
import Grammar.Parse.SyntaxError;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OpponentTest {
    @Test
    public void HasClosestOpponent() throws SyntaxError, EvalError {
        Game g = new Game(9,9);
        g.addPlayerToTestOnly("myPlayer",5,4,null);
        g.addPlayerToTestOnly("PlayerUp",2,4,null);
        g.addPlayerToTestOnly("PlayerUpright",4,5,null);
        g.addPlayerToTestOnly("PlayerUpright2",4,6,null);
        g.addPlayerToTestOnly("PlayerDownright",7,8,null);
        g.addPlayerToTestOnly("PlayerDown",7,4,null);
        g.addPlayerToTestOnly("PlayerDownleft",6,1,null);
        g.addPlayerToTestOnly("PlayerUpleft",3,1,null);

        Opponent n = new Opponent();
        assertEquals(12,n.eval(g));
    }
    @Test
    public void SameDistanceOpponent() throws SyntaxError, EvalError {
        Game g = new Game(9,9);
        g.addPlayerToTestOnly("myPlayer",5,4,null);
        g.addPlayerToTestOnly("PlayerUp",2,4,null);
        g.addPlayerToTestOnly("PlayerUpleft",3,1,null);

        Opponent n = new Opponent();
        assertEquals(31,n.eval(g));
    }
    @Test
    public void NoOpponent() throws SyntaxError, EvalError {
        Game g = new Game(9,9);
        g.addPlayerToTestOnly("myPlayer",5,4,null);

        Opponent n = new Opponent();
        assertEquals(0,n.eval(g));
    }

    String printResult() throws SyntaxError {
        Opponent a = new Opponent();
        StringBuilder s = new StringBuilder();
        a.prettyPrint(s);
        return s.toString();
    }
    @Test
    public void TestPrettyPrint() throws SyntaxError {
        assertEquals("opponent",printResult());
    }
}