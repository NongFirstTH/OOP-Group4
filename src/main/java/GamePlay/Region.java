package GamePlay;

import Grammar.Plan.Direction;

interface RegionI {
    double deposit();
    void interestCal(double baseInterestRate, int currentTurn, long maxDeposit);
    long getInterest(double baseInterestRate, int currentTurn);
    void BeRelocated();
    void beInvested(long amount, Player p, long maxDeposit);
    long beCollected(long amount);
    void beShot(long amount);
    double depositCal(double baseInterestRate, int currentTurn, long maxDeposit);
    double interestRateCal(double baseInterestRate, int currentTurn);
    Player getOwner();
    void setCityCenter(Player p, long init_center_dep);
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
    public void interestCal(double baseInterestRate, int currentTurn, long maxDeposit) {
    }

    @Override
    public long getInterest(double baseInterestRate, int currentTurn) {
        return 0;
    }

    @Override
    public void BeRelocated() {

    }

    @Override
    public void beInvested(long amount, Player p, long maxDeposit) {

    }

    @Override
    public long beCollected(long amount) {
        return 0;
    }

    @Override
    public void beShot(long amount) {

    }

    @Override
    public double depositCal(double baseInterestRate, int currentTurn, long maxDeposit) {
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
    public void setCityCenter(Player p, long init_center_dep) {
        owner = p;
        deposit = init_center_dep;
    }

}
