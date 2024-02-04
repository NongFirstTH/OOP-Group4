package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RegionCommandTest {
    public RegionCommandTest() throws SyntaxError {
    }

    PlanTokenizer pt1 = new PlanTokenizer("50");
    ExpressionParser e1 = new ExpressionParser(pt1);
    @Test
    public void investCommand_over() throws SyntaxError {

        RegionCommand regionCommand = new RegionCommand("invest", e1.parse());
        Territory t = new Territory(100,100);
        Player p = new Player(100,1,1,t);
        // Make sure to handle the exception or add 'throws EvalError' to the method signature
        assertDoesNotThrow(() -> regionCommand.eval(p, t));
        assertEquals(49, p.getBudget());
    }

    PlanTokenizer pt2 = new PlanTokenizer("200");
    ExpressionParser e2 = new ExpressionParser(pt2);
    @Test
    public void investCommand_lesser() throws SyntaxError {

        RegionCommand regionCommand = new RegionCommand("invest", e2.parse());
        Territory t = new Territory(100,100);
        Player p = new Player(100,1,1,t);
        // Make sure to handle the exception or add 'throws EvalError' to the method signature
        assertDoesNotThrow(() -> regionCommand.eval(p, t));
        assertEquals(99, p.getBudget());

    }

    PlanTokenizer pt3 = new PlanTokenizer("10");
    ExpressionParser e3 = new ExpressionParser(pt3);
    @Test
    public void collectCommand() throws SyntaxError {

        RegionCommand regionCommand = new RegionCommand("collect",e3.parse());
        Territory t = new Territory(100,100);
        Player p = new Player(100,1,1,t);
        // Make sure to handle the exception or add 'throws EvalError' to the method signature
        assertDoesNotThrow(() -> regionCommand.eval(p, t));
        assertEquals(99,p.getBudget());

    }
    PlanTokenizer pt4 = new PlanTokenizer("100");
    ExpressionParser e4 = new ExpressionParser(pt4);
    @Test
    public void collectCommand_0() throws EvalError, SyntaxError {

        RegionCommand regionCommand = new RegionCommand("collect", e4.parse());
        Territory t = new Territory(100,100);
        Player p = new Player(0,1,1,t);
        // Make sure to handle the exception or add 'throws EvalError' to the method signature
        assertDoesNotThrow(() -> regionCommand.eval(p, t));
        assertFalse(regionCommand.eval(p, t));
    }

    PlanTokenizer pt5 = new PlanTokenizer("111");
    ExpressionParser e5 = new ExpressionParser(pt5);
    @Test
    public void unknownCommand() throws SyntaxError {

        RegionCommand regionCommand = new RegionCommand("find", e5.parse());
        Territory t = new Territory(100,100);
        Player p = new Player(100,1,1,t);
        assertThrows(EvalError.class, () -> regionCommand.eval(p, t));
    }
}