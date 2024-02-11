package Grammar.Expression;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Direction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class NearbyTest {
    @Test
    public void TestEval() throws SyntaxError, EvalError {
        Nearby a = new Nearby(up);
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,3,1,t);
        Player p2 = new Player(10,1,1,t);
        List<Player> l = new ArrayList<>();
        l.add(p1);
        l.add(p2);
        assertEquals(200,a.eval(new Game(l,t)));
    }

    String printResult(Direction dir) throws SyntaxError {
        Nearby a = new Nearby(dir);
        StringBuilder s = new StringBuilder();
        a.prettyPrint(s);
        return s.toString();
    }
    @Test
    public void TestPrettyPrint() throws SyntaxError {
        assertEquals("nearby up",printResult(up));
        assertEquals("nearby down",printResult(down));
        assertEquals("nearby upleft",printResult(upleft));
        assertEquals("nearby upright",printResult(upright));
        assertEquals("nearby downleft",printResult(downleft));
        assertEquals("nearby downright",printResult(downright));
    }
}