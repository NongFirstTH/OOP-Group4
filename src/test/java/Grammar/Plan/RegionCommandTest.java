package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import org.junit.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RegionCommandTest {
    @Test
    public void investCommand_over(){
        RegionCommand regionCommand = new RegionCommand("invest", new Expression() {
            @Override
            public long eval(Map<String, Long> bindings, Player p, Territory t) throws EvalError {
                return 50;
            }
            @Override
            public void prettyPrint(StringBuilder s) {
            }
        });
        Territory t = new Territory(100,100);
        Player p = new Player(100,1,1,t);
        // Make sure to handle the exception or add 'throws EvalError' to the method signature
        assertDoesNotThrow(() -> regionCommand.eval(null, p, t));
        assertEquals(49, p.getBudget());
    }

    @Test
    public void investCommand_lesser(){
        RegionCommand regionCommand = new RegionCommand("invest", new Expression() {
            @Override
            public long eval(Map<String, Long> bindings, Player p, Territory t) throws EvalError {
                return 200;
            }
            @Override
            public void prettyPrint(StringBuilder s) {
            }
        });
        Territory t = new Territory(100,100);
        Player p = new Player(100,1,1,t);
        // Make sure to handle the exception or add 'throws EvalError' to the method signature
        assertDoesNotThrow(() -> regionCommand.eval(null, p, t));
        assertEquals(99, p.getBudget());
    }

    @Test
    public void collectCommand(){
        RegionCommand regionCommand = new RegionCommand("collect", new Expression() {
            @Override
            public long eval(Map<String, Long> bindings, Player p, Territory t) throws EvalError {
                return 10;
            }
            @Override
            public void prettyPrint(StringBuilder s) {
            }
        });
        Territory t = new Territory(100,100);
        Player p = new Player(100,1,1,t);
        // Make sure to handle the exception or add 'throws EvalError' to the method signature
        assertDoesNotThrow(() -> regionCommand.eval(null, p, t));
        assertEquals(99,p.getBudget());
    }

    @Test
    public void collectCommand_0() throws EvalError {
        RegionCommand regionCommand = new RegionCommand("collect", new Expression() {
            @Override
            public long eval(Map<String, Long> bindings, Player p, Territory t) throws EvalError {
                return 100;
            }
            @Override
            public void prettyPrint(StringBuilder s) {
            }
        });
        Territory t = new Territory(100,100);
        Player p = new Player(0,1,1,t);
        // Make sure to handle the exception or add 'throws EvalError' to the method signature
        assertDoesNotThrow(() -> regionCommand.eval(null, p, t));
        assertFalse(regionCommand.eval(null, p, t));
    }
}