package GamePlay;

import Grammar.Plan.Direction;

interface RegionI {
    double deposit();
    long getInterest();
    void BeRelocated();
    void beInvested(long amount,Player p);
    long beCollected(long amount);
    void beShot(long amount);
    double depositCal(double baseInterestRate, int currentTurn);
    double interestRateCal(double baseInterestRate, int currentTurn);
    Player getOwner();
    void setOwner(Player p);
}
public class Region implements RegionI {
    private Player owner;
    private double deposit;
    private long interest;

    @Override
    public double deposit() {
        return deposit;
    }

    @Override
    public long getInterest() {
        return interest;
    }

    @Override
    public void BeRelocated() {

    }

    @Override
    public void beInvested(long amount,Player p) {

    }

    @Override
    public long beCollected(long amount) {
        return 0;
    }

    @Override
    public void beShot(long amount) {

    }

    @Override
    public double depositCal(double baseInterestRate, int currentTurn) {
        deposit += deposit * interestRateCal(baseInterestRate, currentTurn) / 100.0;
        return deposit;
    }

    @Override
    public double interestRateCal(double baseInterestRate, int currentTurn) {
        return baseInterestRate * Math.log10(deposit) * Math.log(currentTurn);
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Player p) {
        owner = p;
    }

}
