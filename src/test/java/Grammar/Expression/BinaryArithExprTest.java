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
        Map<String,Integer> m = new HashMap<>();
        Territory t = new Territory(1,2);
        Player p1 = new Player(10,1,2,t);
        return e.parse().eval(m,p1,t);
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
    public void TestThrowsEvalError() throws SyntaxError, EvalError {
        assertThrows(EvalError.class,()->result("1@2"));
    }

//    @Test
//    public void prettyPrint() throws SyntaxError {
//        PlanTokenizer p = new PlanTokenizer("1+2^3^2");
//        ExpressionParser e = new ExpressionParser(p);
//        HashMap<String,Integer> m = new HashMap<>();
//        Player p1 = new Player();
//        Territory t = new Territory();
//        StringBuilder s = new StringBuilder();
//        e.parse().prettyPrint(s);
//        assertEquals("(1 + (2 ^ (3 ^ 2)))",s.toString());
//    }

}