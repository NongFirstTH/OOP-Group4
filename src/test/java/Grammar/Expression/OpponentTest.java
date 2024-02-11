package Grammar.Expression;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Direction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class OpponentTest {
    @Test
    public void TestEval() throws SyntaxError, EvalError {
        Opponent a = new Opponent();
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,3,1,t);
        Player p2 = new Player(10,4,2,t);
        Player p3 = new Player(10,3,2,t);
        List<Player> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);

        assertEquals(0,a.eval(new Game(l,t)));
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