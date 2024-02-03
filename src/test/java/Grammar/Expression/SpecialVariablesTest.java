package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SpecialVariablesTest {
    public double result(String var) throws SyntaxError, EvalError {
        PlanTokenizer p = new PlanTokenizer(var);
        ExpressionParser e = new ExpressionParser(p);
        Map<String,Integer> m = new HashMap<>();
//        m.put(var,0);
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,1,1,t);
        return e.parse().eval(m,p1,t);
    }
    @Test
    public void TestThrowsEvalError() throws SyntaxError, EvalError {
        Variable v = new Variable("rows");
        Map<String,Integer> m = new HashMap<>();
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,2,1,t);
        assertEquals(2,v.eval(m,p1,t));
    }
//    @Test
//    public void TestEvalRandom() throws EvalError {
//        SpecialVariables s = new SpecialVariables("random");
//        HashMap<String,Integer> m = new HashMap<>();
//        Player p = new Player();
//        Territory t = new Territory();
//        double result = s.eval(m,p,t);
//        for(int i = 0;i<1000;i++){
//            assertTrue(result >= 0 && result<=1000);
//        }
//    }
}