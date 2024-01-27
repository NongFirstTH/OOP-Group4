package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;

import java.util.HashMap;

public record BinaryPlan(Plan p1, Plan p2) implements Plan {
    @Override
    public boolean eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        if (p1.eval(bindings, p ,t)) {
            return p2.eval(bindings, p, t);
        }
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        p1.prettyPrint(s ,tab);
        p2.prettyPrint(s ,tab);
    }
}