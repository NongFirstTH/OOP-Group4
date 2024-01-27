package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

import java.util.HashMap;

public record WhileStatement (Expression expr, Plan p) implements Plan {
    @Override
    public boolean eval(HashMap<String, Integer> bindings, Player p, Territory t) {
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("while (");
        expr.prettyPrint(s);
        s.append(") {\n");
        p.prettyPrint(s, tab+1);
        s.append("\t".repeat(Math.max(0, tab))).append("}\n");
    }
}