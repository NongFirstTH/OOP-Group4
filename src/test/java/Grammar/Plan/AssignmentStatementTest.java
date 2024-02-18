package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentStatementTest {
    @Test
    public void Assignment_notSame() throws SyntaxError, EvalError {
        PlanTokenizer tokenizer1 = new PlanTokenizer("123");
        ExpressionParser expressionParser1 = new ExpressionParser(tokenizer1);
        Expression expr1 = expressionParser1.parse();
        AssignmentStatement assignmentStatement1 = new AssignmentStatement("a",expr1);
        PlanTokenizer tokenizer2 = new PlanTokenizer("234");
        ExpressionParser expressionParser2 = new ExpressionParser(tokenizer2);
        Expression expr2 = expressionParser2.parse();
        AssignmentStatement assignmentStatement2 = new AssignmentStatement("b",expr2);
    }
}
