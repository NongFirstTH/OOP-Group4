package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Parse.PlanParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Plan;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

interface GameI {
    Player getPlayer();
    Territory getTerritory();
    long getMaxDeposit();
    void addPlayerToTestOnly(String name, int row, int col, Plan plan);

    void addPlayer(String name, Plan plan);

    void nextTurn();
    void revisePlan(Plan plan);

    boolean executePlan() throws EvalError;

    void playerLost(Player player);

    long getBaseInterest();
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

    private final Queue<Player> queueOfPlayers = new LinkedList<>();
    private Player playerTurn;
    private int time;
    private Territory t;

    public Game(String s, Territory t) throws SyntaxError, EvalError {
        Game g = new Game();
        Map<String, Long> bindings = g.getPlayer().bindings();
        new PlanParser(new PlanTokenizer(s)).parse().eval(null);
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
        playerTurn = null;
        row = 0;
        col = 0;
        init_plan_sec = 0;
        init_budget = 0;
        init_center_dep = 0;
        plan_rev_sec = 0;
        rev_cost = 0;
        max_dep = 0;
        interest_pct = 0;
    }

    @Override
    public Player getPlayer(){return playerTurn;}

    @Override
    public Territory getTerritory(){return t;}

    @Override
    public long getBaseInterest(){
        return interest_pct;
    }

    @Override
    public long getMaxDeposit(){return max_dep;}

    @Override
    public void addPlayerToTestOnly(String name, int row, int col, Plan plan) {
        Player p = new Player(name, init_budget, row, col, init_center_dep, t);
        p.setPlan(plan, 0);
        queueOfPlayers.add(p);
        if (playerTurn==null)
            nextTurn();
    }

    @Override
    public void addPlayer(String name, Plan plan) {
        Random random = new Random();
        addPlayerToTestOnly(name, random.nextInt((int) row)+1, random.nextInt((int) col)+1, plan);
    }

    @Override
    public void nextTurn() {
        playerTurn = queueOfPlayers.remove();
        queueOfPlayers.add(playerTurn);
        playerTurn.myTurn();
        playerTurn.interestCal(interest_pct, max_dep);
        playerTurn = queueOfPlayers.peek();
    }

    @Override
    public void revisePlan(Plan plan) {
        playerTurn.setPlan(plan, rev_cost);
    }

    @Override
    public boolean executePlan() throws EvalError {
        Plan plan = playerTurn.getPlan();
        plan.eval(this);
        return queueOfPlayers.size() > 1;
    }

    @Override
    public void playerLost(Player player) {
        queueOfPlayers.remove(player);
    }
}