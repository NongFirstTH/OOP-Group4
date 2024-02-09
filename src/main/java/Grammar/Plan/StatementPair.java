package Grammar.Plan;

import GamePlay.Game;
import Grammar.Expression.EvalError;

public record StatementPair(Plan p1, Plan p2) implements Plan {
    @Override
    public boolean eval(Game g) throws EvalError {
        if (p1.eval(g)) {
            return p2.eval(g);
        }
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        p1.prettyPrint(s ,tab);
        p2.prettyPrint(s ,tab);
    }
}