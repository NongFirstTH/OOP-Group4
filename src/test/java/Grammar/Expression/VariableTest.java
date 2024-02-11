package Grammar.Expression;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VariableTest {
    long evalResult(String src) throws EvalError, SyntaxError {
        Variable v = new Variable(src);
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,2,3,t);
        List<Player> l = new ArrayList<>();
        l.add(p1);
        p1.bindings().put("a", 220L);
        p1.bindings().put("b", 290L);
        return v.eval(new Game(l,t));
    }
    @Test
    public void TestReadSpecialVar() throws EvalError, SyntaxError {
        assertEquals(2,evalResult("rows"));
        assertEquals(3,evalResult("cols"));
        assertEquals(2,evalResult("currow"));
        assertEquals(3,evalResult("curcol"));
        assertEquals(10,evalResult("budget"));
        assertEquals(0,evalResult("deposit"));
        assertEquals(0,evalResult("interest"));
        assertEquals(0,evalResult("maxdeposit"));
    }
    @Test
    public void TestReadVar() throws EvalError, SyntaxError {
        assertEquals(220,evalResult("a"));
        assertEquals(290,evalResult("b"));
        assertEquals(0,evalResult("c"));
    }

    @Test
    public void TestEvalRandom() throws EvalError, SyntaxError {
        Variable s = new Variable("random");
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,1,1,t);
        double result = s.eval(new Game("a",null,t));
        for(int i = 0;i<1000;i++){
            assertTrue(result >= 0 && result<=1000);
        }
    }

    @Test
    public void TestPrettyPrint(){
        Variable v = new Variable("random");
        StringBuilder s = new StringBuilder();
        v.prettyPrint(s);
        assertEquals("random",s.toString());
    }
}