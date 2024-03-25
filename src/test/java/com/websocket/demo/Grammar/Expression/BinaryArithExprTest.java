package com.websocket.demo.Grammar.Expression;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.GamePlay.GameFactory;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Parse.ExpressionParser;
import com.websocket.demo.Grammar.Parse.PlanTokenizer;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryArithExprTest {
    public long result(String src) throws SyntaxError, EvalError {
        PlanTokenizer p = new PlanTokenizer(src);
        ExpressionParser e = new ExpressionParser(p);
        GameFactory gameF = new GameFactory();
        Game g = gameF.newGame1P(1,1);
        return e.parse().eval(g);
    }
    @Test
    public void TestOperators() throws SyntaxError, EvalError {
        assertEquals(28,result("9+19"));
        assertEquals(-32,result("1-33"));
        assertEquals(198,result("99*2"));
        assertEquals(11,result("77/7"));
        assertEquals(0,result("98%7"));
        assertEquals(513,result("1+2^3^2"));
        assertEquals(53,result("7*8%11^2-9+6"));
    }

    @Test
    public void TestThrows() throws SyntaxError{
        assertThrows(SyntaxError.class,()->result("1@2"));
        assertThrows(SyntaxError.class,()->result("1+?3"));
        assertThrows(EvalError.class,()->result("1//3"));
        assertThrows(EvalError.class,()->result("1*+3"));
        assertThrows(EvalError.class,()->result("1--3"));
    }

    String printResult(String src) throws SyntaxError {
        PlanTokenizer p = new PlanTokenizer(src);
        ExpressionParser e = new ExpressionParser(p);
        StringBuilder s = new StringBuilder();
        e.parse().prettyPrint(s);
        return s.toString();
    }
    @Test
    public void prettyPrint() throws SyntaxError {
        assertEquals("(7 - (2 * 7))",printResult("7-2*7"));
        assertEquals("((((7 * 8) % (11 ^ 2)) - 9) + 6)",printResult("7*8%11^2-9+6"));
    }

}