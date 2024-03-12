package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.GamePlay.GameFactory;
import com.websocket.demo.GamePlay.Player;
import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Expression.Expression;
import com.websocket.demo.Grammar.Parse.ExpressionParser;
import com.websocket.demo.Grammar.Parse.PlanTokenizer;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import com.websocket.demo.Grammar.Plan.AttackCommand;
import org.junit.jupiter.api.Test;
import static com.websocket.demo.Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;


public class AttackCommandTest {
    @Test
    public void shoot_Success() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame2P(1,1,2,2);
        Player p1 = g.getPlayer();

        PlanTokenizer tokenizer = new PlanTokenizer("10");
        ExpressionParser expressionParser = new ExpressionParser(tokenizer);
        Expression expr = expressionParser.parse();

        PlanTokenizer tokenizer1 = new PlanTokenizer("90");
        ExpressionParser expressionParser1 = new ExpressionParser(tokenizer1);
        Expression expr1 = expressionParser1.parse();

        AttackCommand shoot_dr1 = new AttackCommand(downright, expr);
        AttackCommand shoot_dr2 = new AttackCommand(downright, expr1);
        AttackCommand shoot_d = new AttackCommand(down,expr);
        assertTrue(shoot_dr1.eval(g));
        // budget decrease by 10 + 1
        assertEquals(9989,p1.getBudget());
        // deposit decrease by 10
        assertEquals(90,g.getTerritory().getRegion(2,2).getDeposit());

        // go to next turn change to p2
        g.nextTurn();
        Player p2 = g.getPlayer();
        // move to (2,1) then invest
        p2.move(downleft,g.getTerritory());
        p2.invest(15,g.getTerritory(),1000);

        // move to (3,1) then invest
        p2.move(down, g.getTerritory());
        p2.invest(15,g.getTerritory(),1000);

        // go to next turn change to p1
        g.nextTurn();
        assertTrue(shoot_d.eval(g));
        assertEquals(5, g.getTerritory().getRegion(2,1).getDeposit());
        assertTrue(shoot_d.eval(g));
        assertEquals(0, g.getTerritory().getRegion(2,1).getDeposit());
        assertNull(g.getTerritory().getRegion(2,1).getOwner());

        // shoot city center to make p2 loss
        assertTrue(shoot_dr2.eval(g));

        assertEquals(0,g.getTerritory().getRegion(2,2).getDeposit());
        assertNull(g.getTerritory().getRegion(2,2).getOwner());

        /* check if city center had been destroyed
           other region that this player occupied is unowned
           but deposit is still the same as before
         */
        assertNull(g.getTerritory().getRegion(3,1).getOwner());
        assertEquals(15, g.getTerritory().getRegion(3,1).getDeposit());
    }

    @Test
    public void shoot_unowned() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame2P(1,1,2,2);
        Player p1 = g.getPlayer();

        PlanTokenizer tokenizer = new PlanTokenizer("10");
        ExpressionParser expressionParser = new ExpressionParser(tokenizer);
        Expression expr = expressionParser.parse();

        AttackCommand shoot_ur = new AttackCommand(upright, expr);
        assertTrue(shoot_ur.eval(g));
        /* test if shoot to unowned region
           nothing happened but still pay
         */
        assertEquals(9989, p1.getBudget());
    }
    @Test
    public void shoot_notEnoughBudget() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame2P(1,1,2,2);
        Player p1 = g.getPlayer();

        PlanTokenizer tokenizer = new PlanTokenizer("10000");
        ExpressionParser expressionParser = new ExpressionParser(tokenizer);
        Expression expr = expressionParser.parse();

        AttackCommand shoot_ur = new AttackCommand(upright, expr);
        assertTrue(shoot_ur.eval(g));
        /* test if shoot to unowned region
           nothing happened but still pay
         */
        assertEquals(9999, p1.getBudget());
    }
}