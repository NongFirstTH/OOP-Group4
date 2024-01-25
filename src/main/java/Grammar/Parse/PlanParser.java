package Grammar.Parse;

import Grammar.Expression.ExprTokenizer;
import Grammar.Plan.Plan;

public class PlanParser {
    private ExprTokenizer tkz;

    public Plan parse();

    public Plan parsePlan();

    public Plan parseStatement();

    public Plan parseCommand();

    public Plan parseBlockStatement();

    public Plan parseIfStatement();

    public Plan parseWhileStatement();

    public Plan parseAssignmentStatement();

    public Plan parseExpression();

    public Plan parseActionCommand();

    public Plan parseMoveCommand();

    public Plan parseRegionCommand();

    public Plan parseAttackCommand();

    public Plan parseTerm();

    public Plan parseFactor();

    public Plan parsePower();

    public Plan parseDirection();

    public Plan parseInfoExpression();

}
