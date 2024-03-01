package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.GamePlay.GameFactory;
import com.websocket.demo.GamePlay.Player;
import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Expression.Expression;
import com.websocket.demo.Grammar.Expression.Variable;
import com.websocket.demo.Grammar.Parse.ExpressionParser;
import com.websocket.demo.Grammar.Parse.PlanTokenizer;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import com.websocket.demo.Grammar.Plan.AssignmentStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentStatementTest {
    @Test
    public void Assignment_notSame() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(1,1);
        Player p = g.getPlayer();

        PlanTokenizer tokenizer1 = new PlanTokenizer("123");
        ExpressionParser expressionParser1 = new ExpressionParser(tokenizer1);
        Expression expr1 = expressionParser1.parse();
        AssignmentStatement assignmentStatement1 = new AssignmentStatement("a",expr1);
        // "a" collect 123

        PlanTokenizer tokenizer2 = new PlanTokenizer("234");
        ExpressionParser expressionParser2 = new ExpressionParser(tokenizer2);
        Expression expr2 = expressionParser2.parse();
        AssignmentStatement assignmentStatement2 = new AssignmentStatement("b",expr2);
        // b collect 234

        assertTrue(assignmentStatement1.eval(g));
        assertTrue(assignmentStatement2.eval(g));

        assertEquals(123, new Variable("a").eval(g));
        assertEquals(234, new Variable("b").eval(g));
        System.out.println(new Variable("a").eval(g));
        System.out.println(new Variable("b").eval(g));
    }
    @Test
    public void Assignment_SameName() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(1, 1);
        Player p = g.getPlayer();

        PlanTokenizer tokenizer1 = new PlanTokenizer("123");
        ExpressionParser expressionParser1 = new ExpressionParser(tokenizer1);
        Expression expr1 = expressionParser1.parse();
        AssignmentStatement assignmentStatement1 = new AssignmentStatement("a",expr1);
        assertTrue(assignmentStatement1.eval(g));
        System.out.println(new Variable("a").eval(g));
        assertEquals(123, new Variable("a").eval(g));
        // "a" collect 123

        PlanTokenizer tokenizer2 = new PlanTokenizer("234");
        ExpressionParser expressionParser2 = new ExpressionParser(tokenizer2);
        Expression expr2 = expressionParser2.parse();
        AssignmentStatement assignmentStatement2 = new AssignmentStatement("a",expr2);
        assertTrue(assignmentStatement2.eval(g));
        System.out.println(new Variable("a").eval(g));
        assertEquals(234, new Variable("a").eval(g));
        // now "a" change to collect 234
    }
}
