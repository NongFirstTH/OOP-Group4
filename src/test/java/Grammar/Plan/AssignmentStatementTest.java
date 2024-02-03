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

public class AssignmentStatementTest {
    Territory t = new Territory(100,100);
    Player p = new Player(100,1,1,t);
    PlanTokenizer pt1 = new PlanTokenizer("123");
    PlanTokenizer pt2 = new PlanTokenizer("234");
    ExpressionParser e1 = new ExpressionParser(pt1);
    ExpressionParser e2 = new ExpressionParser(pt2);

    public AssignmentStatementTest() throws SyntaxError {
    }
    @Test
    public void assignmentTest_expr() throws SyntaxError, EvalError {
        AssignmentStatement assignmentStatement1 = new AssignmentStatement("A", e1.parse());
        AssignmentStatement assignmentStatement2 = new AssignmentStatement("B", e2.parse());
        assertEquals(123, assignmentStatement1.expr().eval(p, t));
        assertEquals(234, assignmentStatement2.expr().eval(p, t));
    }

    @Test
    public void assignmentTest_identifier() throws SyntaxError, EvalError {
        AssignmentStatement assignmentStatement1 = new AssignmentStatement("A", e1.parse());
        AssignmentStatement assignmentStatement2 = new AssignmentStatement("B", e2.parse());
        assertEquals("A", assignmentStatement1.identifier());
        assertEquals("B", assignmentStatement2.identifier());
    }
}