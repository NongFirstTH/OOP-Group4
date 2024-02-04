package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class AttackCommandTest {
    Territory t = new Territory(20,20);
    Player p1 = new Player(100,1,1,t);
    Player p2 = new Player(100,1,1,t);
    PlanTokenizer pt1 = new PlanTokenizer("10");
    ExpressionParser e1 = new ExpressionParser(pt1);

    public AttackCommandTest() throws SyntaxError {
    }


    @Test
    public void AttackTest() throws SyntaxError {
        AttackCommand attackCommand = new AttackCommand(Direction.up, e1.parse());
        p1.shoot(Direction.up,1);
        assertEquals(98,p1.getBudget());
    }
}