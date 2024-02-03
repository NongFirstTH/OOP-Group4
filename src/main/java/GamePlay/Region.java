package GamePlay;

import Grammar.Plan.Direction;

interface RegionI {
    double deposit();
    long getInterest();
    void BeRelocated();
    void beInvested(long amount);
    void beCollected(long amount);
    void beShot(Direction direction, long amount);
    double depositCal();
    double interestRateCal();
    Player getOwner();
}
public class Region implements RegionI {
    private Player owner;
    private double deposit;
    private double interest;

    @Override
    public double deposit() {
        return 0;
    }

    @Override
    public long getInterest() {
        return 0;
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
    public double depositCal() {
        return 0;
    }

    @Override
    public double interestRateCal() {
        return 0;
    }

    @Override
    public Player getOwner() {
        return null;
    }

}
