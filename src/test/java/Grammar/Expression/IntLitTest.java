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

public class IntLitTest {
    public long result(long num) throws SyntaxError, EvalError {
        IntLit a = new IntLit(num);
        Map<String,Long> m = new HashMap<>();
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,1,2,t);
        return a.eval(m,p1,t);
    }
    @Test
    public void TestEval() throws SyntaxError, EvalError {
        assertEquals(5,result(5));
        assertEquals(-7,result(-7));
        assertEquals(100000000,result(100000000));
    }

}