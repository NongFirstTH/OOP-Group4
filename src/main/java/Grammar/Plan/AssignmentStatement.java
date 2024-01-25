package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.Expression;

import java.util.HashMap;
public record AssignmentStatement (String identifier, Expression expr) implements Plan {
    @Override
    public boolean eval(HashMap<String, Integer> bindings, Player p, Territory t) {
        return false;
    }
}
