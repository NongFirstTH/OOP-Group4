package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Parse.PlanParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import Grammar.Plan.Plan;

import java.util.HashMap;
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

//    private final int numberOfPlayer;
//    private final List<Player> listOfPlayers;
    private int turnCount;
    private Player playerturn;
    private int time;
    private Territory t;

    public Game(String s) throws SyntaxError, EvalError {
        Map<String, Integer> bindings = new HashMap<>();
        new PlanParser(new PlanTokenizer(s)).parse().eval(bindings, null, null);
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
}