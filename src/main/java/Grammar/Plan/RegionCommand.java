package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Parse.ExpressionParser;

import java.util.Map;

public record RegionCommand (String command, Expression expr) implements Plan {
    @Override
    public boolean eval(Map<String, Long> bindings, Player p, Territory t) {
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append(command).append(" ");
        expr.prettyPrint(s);
        s.append("\n");
    }
    public void executeCommand(Map<String, Long> bindings, Player player, Territory territory) throws EvalError {
        switch(command){
            case "invest" ->{
                player.invest((int)expr.eval(bindings, player, territory));
            }
            case "collect" -> {
                player.collect(expr.eval(bindings, player, territory));
            }
        }
    }
}

