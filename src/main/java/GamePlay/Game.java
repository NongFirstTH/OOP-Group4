package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Parse.PlanParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;

import java.util.List;
import java.util.Map;

public class Game {
    private final long row;
    private final long col;
    private final long init_plan_sec;
    private final long init_budget;
    private final long init_center_dep;
    private final long plan_rev_sec;
    private final long rev_cost;
    private final long max_dep;
    private final long interest_pct;

    private final List<Player> listOfPlayers;
    private int turnCount;
    private Player playerturn;
    private int time;
    private Territory t;

    public Game(String s, List<Player> listOfPlayers, Territory t) throws SyntaxError, EvalError {
        this.listOfPlayers = listOfPlayers;
        playerturn = listOfPlayers.getFirst();
        this.t = t;

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

    public Game(List<Player> listOfPlayers, Territory t) throws SyntaxError, EvalError {
        this.listOfPlayers = listOfPlayers;
        playerturn = listOfPlayers.getFirst();
        this.t = t;

        Game g = new Game();
        row = 20;
        col = 20;
        init_plan_sec = 0;
        init_budget = 0;
        init_center_dep = 0;
        plan_rev_sec = 0;
        rev_cost = 0;
        max_dep = 0;
        interest_pct = 0;
        t = new Territory((int) row, (int) col);
    }

    private Game() {
        playerturn = new Player(0, 0, 0, null);
        this.listOfPlayers = null;
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
    public Player getPlayer(){return playerturn;}
    public Territory getTerritory(){return t;}
    public long getMaxDeposit(){return max_dep;}
}