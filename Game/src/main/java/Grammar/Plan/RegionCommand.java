package Grammar.Plan;

import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

public class RegionCommand extends Plan {
    private String command;
    private Expression expr;

    public RegionCommand(String command, Expression expr){
        this.command = command;
        this.expr = expr;
    }
    public void eval(Player p, Territory t){

    }
}

