package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryArithExprTest {
    public double result(String src) throws SyntaxError, EvalError {
        PlanTokenizer p = new PlanTokenizer(src);
        ExpressionParser e = new ExpressionParser(p);
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,1,2,t);
        return e.parse().eval(p1,t);
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
        assertThrows(EvalError.class,()->result("1//3"));
    }

    String printResult(String src) throws SyntaxError {
        PlanTokenizer p = new PlanTokenizer(src);
        ExpressionParser e = new ExpressionParser(p);
        Territory t = new Territory(20,20);
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