package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveCommandTest {

    @Test
    public void testMove_SuccessfulMove() {
        // Create an instance of the testing classes
        Player p = new Player();
        Territory t = new Territory();
        // Create a MoveCommand instance with a valid direction
        MoveCommand moveCommand1 = new MoveCommand(Direction.down);
        MoveCommand moveCommand2 = new MoveCommand(Direction.up);
        MoveCommand moveCommand3 = new MoveCommand(Direction.downright);
        MoveCommand moveCommand4 = new MoveCommand(Direction.upright);
        MoveCommand moveCommand5 = new MoveCommand(Direction.upleft);
        MoveCommand moveCommand6 = new MoveCommand(Direction.downleft);
        // Perform the move
        boolean result1 = moveCommand1.move(p, t);
        boolean result2 = moveCommand2.move(p, t);
        boolean result3 = moveCommand3.move(p, t);
        boolean result4 = moveCommand4.move(p, t);
        boolean result5 = moveCommand5.move(p, t);
        boolean result6 = moveCommand6.move(p, t);
        // Always false because we don't give them a budget
        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
        assertTrue(result4);
        assertTrue(result5);
        assertTrue(result6);
    }

    @Test
    public void testMove_InvalidDirection() {
        // Create an instance of the testing classes
        Player p = new Player();
        Territory t = new Territory();
        // Create a MoveCommand instance with an invalid direction
        MoveCommand moveCommand = new MoveCommand(Direction.left);
        // Perform the move
        boolean result = moveCommand.move(p, t);
        // Assert that the move was not successful due to an invalid direction
        assertFalse(result);
    }
}
