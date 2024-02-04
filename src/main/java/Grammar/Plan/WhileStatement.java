package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

import java.util.Map;

public record WhileStatement (Expression expr, Plan s1) implements Plan {
    @Override
    public boolean eval(Game g) throws EvalError {
        for (int counter = 0; counter < 10000 && expr.eval(g)>0; counter++) {
            if(!s1.eval(g)) {
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