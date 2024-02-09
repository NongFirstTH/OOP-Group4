package Grammar.Plan;

import GamePlay.Game;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;

public record RegionCommand (String command, Expression expr) implements Plan {
    @Override
    public boolean eval(Game g) throws EvalError {
        if(command.equals("invest")){
            g.getPlayer().invest(expr.eval(g),g.getTerritory());
            return true;
        }else if(command.equals("collect")){
            return g.getPlayer().collect(expr.eval(g),g.getTerritory());
        }
        throw new EvalError("Undefined Region Command: " + command);
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append(command).append(" ");
        expr.prettyPrint(s);
        s.append("\n");
    }
}

