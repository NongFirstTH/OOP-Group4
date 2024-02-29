package GamePlay;

interface RegionI {
    double getDeposit();
    long getInterest(double baseInterestRate, int currentTurn) ;
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
    private long interest;
    private Territory t;
    private double currentRate;

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
    public long getInterest(double baseInterestRate, int currentTurn) {
        double calculatedInterestRate = interestRateCal(baseInterestRate, currentTurn);
        double calculatedInterest = deposit * (calculatedInterestRate / 100.0);
        interest = (long)calculatedInterest;
        return interest;
    }

    private double interestRateCal(double baseInterestRate, int currentTurn) {
        double calculatedInterestRate = baseInterestRate * Math.log10(deposit) * Math.log(currentTurn);
        currentRate = calculatedInterestRate;
        return calculatedInterestRate;
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
            if(deposit == 0){
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
        if(deposit <= 0){
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
        return new RegionWrap((long) deposit);
    }
}
