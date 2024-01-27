package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.Expression;
import java.util.HashMap;

public record RegionCommand (String command, Expression expr) implements Plan {
    @Override
    public boolean eval(HashMap<String, Integer> bindings, Player p, Territory t) {
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append(command).append(" ");
        expr.prettyPrint(s);
        s.append("\n");
    }
}

