package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;

import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {
    private Region r = new Region();
    private Territory t = new Territory(100,100);
    private Player p = new Player("Knack",100,1,1,100,t);
    public RegionTest() throws SyntaxError, EvalError {
    }


    @Test
    public void testGetDeposit() {
        double expectedDeposit = 0.0;
        assertEquals(expectedDeposit, r.getDeposit());
    }

//    @Test
//    public void testGetInterest() {
//        double baseInterest = 1000.0;
//        int currentTurn = 20;
//        long maxDeposit = 10000;
//        long expectedInterest = 0;
//        assertEquals(expectedInterest, r.getInterest(baseInterest, currentTurn, maxDeposit));
//    }

    @Test
    public void testDepositCal() {
        double baseInterestRate = 0.05; // 50%
        int currentTurn = 20;
        long maxDeposit = 10000;
        r.setCityCenter(p, 1000);
        double expectedDeposit = 1000 + 1000 * (0.05 * Math.log10(1000) * Math.log(20)) / 100.0;
        assertEquals(expectedDeposit, r.depositCal(baseInterestRate, currentTurn, maxDeposit));
    }

//    @Test
//    public void testInterestRateCal() {
//        double baseInterestRate = 0.05; // 5%
//        int currentTurn = 5;
//        r.setCityCenter(p, 1000);
//        double expectedInterestRate = baseInterestRate * Math.log10(1000) * Math.log(5);
//        assertEquals(expectedInterestRate, r.interestRateCal(baseInterestRate, currentTurn));
//    }
}
