package Grammar.Plan;

import GamePlay.*;
import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;
import org.junit.Test;
import static  Grammar.Plan.Direction.*;

import static org.junit.jupiter.api.Assertions.*;

public class RelocateTest {

    @Test
    public void relocatePass() throws SyntaxError, EvalError {
        Relocate relocate = new Relocate();
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(1,1);
        Player p = g.getPlayer();
        // have enough budget move city center from (1,1) to (4,1)
        p.move(down,g.getTerritory());
        p.invest(1,g.getTerritory(),10000);
        p.move(down,g.getTerritory());
        p.invest(1,g.getTerritory(),10000);
        p.move(down,g.getTerritory());
        p.invest(1,g.getTerritory(),10000);
        // location of city center before relocate
        System.out.println(p.getCol() + ", " + p.getRow());
        assertFalse(relocate.eval(g));
        // location of city center after relocate
        System.out.println(p.getCol() + ", " + p.getRow());
        assertEquals(9966,p.getBudget());
        assertEquals(4,p.getRow());
        assertEquals(1,p.getCol());
    }

    @Test
    public void relocateFail() throws SyntaxError, EvalError {
        Relocate relocate = new Relocate();
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(1,1);
        Player p = g.getPlayer();
        // make player have 0 budget
        p.invest(9990,g.getTerritory(),10000);
        p.move(down,g.getTerritory());
        p.invest(1,g.getTerritory(),10000);
        p.move(down,g.getTerritory());
        p.invest(1,g.getTerritory(),10000);
        p.move(down,g.getTerritory());
        p.invest(1,g.getTerritory(),10000);
        System.out.println(p.getCol() + ", " + p.getCol());
        // player don't have enough budget to invest
        assertFalse(relocate.eval(g));
        System.out.println(p.getCol() + ", " + p.getRow());
        // collect deposit from region to make it unowned
        p.collect(1,g);
        System.out.println(p.getCol() + ", " + p.getRow());
        // relocate unowned region
        assertFalse(relocate.eval(g));
    }
}