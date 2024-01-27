package GamePlay;
interface RegionI {
    int getCol();
    int getRow();
    int getDeposit();
    int getInterest();
    int getMaxDeposit();
    void BeRelocated();
    void beInvested(int amount);
    void beCollected(int amount);
    void beShot(String direction, int amount);
    double depositCal();
    double interestRateCal();
    Player getOwner();

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
    public int getCol() {
        return 0;
    }

    @Override
    public int getRow() {
        return 0;
    }

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
    public void beShot(String direction, int amount) {

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
