package GamePlay;

import Grammar.Plan.Plan;

import java.util.List;

interface PlayerI {
    List<Region> getRegions();
    Region getCityCenter();
    Plan getPlan();
    String getName();
    int getCol();
    int getRow();
    int getCurcol();
    int getCurrow();
    double getBudget();
    double getDeposit();
    double getInterest();

    double getMaxDeposit();

    int opponent(Territory t);
    int nearby(Territory t, String direction);
    //returns true when relocatable (to end turn)
    boolean relocate();
    //returns true when movable (have enough budget)
    boolean move(String direction);
    void invest(int amount);
    //returns true when collectable (have enough budget)
    boolean collect(int amount);
    void shoot(String direction, int  amount);
    //returns true if lost region is the city center
    void lostRegion(Region region);
}

public class Player implements PlayerI {
    private String name;
    private List<Region> regions;
    private Region cityCenter;
    private CityCrew crew;
    private double budget;
    private Plan plan;

    @Override
    public List<Region> getRegions() {
        return regions;
    }

    @Override
    public Region getCityCenter() {
        return cityCenter;
    }

    @Override
    public Plan getPlan() {
        return plan;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCol() {
        return cityCenter.getCol();
    }

    @Override
    public int getRow() {
        return cityCenter.getRow();
    }

    @Override
    public int getCurcol() {
        return crew.getCurcol();
    }

    @Override
    public int getCurrow() {
        return crew.getCurrow();
    }

    @Override
    public double getBudget() {
        return budget;
    }

    @Override
    public double getDeposit() {
        return crew.getDeposit();
    }

    @Override
    public double getInterest() {
        return crew.getInterest();
    }

    @Override
    public double getMaxDeposit() {
        return crew.getMaxDeposit();
    }

    @Override
    public int opponent(Territory t) {
        return crew.opponent(t);
    }

    @Override
    public int nearby(Territory t, String direction) {
        return crew.nearby(t, direction);
    }

    @Override
    public boolean relocate() {
        int cost = 5 * minDistance() + 10;
        if ( budget >= cost ) {
            budget-=cost;
            crew.relocate();
            return true;
        }
        return false;
    }

    @Override
    public boolean move(String direction) {
        if ( budget >= 1 ) {
            budget -= 1;
            crew.move(direction);
            return true;
        }
        return false;
    }

    @Override
    public void invest(int amount) {
        int cost = amount + 1;
        if ( budget >= cost ) {
            budget -= cost;
            crew.invest(amount);
        } else {
            budget = (budget >= 1) ? (budget - 1) : 0;
        }
    }

    @Override
    public boolean collect(int amount) {
        if (budget<1) return false;
        budget -= 1;
        budget += crew.collect(amount);
        // If the deposit becomes zero after the collection, the player loses the possession of that region.
        return true;
    }

    @Override
    public void shoot(String direction, int amount) {
        int cost = amount + 1;
        if (budget >= cost) {
            crew.shoot(direction, amount);
        }
        //If the deposit becomes less than one, the opponent loses ownership of that region.
        //if the target region is a city center, and the attack reduces its deposit to zero, the attacked player loses the game.
    }

    private int minDistance() {
        return minDistance(crew.getCurrow(), crew.getCurcol(), cityCenter.getRow(), cityCenter.getCol());
    }

    public int minDistance(int i0, int j0, int i1, int j1) {
        int iDif = Math.abs(i0-i1), jDif = Math.abs(j0-j1);;
        if (jDif==0) return Math.abs(i0-i1);
        int max_iDif = jDif/2;
        if (jDif%2!=0) {
            if (j0%2==0 && i1<i0)
                max_iDif+=1;
            else if (j0%2==1 && i1>i0)
                max_iDif+=1;
        }
        if (max_iDif>=iDif) return jDif;
        return jDif+iDif-max_iDif;
    }

    @Override
    public void lostRegion(Region region) {
        regions.remove(region);
        if (region == cityCenter) {
            for (Region r:regions) {
                r.ownerless();
            }
        }
    }
}
