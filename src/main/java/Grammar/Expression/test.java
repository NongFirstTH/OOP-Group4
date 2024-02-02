package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Parse.ExpressionParser;
import Grammar.Parse.PlanTokenizer;
import Grammar.Parse.SyntaxError;

import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) throws EvalError, SyntaxError {
//        PlanTokenizer p = new PlanTokenizer("3+2^4");
//        ExpressionParser e = new ExpressionParser(p);
//        StringBuilder s = new StringBuilder();
//        HashMap<String,Integer> m = new HashMap<>();
//        Player p1 = new Player();
//        Territory t = new Territory();
//        int r = e.parse().eval(m,p1,t);
//        System.out.println(r);
        SpecialVariables s = new SpecialVariables("random");
        HashMap<String,Integer> m = new HashMap<>();
        Player p = new Player();
        Territory t = new Territory();
        System.out.println(s.eval(m,p,t));
    }
}
