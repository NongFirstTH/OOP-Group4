package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Region;
import GamePlay.Territory;
import Grammar.Expression.EvalError;
import Grammar.Expression.Expression;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;
import org.junit.Test;

import javax.swing.text.html.parser.Parser;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RegionCommandTest {
    @Test
    public void invest_enoughBudget() throws SyntaxError, EvalError {
        PlanTokenizer tokenizer = new PlanTokenizer("50");
        ExpressionParser expressionParser = new ExpressionParser(tokenizer);
        Expression expr = expressionParser.parse();

        RegionCommand regionCommand = new RegionCommand("invest", expr);
        Territory t = new Territory(100,100);
//        Player p = new Player(100,1,1,10,t);
        Game g = new Game(100,100);
        Region[][] r = new Region[2][2];

//        p.invest(expr.eval(g),t,1000);
//        assertEquals(50,p.getBudget());
    }
}