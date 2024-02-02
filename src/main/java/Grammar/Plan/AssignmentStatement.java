package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.Expression;

import java.util.Map;
public record AssignmentStatement (String identifier, Expression expr) implements Plan {
    @Override
    public boolean eval(Map<String, Integer> bindings, Player p, Territory t) {
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append(identifier).append(" = ");
        expr.prettyPrint(s);
        s.append("\n");
    }
}
