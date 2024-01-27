package Grammar.Plan;

import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

public class AttackCommand extends Plan {
    private String dir;
    private Expression expr;

    public AttackCommand(String dir, Expression expr){
        this.dir = dir;
        this.expr = expr;
    }
    public void eval(Player p, Territory t){

    }
}
