package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class AttackCommandTest {
    Territory t = new Territory(20,20);
    Player p1 = new Player(100,1,1,t);
    Player p2 = new Player(100,1,1,t);
    @Test
    public void AttackTest(){
//        AttackCommand attackCommand = new AttackCommand(Direction.up, new Expression() {
//            @Override
//            public long eval(Map<String, Long> bindings, Player p, Territory t) throws EvalError {
//                return 10;
//            }
//            @Override
//            public void prettyPrint(StringBuilder s) {
//
//            }
//        });
//        p1.shoot(Direction.up,1);
//        assertEquals(99,p1.getBudget());
    }
}