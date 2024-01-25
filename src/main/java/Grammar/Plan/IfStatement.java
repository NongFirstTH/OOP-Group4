package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

import java.util.HashMap;

public record IfStatement (Expression expr, Plan s1, Plan s2) implements Plan {
    @Override
    public boolean eval(HashMap<String, Integer> bindings, Player p, Territory t) {
        return true;
    }
}
