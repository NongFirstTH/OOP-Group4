package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Parse.PlanParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Plan;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

interface GameI {
    Player getPlayer();
    Territory getTerritory();
    long getMaxDeposit();

    void addPlayer(String name);

    void addPlayerToTestOnly(String name, int row, int col, Plan plan);

    void nextTurn();

    void devisePlan(int p, Plan plan);

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
    public Territory getTerritory(){return t;}

    @Override
    public long getBaseInterest(){
        return interest_pct;
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
    }

    @Override
    public void addPlayerToTestOnly(String name, int row, int col, Plan plan) {
        Player p = new Player(name, init_budget, row, col, init_center_dep, t);
        p.setPlan(plan, 0);
        players.add(p);
    }

    @Override
    public void nextTurn() {
        turn = (turn+1)%players.size();
        Player p = getPlayer();
        p.myTurn();
        p.interestCal(interest_pct, max_dep);
    }

    @Override
    public void devisePlan(int p, Plan plan) {
        players.get(p).setPlan(plan, 0);
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

//        System.out.println("\t"+player.getName()+" lost");
    }
}