package Grammar.Expression;

import GamePlay.Game;
import GamePlay.GameFactory;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryArithExprTest {
    public long result(String src) throws SyntaxError, EvalError {
        PlanTokenizer p = new PlanTokenizer(src);
        ExpressionParser e = new ExpressionParser(p);
        GameFactory gameF = new GameFactory();
        Game g = gameF.newGame1P();
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