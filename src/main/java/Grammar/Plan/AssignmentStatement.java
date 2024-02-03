package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Parse.ExpressionParser;

import java.util.Map;
public record AssignmentStatement (String identifier, Expression expr) implements Plan {
    @Override
    public boolean eval(Player p, Territory t) throws EvalError {
        p.bindings().put(identifier, expr.eval(p, t));
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append(identifier).append(" = ");
        expr.prettyPrint(s);
        s.append("\n");
    }
}
