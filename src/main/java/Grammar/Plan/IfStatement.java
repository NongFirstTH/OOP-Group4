package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

import java.util.Map;

public record IfStatement (Expression expr, Plan s1, Plan s2) implements Plan {
    @Override
    public boolean eval(Map<String, Long> bindings, Player p, Territory t) throws EvalError {
        if(expr.eval(bindings, p, t)>0) {
            return s1.eval(bindings, p, t);
        }
        return s2.eval(bindings, p, t);
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("if (");
        expr.prettyPrint(s);
        s.append(") then {\n");
        s1.prettyPrint(s, tab+1);
        s.append("\t".repeat(Math.max(0, tab))).append("} else {\n");
        s2.prettyPrint(s, tab+1);
        s.append("\t".repeat(Math.max(0, tab))).append("}\n");
    }
}
