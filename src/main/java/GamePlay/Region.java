package GamePlay;

import Grammar.Plan.Direction;

interface RegionI {
    double deposit();
    long getInterest();
    void BeRelocated();
    void beInvested(long amount);
    void beCollected(long amount);
    void beShot(Direction direction, long amount);
    double depositCal(double baseInterestRate, int currentTurn);
    double interestRateCal(double baseInterestRate, int currentTurn);
    Player getOwner();
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
    public void beInvested(long amount) {

    }

    @Override
    public void beCollected(long amount) {

    }

    @Override
    public void beShot(Direction direction, long amount) {

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

}
