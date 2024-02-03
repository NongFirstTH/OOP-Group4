package GamePlay;

import Grammar.Plan.Direction;

interface RegionI {
    int getDeposit();
    int getInterest();
    int getMaxDeposit();
    void BeRelocated();
    void beInvested(int amount);
    void beCollected(int amount);
    void beShot(Direction direction, int amount);
    double depositCal();
    double interestRateCal();
    Player getOwner();

    void ownerless();
}
public class Region implements RegionI {
    private Player owner;
    private double maxDeposit;
    private double deposit;
    private double interest;
    private double baseInterest;

    @Override
    public int getDeposit() {
        return 0;
    }

    @Override
    public int getInterest() {
        return 0;
    }

    @Override
    public int getMaxDeposit() {
        return 0;
    }

    @Override
    public void BeRelocated() {

    }

    @Override
    public void beInvested(int amount) {

    }

    @Override
    public void beCollected(int amount) {

    }

    @Override
    public void beShot(Direction direction, int amount) {

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

    @Override
    public void ownerless() {

    }
}
