package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.GameFactory;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Expression.Expression;
import com.websocket.demo.Grammar.Parse.ExpressionParser;
import com.websocket.demo.Grammar.Parse.PlanTokenizer;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.GamePlay.Player;
import org.junit.jupiter.api.Test;


import static com.websocket.demo.Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

public class RegionCommandTest {
    @Test
    public void invest_enoughBudget() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(1,1);
        Player p = g.getPlayer();

        PlanTokenizer tokenizer = new PlanTokenizer("500");
        ExpressionParser expressionParser = new ExpressionParser(tokenizer);
        Expression expr = expressionParser.parse();

        RegionCommand invest = new RegionCommand("invest", expr);
        assertTrue(invest.eval(g));
        /* budget = 10000 want to invest 500
        so budget left = 10000 - (500 + 1) = 9499
         */
        assertEquals(9499, p.getBudget());
        /* deposit = 100  want to invest 500
        so new deposit = 100 + 500 = 600
         */
        assertEquals(600,p.getCityCenter(g.getTerritory()).getDeposit());
        /* we still in city center so we can call getCityCenter(g.getTerritory()).getDeposit()
         or call getDeposit()
         */
    }

    @Test
    public void invest_notEnoughBudget() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(1, 1);
        Player p = g.getPlayer();

        PlanTokenizer tokenizer = new PlanTokenizer("12000");
        ExpressionParser expressionParser = new ExpressionParser(tokenizer);
        Expression expr = expressionParser.parse();

        RegionCommand invest = new RegionCommand("invest", expr);
        assertTrue(invest.eval(g));
        /* budget = 10000 want to invest 12000 not enough budget no op
           but still have to pay cost so budget left = 10000 - 1 = 9999
         */
        assertEquals(9999, p.getBudget());
        /* we have 100 deposit but no op
           so deposit not change
         */
        assertEquals(100, p.getDeposit(g.getTerritory()));
        /* we still in city center so we can call getCityCenter(g.getTerritory()).getDeposit()
         or call getDeposit()
         */
    }

    @Test
    public void collect_enoughBudget() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(1, 1);
        Player p = g.getPlayer();
        // move away from city center to (2,2) budget = 9999
        p.move(downright, g.getTerritory());
        PlanTokenizer tokenizer1 = new PlanTokenizer("5");
        ExpressionParser expressionParser1 = new ExpressionParser(tokenizer1);
        Expression expr1 = expressionParser1.parse();
        PlanTokenizer tokenizer2 = new PlanTokenizer("10");
        ExpressionParser expressionParser2 = new ExpressionParser(tokenizer2);
        Expression expr2 = expressionParser2.parse();
        PlanTokenizer tokenizer3 = new PlanTokenizer("15");
        ExpressionParser expressionParser3 = new ExpressionParser(tokenizer3);
        Expression expr3 = expressionParser3.parse();


        // Case if collect lesser than deposit
        // we invest in new region first budget = 9999 - (10 + 1) = 9988
        RegionCommand invest = new RegionCommand("invest", expr2);
        invest.eval(g);
        // we collect 5, so we should have 9988 + (5 - 1) = 9992
        RegionCommand collect1 = new RegionCommand("collect", expr1);
        assertTrue(collect1.eval(g));
        assertEquals(9992, p.getBudget());
        assertEquals(5, p.getDeposit(g.getTerritory()));

        // Case if collect over than deposit
        // we want to collect 15, but we only have deposit 5 so no op
        RegionCommand collect2 = new RegionCommand("collect", expr3);
        assertTrue(collect2.eval(g));
        assertEquals(9991, p.getBudget());
        assertEquals(5, p.getDeposit(g.getTerritory()));

        // Case if collect = deposit, lost that region
        assertTrue( collect1.eval(g));// use collect 1 because collect 5 equal budget
        assertEquals(9995, p.getBudget());
        assertEquals(0, p.getDeposit(g.getTerritory()));
        assertNull(g.getTerritory().getOwner(2, 2));
    }

    @Test
    public void collect_Budget0() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(1,1);
        Player p = g.getPlayer();
        p.move(downright,g.getTerritory());

        PlanTokenizer tokenizer = new PlanTokenizer("9998");
        ExpressionParser expressionParser = new ExpressionParser(tokenizer);
        Expression expr = expressionParser.parse();

        RegionCommand invest = new RegionCommand("invest", expr);
        invest.eval(g);
        RegionCommand collect = new RegionCommand("collect", expr);
        /* have 0 budget but if want to collect need at least 1 budget to
           pay for unit cost of the command
         */
        assertFalse(collect.eval(g));
    }

    @Test
    public void invalid_Command() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame0P();

        String command = "cmd";
        PlanTokenizer tokenizer = new PlanTokenizer("12000");
        ExpressionParser expressionParser = new ExpressionParser(tokenizer);
        Expression expr = expressionParser.parse();

        RegionCommand regionCommand = new RegionCommand(command, expr);
        assertThrows(EvalError.class, ()->regionCommand.eval(g));
    }
}