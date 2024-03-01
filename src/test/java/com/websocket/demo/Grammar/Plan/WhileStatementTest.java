package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.GamePlay.GameFactory;
import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Expression.Expression;
import com.websocket.demo.Grammar.Expression.IntLit;
import com.websocket.demo.Grammar.Expression.Variable;
import com.websocket.demo.Grammar.Parse.PlanParser;
import com.websocket.demo.Grammar.Parse.PlanTokenizer;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import com.websocket.demo.Grammar.Plan.NoStatement;
import com.websocket.demo.Grammar.Plan.Plan;
import com.websocket.demo.Grammar.Plan.WhileStatement;
import org.junit.jupiter.api.Test;

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