package GamePlay;

import Grammar.Plan.Direction;
import Grammar.Plan.Plan;

import java.util.HashMap;
import java.util.Map;

interface PlayerI {
    Region getCityCenter(Territory t);
    Plan getPlan();
    void setPlan(Plan p);
    String getName();
    int getRow();
    int getCol();
    int getCurrow();
    int getCurcol();
    long getBudget();
    double getDeposit();
    long getInterest();
    long getMaxDeposit();
    Map<String, Long> bindings();
    long opponent(Territory t);
    long nearby(Territory t, Direction direction);
    //returns true when relocatable (to end turn)
    void relocate(Territory t);
    //returns true when movable (have enough budget)
    boolean move(Direction direction, Territory t);
  
    void invest(long amount);
    //returns true when collectable (have enough budget)
    boolean collect(long amount);
    void shoot(Direction direction, long  amount);
    //returns true if lost region is the city center
    void lostRegion(Region region, Territory t);
}

public class Player implements PlayerI {
    private String name;
    private final int[] cityCenter = new int[2];
    private final CityCrew crew;
    private long budget;
    private Plan plan;
    private final Map<String, Long> bindings = new HashMap<>();

     public Player(long budget, int row, int col, Territory t) {
        crew = new CityCrew(row, col);
        cityCenter[0] = row;
        cityCenter[1] = col;
        this.budget = budget;
    }

    @Override
    public Region getCityCenter(Territory t) {
        return t.getRegions(cityCenter[0], cityCenter[1]);
    }

    @Override
    public Plan getPlan() {
        return plan;
    }

    @Override
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRow() {
        return cityCenter[0];
    }

    @Override
    public int getCol() {
        return cityCenter[1];
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
    public long getBudget() {
        return budget;
    }

    @Override
    public double getDeposit() {
        return crew.getDeposit();
    }

    @Override
    public long getInterest() {
        return crew.getInterest();
    }

    @Override
    public long getMaxDeposit() {
        return crew.getMaxDeposit();
    }

    @Override
    public Map<String, Long> bindings() {
        return bindings;
    }

    @Override
    public long opponent(Territory t) {
        return crew.opponent(t);
    }

    @Override
    public long nearby(Territory t, Direction direction) {
        return crew.nearby(t, direction);
    }

    @Override
    public void relocate(Territory t) {
        long cost = 5 * minDistance() + 10;
        if ( budget >= cost && this == crew.owner(t) ) {
            budget-=cost;
            cityCenter[0] = crew.getCurrow();
            cityCenter[1] = crew.getCurcol();
        }
    }

    @Override
    public boolean move(Direction direction, Territory t) {
        long cost = 1;
        Player owner = crew.ownerMoveTo(direction, t);
        if ( budget >= cost ) {
            budget -= cost;
            if (owner!=null && owner!=this)
                return true;
            crew.move(direction, t);
            return true;
        }
        return false;
    }

    @Override
    public void invest(long amount) {
        long cost = amount + 1;
        if ( budget >= cost ) {
            budget -= cost;
            crew.invest(amount);
        } else {
            budget = Math.max(budget - 1, 0);
        }
    }

    @Override
    public boolean collect(long amount) {
         long cost = 1;
         if (budget>=cost) {
             budget -= cost;
             budget += crew.collect(amount);
             // If the deposit becomes zero after the collection, the player loses the possession of that region.
             return true;
         }
        return false;
    }

    @Override
    public void shoot(Direction direction, long amount) {
        long cost = amount + 1;
        if (budget >= cost) {
            budget -= cost;
            crew.shoot(direction, amount);
        }
        //If the deposit becomes less than one, the opponent loses ownership of that region.
        //if the target region is a city center, and the attack reduces its deposit to zero, the attacked player loses the game.
    }

    private long minDistance() {
        return minDistance(crew.getCurrow(), crew.getCurcol(), cityCenter[0], cityCenter[1]);
    }

    public long minDistance(int i0, int j0, int i1, int j1) {
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
    public void lostRegion(Region region, Territory t) {
        if (region == getCityCenter(t)) {
        }
    }
}
