package GamePlay;
interface RegionI {
    public int getDeposit();
    public int getMaxDeposit();
    public double depositCal();
    public double interestRateCal();
    public int getInterest();
    public int getCol();
    public int getRow();
    public Player getOwner();
    public void invest(double amount);
    public void collect(double amount);
    public void beShot(String direction, double amount);

    void ownerless();
}
public class Region implements RegionI {
    private int rows;
    private int cols;
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
    public int getMaxDeposit() {
        return 0;
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
    public int getInterest() {
        return 0;
    }

    @Override
    public int getCol(){
        return 0;
    }
    @Override
    public int getRow(){
        return 0;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public void invest(double amount) {

    }

    @Override
    public void collect(double amount) {

    }

    @Override
    public void beShot(String direction, double amount) {

    }

    @Override
    public void ownerless() {

    }
}
