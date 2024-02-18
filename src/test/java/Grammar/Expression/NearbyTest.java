package Grammar.Expression;
import GamePlay.Game;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Direction;
import org.junit.Test;
import static Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class NearbyTest {
    @Test
    public void NearbyHasOpponent() throws SyntaxError, EvalError {
        Game g = new Game(9,9);
        g.addPlayer("myPlayer",5,4,null);
        g.addPlayer("PlayerUp",2,4,null);
        g.addPlayer("PlayerUpright",4,5,null);
        g.addPlayer("PlayerUpright2",4,6,null);
        g.addPlayer("PlayerDownright",7,8,null);
        g.addPlayer("PlayerDown",7,4,null);
        g.addPlayer("PlayerDownleft",6,1,null);
        g.addPlayer("PlayerUpleft",3,1,null);

        Nearby n = new Nearby(up);
        assertEquals(303,n.eval(g));
        n = new Nearby(upright);
        assertEquals(103,n.eval(g));
        n = new Nearby(downright);
        assertEquals(403,n.eval(g));
        n = new Nearby(down);
        assertEquals(203,n.eval(g));
        n = new Nearby(downleft);
        assertEquals(303,n.eval(g));
        n = new Nearby(upleft);
        assertEquals(303,n.eval(g));
    }
    @Test
    public void NearbyHasNoOpponent() throws SyntaxError, EvalError {
        Game g = new Game(9,9);
        g.addPlayer("myPlayer",5,4,null);

        Nearby n = new Nearby(up);
        assertEquals(0,n.eval(g));
        n = new Nearby(upright);
        assertEquals(0,n.eval(g));
        n = new Nearby(downright);
        assertEquals(0,n.eval(g));
        n = new Nearby(down);
        assertEquals(0,n.eval(g));
        n = new Nearby(downleft);
        assertEquals(0,n.eval(g));
        n = new Nearby(upleft);
        assertEquals(0,n.eval(g));
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