package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Direction;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class NearbyTest {
    @Test
    public void TestEval(){
        Nearby a = new Nearby(up);
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,2,3,t);

        assertEquals(0,a.eval(p1,t));
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