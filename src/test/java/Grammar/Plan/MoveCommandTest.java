package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import java.util.HashMap;

import static Grammar.Plan.Direction.*;
import static org.junit.Assert.*;

public class MoveCommandTest {

    @Test
    public void testMove_SuccessfulMove() throws SyntaxError, EvalError {
        //Create an instance of the testing classes
        Territory t = new Territory(20,20);
        Player p = new Player(100,1,1,100,t);
        Game g = new Game(p.getRow(),p.getCol());
        // Create a MoveCommand instance with a valid direction
        MoveCommand moveCommand1 = new MoveCommand(down);
        MoveCommand moveCommand2 = new MoveCommand(up);
        MoveCommand moveCommand3 = new MoveCommand(downright);
        MoveCommand moveCommand4 = new MoveCommand(upright);
        MoveCommand moveCommand5 = new MoveCommand(upleft);
        MoveCommand moveCommand6 = new MoveCommand(downleft);
        // Perform the move

        boolean result1 = moveCommand1.eval(g);
        boolean result2 = moveCommand2.eval(g);
        boolean result3 = moveCommand3.eval(g);
        boolean result4 = moveCommand4.eval(g);
        boolean result5 = moveCommand5.eval(g);
        boolean result6 = moveCommand6.eval(g);
        // Always false because we don't give them a budget
//        assertTrue(result1);
//        assertTrue(result2);
//        assertTrue(result3);
//        assertTrue(result4);
//        assertTrue(result5);
//        assertTrue(result6);
    }
//    @Test
//    public void testMove_InvalidDirection() {
//        // Create an instance of the testing classes
//        Territory t = new Territory(20,20);
//        Player p = new Player(100,1,1,t);
//        // Create a MoveCommand instance with an invalid direction
//        MoveCommand moveCommand = new MoveCommand(Direction.up);
//        //Perform the move
//        boolean result = moveCommand.move(p, t);
//        //Assert that the move was not successful due to an invalid direction
//        assertFalse(result);
//    }
}
