package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Direction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static Grammar.Plan.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class CityCrewTest {

    private void assertMove(int i, int j,Game g,Direction direction) throws EvalError {
        g.getPlayer().move(direction,g.getTerritory());
        assertEquals(i,g.getPlayer().getCurrow());
        assertEquals(j,g.getPlayer().getCurcol());
    }
    @Test
    void Testmove() throws SyntaxError, EvalError {
        Territory t = new Territory(3,3);
        Player p = new Player("A",1000,1,1,100,t);
        List<Player> l = new ArrayList<>();
        l.add(p);
        Game g = new Game(3,3);
        g.addPlayer("A",1,1,null);

//        assertMove(1,1,g,up);
//
//        assertMove(2,2,g,downright);
//        assertMove(2,3,g,downright);
//        assertMove(2,3,g,downright);

//        assertMove(1,2,g,upright);
//        assertMove(1,2,g,upright);

//        assertMove(2,1,g,down);
//        assertMove(3,1,g,down);
//        assertMove(3,1,g,down);

//        assertMove(1,1,g,downleft);
//        assertMove(2,2,g,downright);
//        assertMove(2,1,g,downleft);

//        assertMove(1,1,g,upleft);
//        assertMove(1,1,g,upleft);

        assertMove(2,1,g,down);
        assertMove(2,2,g,upright);
        assertMove(2,3,g,downright);
        assertMove(3,2,g,downleft);
        assertMove(2,1,g,upleft);
        assertMove(1,1,g,up);

    }

}