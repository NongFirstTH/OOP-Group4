package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public interface Expression {
    double eval(Map<String, Integer> bindings, Player p, Territory t) throws EvalError;
    void prettyPrint(StringBuilder s);
}

