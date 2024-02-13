package GamePlay;

interface RegionI {
    double getDeposit();
    void interestCal(double baseInterestRate, int currentTurn, long maxDeposit);
    long getInterest(double baseInterestRate, int currentTurn);
    void BeRelocated(Player p, Region destinationRegion);
    void beInvested(long amount, Player p, long maxDeposit);
    long beCollected(long amount, Game g);
    void beShot(long amount, Game g);
    double depositCal(double baseInterestRate, int currentTurn, long maxDeposit);
    double interestRateCal(double baseInterestRate, int currentTurn);
    Player getOwner();
    void setCityCenter(Player p, long init_center_dep);

    void lost();
}
public class Region implements RegionI {
    private Player owner;
    private double deposit;
    private long interest;
    private Territory t;

    @Override
    public double getDeposit() {
        return deposit;
    }

    @Override
    public void interestCal(double baseInterestRate, int currentTurn, long maxDeposit) {
    }
  
    public void getInterest(double baseInterest, int currentTurn, long maxDeposit) {
        double calculatedDeposit = depositCal(baseInterest,currentTurn,maxDeposit);
        if (calculatedDeposit >= maxDeposit) {
            interest = maxDeposit;
        } else {
            interest = (long) calculatedDeposit;
        }
    }

    @Override
    public void BeRelocated(Player p, Region destinationRegion) {

    }

    @Override
    public void beInvested(long amount, Player p, long maxDeposit) {
        double currentDeposit  = deposit + amount;
        if (deposit >= maxDeposit) {
            deposit = maxDeposit;
        } else {
            deposit = currentDeposit;
        }
    }

    @Override
    public long beCollected(long amount, Game g) {
        deposit -= amount;
        return (long)deposit;
    }

    @Override
    public void beShot(long amount, Game g) {

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

    @Override
    public void lost() {
        owner = null;
    }
}
