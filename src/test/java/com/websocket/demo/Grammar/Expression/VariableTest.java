package com.websocket.demo.Grammar.Expression;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Expression.Variable;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VariableTest {
    long evalResult(String src) throws EvalError, SyntaxError {
        Variable v = new Variable(src);
        Game g = new Game(9,9);
        g.addPlayerToTestOnly("p",1,1,null);
        g.getPlayer().bindings().put("a", 220L);
        g.getPlayer().bindings().put("b", 290L);
        return v.eval(g);
    }
    @Test
    public void TestReadSpecialVar() throws EvalError, SyntaxError {
        assertEquals(1,evalResult("rows"));
        assertEquals(1,evalResult("cols"));
        assertEquals(1,evalResult("currow"));
        assertEquals(1,evalResult("curcol"));
        assertEquals(10000,evalResult("budget"));
        assertEquals(100,evalResult("deposit"));
        assertEquals(0,evalResult("interest"));
        assertEquals(1000000,evalResult("maxdeposit"));
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
        double result = s.eval(null);
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