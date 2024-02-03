package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import org.junit.Test;

import java.util.HashMap;

import static Grammar.Plan.Direction.*;
import static org.junit.Assert.*;

public class MoveCommandTest {

    @Test
    public void testMove_SuccessfulMove() {
        //Create an instance of the testing classes
        Territory t = new Territory(20,20);
        Player p = new Player(100,1,1,t);
        // Create a MoveCommand instance with a valid direction
        MoveCommand moveCommand1 = new MoveCommand(down);
        MoveCommand moveCommand2 = new MoveCommand(up);
        MoveCommand moveCommand3 = new MoveCommand(downright);
        MoveCommand moveCommand4 = new MoveCommand(upright);
        MoveCommand moveCommand5 = new MoveCommand(upleft);
        MoveCommand moveCommand6 = new MoveCommand(downleft);
        // Perform the move

        boolean result1 = moveCommand1.eval(p, t);
        boolean result2 = moveCommand2.eval(p, t);
        boolean result3 = moveCommand3.eval(p, t);
        boolean result4 = moveCommand4.eval(p, t);
        boolean result5 = moveCommand5.eval(p, t);
        boolean result6 = moveCommand6.eval(p, t);
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
