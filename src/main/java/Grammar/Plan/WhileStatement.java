package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

import java.util.Map;

public record WhileStatement (Expression expr, Plan s1) implements Plan {
    @Override
    public boolean eval(Map<String, Integer> bindings, Player p, Territory t) throws EvalError {
        while( expr.eval(bindings, p, t)>0 ) {
            if(!s1.eval(bindings, p, t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("while (");
        expr.prettyPrint(s);
        s.append(") {\n");
        s1.prettyPrint(s, tab+1);
        s.append("\t".repeat(Math.max(0, tab))).append("}\n");
    }
}