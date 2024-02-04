package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Parse.ExpressionParser;

import java.util.Map;

public record RegionCommand (String command, Expression expr) implements Plan {
    @Override
    public boolean eval(Game g) throws EvalError {
        if(command.equals("invest")){
            g.getPlayer().invest(expr.eval(g));
            return true;
        }else if(command.equals("collect")){
            return g.getPlayer().collect(expr.eval(g));
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

