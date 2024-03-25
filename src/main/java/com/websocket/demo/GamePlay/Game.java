package com.websocket.demo.GamePlay;

import com.websocket.demo.GamePlay.Wrapper.InitGame;
import com.websocket.demo.GamePlay.Wrapper.PlayerWrap;
import com.websocket.demo.GamePlay.Wrapper.TerritoryWrap;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Parse.PlanParser;
import com.websocket.demo.Grammar.Parse.PlanTokenizer;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import com.websocket.demo.Grammar.Plan.Plan;

import java.util.*;

interface GameI {

    /**
     * Returns the player in the current turn if there are more than 0 players, otherwise null.
     *
     * @return the player in the current turn, or null if there are no players
     */
    Player getPlayer();

    /**
     * Returns a set of players' names.
     *
     * @return a set of players' names
     */
    Set<String> getPlayers();

    /**
     * Returns the territory.
     *
     * @return the territory
     */
    Territory getTerritory();

    /**
     * Returns the maximum deposit.
     *
     * @return the maximum deposit
     */
    long getMaxDeposit();

    /**
     * Effects: Adds a player with the given name, creates a city center, sets budget, and city center deposit.
     * The city center location is randomly generated within the game's territory, ensuring it is unique and not
     * already occupied by another player's city center.
     *
     * @param name the name of the player being added
     */
    void addPlayer(String name);

    /**
     * Effects: Adds a player with the given name, row, col, and plan (for testing purposes).
     *
     * @param name the name of the player being added
     * @param row  the row of the player's city center
     * @param col  the column of the player's city center
     * @param plan the player's plan
     */
    void addPlayerToTestOnly(String name, int row, int col, Plan plan);

    /**
     * Effects: Moves to the next turn.
     */
    void nextTurn();

    /**
     * Effects: Sets a plan for the player with the given name.
     *
     * @param name the name of the player
     * @param plan the player's plan
     */
    void devisePlan(String name, Plan plan);

    /**
     * Sets a revised plan for the player's current turn and deducts the revise cost from the player's budget.
     *
     * @param plan the revised plan to be set
     */
    void revisePlan(Plan plan);

    /**
     * Executes the current player's plan.
     *
     * @return true if there is more than one player remaining after execution, false otherwise
     * @throws EvalError if there is an error evaluating the plan
     */
    boolean executePlan() throws EvalError;

    /**
     * Handles a player losing the game.
     *
     * @param player the player who lost
     */
    void playerLost(Player player);

    /**
     * Returns the base interest rate.
     *
     * @return the base interest rate
     */
    long getBaseInterest();

    /**
     * Returns the game map along with the status of each player.
     * The game map includes all regions in the territory, indicating the owner's name (if there is one) and the deposit.
     * The status of each player includes their name, budget, current row and column position,
     * as well as their city center's row and column position.
     *
     * @return the game map along with the status of each player
     */
    TerritoryWrap getMap();
}


public class Game implements GameI {
    private final long row;
    private final long col;
    private final long init_plan_sec;
    private final long init_budget;
    private final long init_center_dep;
    private final long plan_rev_sec;
    private final long rev_cost;
    private final long max_dep;
    private final long interest_pct;

    private final Map<String, Player> playerNames = new HashMap<>();
    private final List<Player> players = new LinkedList<>();
    private int turn;
    private int time;
    private Territory t;

    public Game(String s) throws SyntaxError, EvalError {
        Game g = new Game();
        Map<String, Long> bindings = g.getPlayer().bindings();
        new PlanParser(new PlanTokenizer(s)).parse().eval(g);
        row = bindings.get("m");
        col = bindings.get("n");
        init_plan_sec = 60L*bindings.get("init_plan_min")+bindings.get("init_plan_sec");
        init_budget = bindings.get("init_budget");
        init_center_dep = bindings.get("init_center_dep");
        plan_rev_sec = 60L*bindings.get("plan_rev_min")+bindings.get("plan_rev_sec");
        rev_cost = bindings.get("rev_cost");
        max_dep = bindings.get("max_dep");
        interest_pct = bindings.get("interest_pct");

        t = new Territory((int) row, (int) col);
    }

    public Game(InitGame init) throws SyntaxError, EvalError {
        row = init.getM();
        col = init.getN();
        init_plan_sec = init.getInit_plan_sec();
        init_budget = init.getInit_budget();
        init_center_dep = init.getInit_center_dep();
        plan_rev_sec = init.getPlan_rev_sec();
        rev_cost = init.getRev_cost();
        max_dep = init.getMax_dep();
        interest_pct = init.getInterest_pct();
        t = new Territory((int) row, (int) col);
    }

    public Game(int row, int col) throws SyntaxError, EvalError {
        this.row=row;
        this.col=col;
        init_plan_sec=300;
        init_budget=10000;
        init_center_dep=100;
        plan_rev_sec=1800;
        rev_cost=100;
        max_dep=1000000;
        interest_pct=5;
        t = new Territory(row, col);
    }

    private Game() {
        turn = 0;
        row = 1;
        col = 1;
        init_plan_sec = 0;
        init_budget = 0;
        init_center_dep = 0;
        plan_rev_sec = 0;
        rev_cost = 0;
        max_dep = 0;
        interest_pct = 0;
        players.add(new Player("",0,1,1,0,new Territory(1,1)));
    }

    @Override
    public Player getPlayer(){return players.get(turn);}

    @Override
    public Set<String> getPlayers() {
        return playerNames.keySet();
    }

    @Override
    public Territory getTerritory(){return t;}

    @Override
    public long getBaseInterest(){
        return interest_pct;
    }

    @Override
    public TerritoryWrap getMap() {
        PlayerWrap[] array = new PlayerWrap[players.size()];
        int i = 0;
        for (Player p:players) {
            array[i++] = new PlayerWrap(p.getName(), p.getBudget(), p.getRow(), p.getCol(), p.getCurrow(), p.getCurcol());
        }
        return new TerritoryWrap(t.wrap(), array);
    }

    @Override
    public long getMaxDeposit(){return max_dep;}

    private int[] getUniqueCityCenter() {
        Random random = new Random();
        boolean isUniq = false;
        int row = 0, col = 0;
        while (!isUniq) {
            row = random.nextInt((int) this.row) + 1;
            col = random.nextInt((int) this.col) + 1;
            isUniq = true;
            for (Player p: players) {
                if (row == p.getRow() && col == p.getCol()) {
                    isUniq = false;
                    break;
                }
            }
        }
        return new int[]{row, col};
    }

    @Override
    public void addPlayer(String name) {
        int[] cityCenter = getUniqueCityCenter();
        Player p = new Player(name, init_budget, cityCenter[0], cityCenter[1], init_center_dep, t);
        players.add(p);
        playerNames.put(name,p);
    }

    @Override
    public void addPlayerToTestOnly(String name, int row, int col, Plan plan) {
        Player p = new Player(name, init_budget, row, col, init_center_dep, t);
        p.setPlan(plan, 0);
        players.add(p);
        playerNames.put(name,p);
    }

    @Override
    public void nextTurn() {
        turn = (turn+1)%players.size();
        Player p = getPlayer();
        p.myTurn();
        p.interestCal(interest_pct, max_dep);
    }

    @Override
    public void devisePlan(String name, Plan plan) {
        playerNames.get(name).setPlan(plan, 0);
    }

    @Override
    public void revisePlan(Plan plan) {
        getPlayer().setPlan(plan, rev_cost);
    }

    @Override
    public boolean executePlan() throws EvalError {
        Plan plan = getPlayer().getPlan();
        plan.eval(this);
        return players.size()>1;
    }

    @Override
    public void playerLost(Player player) {
        int lost = players.indexOf(player);
        players.remove(player);
        turn = lost<turn ? turn-1 : turn;
    }
}