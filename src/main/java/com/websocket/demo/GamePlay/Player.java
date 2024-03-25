package com.websocket.demo.GamePlay;

import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Plan.Direction;
import com.websocket.demo.Grammar.Plan.Plan;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

interface PlayerI {

    /**
     * Returns the city center region of the player.
     *
     * @param t the territory
     * @return the city center region of the player
     */
    Region getCityCenter(Territory t);

    /**
     * Returns the player's current plan.
     *
     * @return the player's current plan
     */
    Plan getPlan();

    /**
     * Sets the player's plan and deducts the cost from the player's budget.
     *
     * @param p the plan to set
     * @param cost the cost of setting the plan
     */
    void setPlan(Plan p, long cost);

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    String getName();

    /**
     * Returns the row of the player's city center.
     *
     * @return the row of the player's city center
     */
    int getRow();

    /**
     * Returns the column of the player's city center.
     *
     * @return the column of the player's city center
     */
    int getCol();

    /**
     * Returns the current row of the player's city crew.
     *
     * @return the current row of the player's city crew
     */
    int getCurrow();

    /**
     * Returns the current column of the player's city crew.
     *
     * @return the current column of the player's city crew
     */
    int getCurcol();

    /**
     * Returns the budget of the player.
     *
     * @return the budget of the player
     */
    long getBudget();

    /**
     * Returns the deposit of the city crew's region.
     *
     * @param t the territory
     * @return the deposit of the city crew's region
     */
    double getDeposit(Territory t);

    /**
     * Returns the interest of the city crew's region in a territory.
     *
     * @param baseInterestRate the base interest rate
     * @param t the territory
     * @return the interest of the city crew's region
     */
    long getInterest(double baseInterestRate, Territory t);

    /**
     * Returns the mappings of variable names to variable values.
     *
     * @return the mappings of variable names to variable values
     */
    Map<String, Long> bindings();

    /**
     * Returns the location of the closest region belonging to an opponent in one of the six directions from the current region occupied by the city crew.
     * The returned value is relative to the city crew's current location and indicates the direction and distance to the opponent's region.
     * If there are multiple opponent's regions with the same minimal distance, the location of the one with the lowest direction number is returned.
     * If there are no visible opponent's regions from the current location of the city crew, returns 0.
     *
     * @param t the territory
     * @return the location of the closest opponent's region, or 0 if no opponent's region is visible
     * @throws EvalError if there is an error evaluating the opponent
     */
    long opponent(Territory t) throws EvalError;


    /**
     * Returns the location and deposit information of the nearest opponent's region in the specified direction.
     * The returned value is in the format 100x + y, where x is the distance from the city crew to the opponent's region and y is the number of digits in the current deposit of that region.
     * If no opponent owns a region in the given direction, returns 0.
     *
     * @param t the territory
     * @param direction the direction to check
     * @return the location and deposit information of the nearest opponent's region, or 0 if no opponent's region is found in the given direction
     * @throws EvalError if there is an error evaluating the nearby region
     */
    long nearby(Territory t, Direction direction) throws EvalError;


    /**
     * Relocates the player's city center to the current position of the crew.
     * The cost to relocate the city center is 5x + 10, where x is the minimum moving distance from the current city center to the destination region (regardless of the presence of any opponent's region in between).
     * This cost is deducted from the player's budget.
     * If the player does not have enough budget to execute this command, or if the current region does not belong to the player, the relocation fails.
     * Once executed (regardless of the outcome), the evaluation of the construction plan in that turn ends.
     *
     * @param t the territory
     */
    void relocate(Territory t);

    /**
     * Moves the city crew one unit in the specified direction.
     * If the destination region belongs to another opponent, the command acts like a no-op.
     * Whenever this command is executed (regardless of validity), the player's budget is decreased by one unit.
     * If the player does not have enough budget to execute this command, the evaluation of the construction plan in that turn ends.
     *
     * @param direction the direction to move the city crew
     * @param t the territory
     * @return true if the move was successful, false if the player does not have enough budget to execute the move
     * @throws EvalError if there is an error evaluating the move
     */
    boolean move(Direction direction, Territory t) throws EvalError;

    /**
     * Adds more deposits to the current region occupied by the city crew.
     * The total cost of an investment is amount + 1, where amount is the investment amount.
     * If the player does not have enough budget to execute this command, the command acts like a no-op, but the player still pays for the unit cost of the command.
     * Otherwise, the player's deposit in the current region is increased by the investment amount, but will not exceed the maximum deposit as specified in the configuration file.
     * A player may invest in a region belonging to no player as long as that region is adjacent to another region belonging to the player.
     *
     * @param amount the amount to invest
     * @param t the territory
     * @param maxDeposit the maximum deposit allowed in a region
     * @throws EvalError if there is an error evaluating the invest command
     */
    void invest(long amount, Territory t, long maxDeposit) throws EvalError;

    /**
     * Retrieves deposits from the current region occupied by the city crew.
     * Whenever this command is executed, the player's budget is decreased by one unit.
     * If the player does not have enough budget to execute this command, the evaluation of the construction plan in that turn ends.
     * If the specified collection amount is more than the deposit in the current region, the command acts like a no-op, but the player still pays for the unit cost of the command.
     * Otherwise, the player's deposit in the current region is decreased by the collection amount, which is then added to the player's available budget.
     * If the deposit becomes zero after the collection, the player loses the possession of that region.
     *
     * @param amount the amount to collect
     * @param g the game
     * @return true if the collection was successful, false if the player does not have enough budget to execute the collection
     */
    boolean collect(long amount, Game g);

    /**
     * Attempts to attack a region located one unit away from the city crew in the specified direction.
     * Each attack is given an expenditure, i.e., how much the player would like to spend in that attack. This will be deducted from the budget.
     * Each attack also has a fixed budget cost of one unit. That is, the total cost of an attack is x+1, where x is the given expenditure.
     * If the player does not have enough budget to execute this command, the command acts like a no-op.
     * Otherwise, the opponent's deposit in the target region will be decreased no more than the expenditure.
     * Specifically, if the expenditure is x and the opponent's deposit is d, then after the attack, the deposit will be max(0, d-x).
     * If the deposit becomes less than one, the opponent loses ownership of that region.
     * If the target region is a city center, and the attack reduces its deposit to zero, the attacked player loses the game.
     *
     * @param direction the direction of the attack
     * @param amount the amount to spend on the attack
     * @param g the game
     * @throws EvalError if there is an error evaluating the shoot command
     */
    void shoot(Direction direction, long amount, Game g) throws EvalError;

    /**
     * Handles the loss of a region by the player.
     * If the lost region is the player's city center, the player loses the game and ownership of all regions.
     * If the lost region is not the city center, it is removed from the player's list of owned regions.
     *
     * @param region the region that was lost
     * @param g the game
     */
    void lostRegion(Region region, Game g);

    /**
     * Effects: Adds the deposit of all the player's regions by the interest.
     *
     * @param baseInterestRate the base interest rate
     * @param maxDeposit the maximum deposit allowed
     */
    void interestCal(double baseInterestRate, long maxDeposit);

    /**
     * Effects: add turn 1.
     */
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
         t.getRegion(row,col).setCityCenter(this, init_center_dep);
         regions.add(t.getRegion(row, col));
    }

    @Override
    public Region getCityCenter(Territory t) {
        return t.getRegion(cityCenter[0], cityCenter[1]);
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
            if (amount>0) regions.add(t.getRegion(crew.getCurrow(), crew.getCurcol()));
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
