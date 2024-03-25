package com.websocket.demo.GamePlay;

import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {
    Game g = new Game(3,3);
    private Region r = new Region();
    private Territory t = g.getTerritory();
    private Player p = new Player("Knack",100,1,1,100,t);
    public RegionTest() throws SyntaxError, EvalError {
    }


    @Test
    public void testGetDeposit() {
        double expectedDeposit = 0.0;
        assertEquals(expectedDeposit, r.getDeposit());
    }

    @Test
    public void testGetInterest() {
        double baseInterestRate = 5;
        int currentTurn = 20;
        long maxDeposit = 10000;
        double newInterestRate = baseInterestRate * Math.log10(maxDeposit) * Math.log(currentTurn);
        r.setCityCenter(p, 10000);
        double Interest1 = (newInterestRate/100) * maxDeposit;
        double Interest2 = r.getInterest(baseInterestRate,currentTurn);
        assertEquals(Interest1, Interest2);
    }


    @Test
    public void testDepositCal() {
        double baseInterestRate = 0.05; // 50%
        int currentTurn = 20;
        long maxDeposit = 10000;
        r.setCityCenter(p, 1000);
        double expectedDeposit = 1000 + 1000 * (0.05 * Math.log10(1000) * Math.log(20)) / 100.0;
        assertEquals(expectedDeposit, r.depositCal(baseInterestRate, currentTurn, maxDeposit));
    }

    @Test
    public void testInterestRateCal() {
        double baseInterestRate = 0.05; // 5%
        int currentTurn = 5;
        r.setCityCenter(p, 1000);
        double expectedInterestRate = baseInterestRate * Math.log10(1000) * Math.log(5);
        assertEquals(expectedInterestRate, r.interestRateCal(baseInterestRate, currentTurn));
    }

    @Test
    public void testBeInvested() {
        long maxDeposit = 1000;
        long amountToInvest = 500;
        r.setCityCenter(p,500);  // Set initial deposit to half of maxDeposit
        r.beInvested(amountToInvest, p, maxDeposit);
        assertEquals(maxDeposit, r.getDeposit());
    }

    @Test
    public void testBeCollected(){
        long initialDeposit = 100;
        r.setCityCenter(p, initialDeposit);
        r.beCollected(10,g);
        assertEquals(90, r.getDeposit());
    }

    @Test
    public void testBeCollectedLostRegion() {
        long initialDeposit = 100;
        r.setCityCenter(p, initialDeposit);
        long amountToCollect = initialDeposit;
        long collectedAmount = r.beCollected(amountToCollect, g);
        assertEquals(amountToCollect, collectedAmount);
        assertNull(r.getOwner());
        assertEquals(0.0, r.getDeposit());
    }

    @Test
    public void testBeShot(){
        long initialDeposit = 100;
        long amountToShoot = 50;
        r.setCityCenter(p,initialDeposit);
        r.beShot(amountToShoot,g);
        assertEquals(50,r.getDeposit());
    }

    @Test
    public void testBeShotNegativeDeposit() {
        long initialDeposit = 50;
        long amountToShoot = 100;
        r.setCityCenter(p,initialDeposit);
        r.beShot(amountToShoot, g);
        assertEquals(0.0, r.getDeposit());
    }
}
