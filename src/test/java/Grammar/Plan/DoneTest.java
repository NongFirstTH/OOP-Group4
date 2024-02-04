package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoneTest {
    @Test
    public void doneTest(){
        Territory t = new Territory(100,100);
        Player p = new Player(100,1,1,t);
        Done done = new Done();
        assertFalse(done.eval(p, t));
    }
}