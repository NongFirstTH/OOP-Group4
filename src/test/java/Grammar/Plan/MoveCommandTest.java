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
        MoveCommand moveCommand1 = new MoveCommand("left");
        MoveCommand moveCommand2 = new MoveCommand("right");
        MoveCommand moveCommand3 = new MoveCommand("up");
        MoveCommand moveCommand4 = new MoveCommand("down");
        // Perform the move
        boolean result1 = moveCommand1.move(p, t);
        boolean result2 = moveCommand2.move(p, t);
        boolean result3 = moveCommand3.move(p, t);
        boolean result4 = moveCommand4.move(p, t);
        // Always false because we don't give them a budget
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
        assertFalse(result4);
    }

    @Test
    public void testMove_InvalidDirection() {
        // Create an instance of the testing classes
        Player p = new Player();
        Territory t = new Territory();
        // Create a MoveCommand instance with an invalid direction
        MoveCommand moveCommand = new MoveCommand("546543213");
        // Perform the move
        boolean result = moveCommand.move(p, t);
        // Assert that the move was not successful due to an invalid direction
        assertFalse(result);
    }
}
