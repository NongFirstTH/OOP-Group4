package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryArithExprTest {
    @Test
    public void TestPower() throws SyntaxError, EvalError {
        PlanTokenizer p = new PlanTokenizer("1+2^3^2");
        ExpressionParser e = new ExpressionParser(p);
        HashMap<String,Integer> m = new HashMap<>();
        Player p1 = new Player();
        Territory t = new Territory();
        int result = e.parse().eval(m,p1,t);
        assertEquals(513,result);
    }

    @Test
    public void prettyPrint() throws SyntaxError {
        PlanTokenizer p = new PlanTokenizer("1+2^3^2");
        ExpressionParser e = new ExpressionParser(p);
        HashMap<String,Integer> m = new HashMap<>();
        Player p1 = new Player();
        Territory t = new Territory();
        StringBuilder s = new StringBuilder();
        e.parse().prettyPrint(s);
        assertEquals("(1 + (2 ^ (3 ^ 2)))",s.toString());
    }

}