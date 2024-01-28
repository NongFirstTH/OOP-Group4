package Grammar.Plan;

import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

public class IfStatement extends Plan {
    private Expression expr;
    private Statement s1;
    private Statement s2;

    public IfStatement(Expression expr, Statement s1, Statement s2){
        this.expr = expr;
        this.s1 = s1;
        this.s2 = s2;
    }
    public void eval(Player p, Territory t){

    }
}

