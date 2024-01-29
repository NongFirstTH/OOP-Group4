package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SpecialVariablesTest {

    @Test
    public void TestEvalRandom() throws EvalError {
        SpecialVariables s = new SpecialVariables("random");
        HashMap<String,Integer> m = new HashMap<>();
        Player p = new Player();
        Territory t = new Territory();
        double result = s.eval(m,p,t);
        for(int i = 0;i<1000;i++){
            assertTrue(result >= 0 && result<=1000);
        }
    }
}