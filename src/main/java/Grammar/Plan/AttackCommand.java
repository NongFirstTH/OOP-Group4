
package Grammar.Plan;
import GamePlay.Game;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;

public record AttackCommand (Direction dir, Expression expr) implements Plan {
    @Override
    public boolean eval(Game g) throws EvalError {
        g.getPlayer().shoot(dir, expr.eval(g),g);
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("shoot ").append(dir).append(" ");
        expr.prettyPrint(s);
        s.append("\n");
    }
}
