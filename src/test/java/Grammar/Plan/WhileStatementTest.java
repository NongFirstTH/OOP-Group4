package Grammar.Plan;

import GamePlay.Game;
import GamePlay.GameFactory;
import GamePlay.Player;
import Grammar.Expression.*;
import Grammar.Parse.PlanParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import static Grammar.Plan.Direction.upleft;
import static Grammar.Plan.Direction.upright;
import static org.junit.jupiter.api.Assertions.*;

public class WhileStatementTest {

    @Test
    public void testNegExpr() throws EvalError {
        Plan planTrue = new NoStatement();

        Expression exprNeg = new IntLit(-1);

        assertTrue(new WhileStatement(exprNeg, planTrue).eval(null));
    }

    @Test
    public void testEvalExprReduce() throws EvalError, SyntaxError {
        String s = """
                x = 10
                while (x) {
                \tcount = count + 1
                \tx = x - 1
                }""";

        Plan while10L = new PlanParser(new PlanTokenizer(s)).parse();

        GameFactory factory = new GameFactory();
        Game g = factory.newGame1P(1, 1);

        assertTrue(while10L.eval(g));
        assertEquals(10, new Variable("count").eval(g));
    }

    @Test
    public void testEvalP1False() throws EvalError, SyntaxError {
        String s = """
                while (1) {
                \tcount = count + 1
                \tdone
                }""";

        Plan while10L = new PlanParser(new PlanTokenizer(s)).parse();

        GameFactory factory = new GameFactory();
        Game g = factory.newGame1P(1, 1);

        assertFalse(while10L.eval(g));
        assertEquals(1, new Variable("count").eval(g));
    }

    @Test
    public void testDoneInLoop() throws EvalError, SyntaxError {
        String s = """
                while (1) {
                \tcount = count + 1
                \tinvest budget/2
                \tcollect 1
                }""";

        Plan while10L = new PlanParser(new PlanTokenizer(s)).parse();


        GameFactory factory = new GameFactory();
        Game g = factory.newGame1P(1, 1);

        assertFalse(while10L.eval(g));
        assertEquals(13, new Variable("count").eval(g));
    }

    @Test
    public void testEvalInfinityLoop() throws EvalError, SyntaxError {
        String s = """
                while (1) {
                \tcount = count + 1
                }""";

        Plan while10L = new PlanParser(new PlanTokenizer(s)).parse();

        GameFactory factory = new GameFactory();
        Game g = factory.newGame1P(1, 1);

        assertTrue(while10L.eval(g));
        assertEquals(10000, new Variable("count").eval(g));
    }
}