package Grammar.Plan;

import GamePlay.Game;
import GamePlay.GameFactory;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import static  Grammar.Plan.Direction.*;
import static org.junit.Assert.*;

public class MoveCommandTest {
    MoveCommand d = new MoveCommand(down);
    MoveCommand u = new MoveCommand(up);
    MoveCommand dr = new MoveCommand(downright);
    MoveCommand ur = new MoveCommand(upright);
    MoveCommand ul = new MoveCommand(upleft);
    MoveCommand dl = new MoveCommand(downleft);
    @Test
    public void move_Success() throws SyntaxError, EvalError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame2P(3,4,2,5);
        Player p = g.getPlayer();

        //test move to opponent's region
        assertTrue(ur.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        System.out.println("Current budget: " + p.getBudget());
        assertEquals(3,p.getCurrow());
        assertEquals(4,p.getCurcol());
        assertEquals(9999,p.getBudget());

        //Test down direction
        assertTrue(d.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        System.out.println("Current budget: " + p.getBudget());
        assertEquals(4,p.getCurrow());
        assertEquals(4,p.getCurcol());
        assertEquals(9998,p.getBudget());

        //test up direction
        assertTrue(u.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        System.out.println("Current budget: " + p.getBudget());
        assertEquals(3,p.getCurrow());
        assertEquals(4,p.getCurcol());
        assertEquals(9997,p.getBudget());

        //test down-right direction
        assertTrue(dr.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        System.out.println("Current budget: " + p.getBudget());
        assertEquals(3,p.getCurrow());
        assertEquals(5,p.getCurcol());
        assertEquals(9996,p.getBudget());

        //test up-right direction
        assertTrue(ur.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        System.out.println("Current budget: " + p.getBudget());
        assertEquals(3,p.getCurrow());
        assertEquals(6,p.getCurcol());
        assertEquals(9995,p.getBudget());

        //test up-left direction
        assertTrue(dl.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        System.out.println("Current budget: " + p.getBudget());
        assertEquals(3,p.getCurrow());
        assertEquals(5,p.getCurcol());
        assertEquals(9994,p.getBudget());

        //test down-left direction
        assertTrue(ul.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        System.out.println("Current budget: " + p.getBudget());
        assertEquals(3,p.getCurrow());
        assertEquals(4,p.getCurcol());
        assertEquals(9993,p.getBudget());
    }

    @Test
    public void move_Fail() throws EvalError, SyntaxError {
        GameFactory gameFactory = new GameFactory();
        Game g = gameFactory.newGame1P(2,5);
        Player p =  g.getPlayer();
        Territory t = g.getTerritory();
        //make player have 0 budget with amount 9999 + 1
        p.invest(9999,t,10000);

        assertFalse(d.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        assertFalse(u.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        assertFalse(dr.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        assertFalse(ur.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        assertFalse(ul.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
        assertFalse(dl.eval(g));
        System.out.println("Current position: " + "(" + p.getCurrow() + ", " + p.getCurcol() + ")");
    }
}
