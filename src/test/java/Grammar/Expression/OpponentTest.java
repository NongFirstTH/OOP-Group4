package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Direction;
import org.junit.Test;

import static Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class OpponentTest {
    @Test
    public void TestEval(){
        Opponent a = new Opponent();
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,2,3,t);

        assertEquals(0,a.eval(p1,t));
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