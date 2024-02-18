package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Plan.Direction;
import Grammar.Plan.Plan;

import java.util.*;

interface PlayerI {
    Region getCityCenter(Territory t);
    Plan getPlan();
    void setPlan(Plan p, long cost);
    String getName();
    int getRow();
    int getCol();
    int getCurrow();
    int getCurcol();
    long getBudget();
    double getDeposit(Territory t);
    long getInterest(double baseInterestRate, Territory t);
    Map<String, Long> bindings();
    long opponent(Territory t) throws EvalError;
    long nearby(Territory t, Direction direction) throws EvalError;
    //returns true when relocatable (to end turn)
    void relocate(Territory t);
    //returns true when movable (have enough budget)
    boolean move(Direction direction, Territory t) throws EvalError;
    void invest(long amount,Territory t, long maxDeposit) throws EvalError;
    //returns true when collectable (have enough budget)
    boolean collect(long amount, Game g);
    void shoot(Direction direction, long  amount, Game g) throws EvalError;
    //returns true if lost region is the city center
    void lostRegion(Region region, Game g);
    void interestCal(double baseInterestRate, long maxDeposit);
    void myTurn();
}

public class Player implements PlayerI {
    private final String name;
    private final int[] cityCenter = new int[2];
    private final CityCrew crew;
    private long budget;
    private Plan plan;
    private final LinkedHashSet<Region> regions = new LinkedHashSet<>();
    private final Map<String, Long> bindings = new HashMap<>();
    private int turn;

     public Player(String name, long budget, int row, int col, long init_center_dep, Territory t) {
         this.name = name;
         crew = new CityCrew(row, col);
         cityCenter[0] = row;
         cityCenter[1] = col;
         this.budget = budget;
         t.getRegions(row,col).setCityCenter(this, init_center_dep);
         regions.add(t.getRegions(row, col));
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
    public void setPlan(Plan plan, long cost) {
         if (budget >= cost) {
             budget -= cost;
             this.plan = plan;
         }
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
    public double getDeposit(Territory t) {
        return crew.getDeposit(t);
    }

    @Override
    public long getInterest(double baseInterestRate, Territory t) {
        return crew.getInterest(baseInterestRate, turn, t);
    }

    @Override
    public Map<String, Long> bindings() {
        return bindings;
    }

    @Override
    public long opponent(Territory t) throws EvalError {
        return crew.opponent(this,t);
    }

    @Override
    public long nearby(Territory t, Direction direction) throws EvalError {
        return crew.nearby(t, direction,this);
    }

    @Override
    public void relocate(Territory t) {
        long cost = 5 * minDistance() + 10;
        if ( budget >= cost && this == crew.getOwner(t) ) {
            budget-=cost;
            cityCenter[0] = crew.getCurrow();
            cityCenter[1] = crew.getCurcol();
        }
    }

    @Override
    public boolean move(Direction direction, Territory t) throws EvalError {
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
    public void invest(long amount,Territory t, long maxDeposit) throws EvalError {
        long cost = amount + 1;
        Player owner = crew.getOwner(t);
        if ( budget >= cost && (owner==null||owner==this)) {
            budget -= cost;
            crew.invest(amount,t,this, maxDeposit);
            if (amount>0) regions.add(t.getRegions(crew.getCurrow(), crew.getCurcol()));
        } else {
            budget = Math.max(budget - 1, 0);
        }
    }

    @Override
    public boolean collect(long amount, Game g) {
         long cost = 1;
         if (budget>=cost) {
             budget -= cost;
             if (crew.getOwner(g.getTerritory())==this)
                budget += crew.collect(amount, g);
             // If the deposit becomes zero after the collection, the player loses the possession of that region.
             return true;
         }
        return false;
    }

    @Override
    public void shoot(Direction direction, long amount, Game g) throws EvalError {
        long cost = amount + 1;
        if (budget >= cost) {
            budget -= cost;
            crew.shoot(direction, amount , g);
        } else {
            budget = Math.max(budget - 1, 0);
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
    public void lostRegion(Region region, Game g) {
         regions.remove(region);
         if (region == getCityCenter(g.getTerritory())) {
             g.playerLost(this);
             for (Region r:regions) {
                 r.lost();
             }
         }
    }

    @Override
    public void interestCal(double baseInterestRate, long maxDeposit) {
        for (Region region:regions) {
            region.depositCal(baseInterestRate, turn, maxDeposit);
        }
    }

    @Override
    public void myTurn() {
        turn++;
    }
}
