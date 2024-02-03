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
        Territory t = new Territory(20,20);
        Player p1 = new Player(10,1,2,t);
        return a.eval(p1,t);
    }
    @Test
    public void TestEval() throws SyntaxError, EvalError {
        assertEquals(5,result(5));
        assertEquals(-7,result(-7));
        assertEquals(100000000,result(100000000));
    }

    public String resultPrettyPrint(long num){
        IntLit a = new IntLit(num);
        StringBuilder s = new StringBuilder();
        a.prettyPrint(s);
        return s.toString();
    }
    @Test
    public  void TestPrettyPrint(){
        assertEquals("2",resultPrettyPrint(2));
        assertEquals("-2",resultPrettyPrint(-2));
        assertEquals("6541232",resultPrettyPrint(6541232));
        assertEquals("-6541232",resultPrettyPrint(-6541232));
    }
}