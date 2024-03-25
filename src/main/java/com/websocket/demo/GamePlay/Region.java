package com.websocket.demo.GamePlay;

import com.websocket.demo.GamePlay.Wrapper.RegionWrap;

interface RegionI {
    double getDeposit();
    double getInterest(double baseInterestRate, int currentTurn) ;
    void beInvested(long amount, Player p, long maxDeposit);
    long beCollected(long amount, Game g);
    void beShot(long amount, Game g);
    double depositCal(double baseInterestRate, int currentTurn, long maxDeposit);
    Player getOwner();
    void setCityCenter(Player p, long init_center_dep);
    void lost();
    RegionWrap wrap();
}
public class Region implements RegionI {
    private Player owner;
    private double deposit;

    @Override
    public double getDeposit() {
        return deposit;
    }

    @Override
    public double depositCal(double baseInterestRate, int currentTurn, long maxDeposit) {
        deposit += getInterest(baseInterestRate, currentTurn);
        if(deposit >= maxDeposit){
            deposit = maxDeposit;
        }
        return deposit;
    }

    @Override
    public double getInterest(double baseInterestRate, int currentTurn) {
        double calculatedInterestRate = interestRateCal(baseInterestRate, currentTurn);
        return deposit * (calculatedInterestRate / 100.0);
    }

    private double interestRateCal(double baseInterestRate, int currentTurn) {
        return baseInterestRate * Math.log10(deposit) * Math.log(currentTurn);
    }

    @Override
    public void beInvested(long amount, Player p, long maxDeposit) {
        owner = p;
        double currentDeposit  = deposit + amount;
        if (deposit >= maxDeposit) {
            deposit = maxDeposit;
        } else {
            deposit = currentDeposit;
        }
    }

    @Override
    public long beCollected(long amount, Game g) {
        if(amount <= deposit){
            deposit -= amount;
            if(deposit < 1){
                deposit = 0;
                owner.lostRegion(this,g);
                owner = null;
            }
            return amount;
        }else{
            return 0;
        }
    }

    @Override
    public void beShot(long amount, Game g) {
        deposit -= amount;
        if(deposit < 1){
            deposit = 0;
            if(owner != null){
                owner.lostRegion(this, g);
                owner = null;
            }
        }
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setCityCenter(Player p, long init_center_dep) {
        owner = p;
        deposit = init_center_dep;
    }

    @Override
    public void lost() {
        owner = null;
    }

    @Override
    public RegionWrap wrap() {
        return new RegionWrap(owner==null?null:owner.getName(), (long) deposit);
    }
}
