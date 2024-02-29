package Grammar.Plan;

import GamePlay.Game;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;

public record AssignmentStatement (String identifier, Expression expr) implements Plan {
    @Override
    public boolean eval(Game g) throws EvalError {
        g.getPlayer().bindings().put(identifier, expr.eval(g));
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append(identifier).append(" = ");
        expr.prettyPrint(s);
        s.append("\n");
    }
}
