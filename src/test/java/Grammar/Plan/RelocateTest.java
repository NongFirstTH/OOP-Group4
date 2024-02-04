package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RelocateTest {
    Territory t = new Territory(100,100);
    Player p1 = new Player(100,1,1,t);
    Player p2 = new Player(0,1,1,t);
    @Test
    public void relocateTest_pass(){
        Relocate relocate = new Relocate();

    }

    @Test
    public void relocatedTest_fail(){

    }
}