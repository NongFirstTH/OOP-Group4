package com.websocket.demo.Grammar.Expression;
import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import com.websocket.demo.Grammar.Plan.Direction;
import org.junit.jupiter.api.Test;

import static com.websocket.demo.Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class NearbyTest {
    @Test
    public void NearbyHasOpponent() throws SyntaxError, EvalError {
        Game g = new Game(9,9);
        g.addPlayerToTestOnly("myPlayer",5,4,null);
        g.addPlayerToTestOnly("PlayerUp",2,4,null);
        g.addPlayerToTestOnly("PlayerUpright",4,5,null);
        g.addPlayerToTestOnly("PlayerUpright2",4,6,null);
        g.addPlayerToTestOnly("PlayerDownright",7,8,null);
        g.addPlayerToTestOnly("PlayerDown",7,4,null);
        g.addPlayerToTestOnly("PlayerDownleft",6,1,null);
        g.addPlayerToTestOnly("PlayerUpleft",3,1,null);

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
        g.addPlayerToTestOnly("myPlayer",5,4,null);

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