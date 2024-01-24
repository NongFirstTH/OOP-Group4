package Grammar.Plan;

import Grammar.Expression.Expression;
import Grammar.Plan.Plan;

public class AssignmentStatement extends Plan {
    private String identifier;
    private String op;
    private Expression expr;

    public AssignmentStatement(String identifier, String op, Expression expr);
    public void eval(Player p, Territory t)
}

