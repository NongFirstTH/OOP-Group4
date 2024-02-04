
package Grammar.Plan;
import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;

import java.util.Map;

public record AttackCommand (Direction dir, Expression expr) implements Plan {
    @Override
    public boolean eval(Game g) throws EvalError {
        g.getPlayer().shoot(dir, expr.eval(g));
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("shoot ").append(dir).append(" ");
        expr.prettyPrint(s);
        s.append("\n");
    }
}
