package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;

public interface Expression {
    int eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError;
    void prettyPrint(StringBuilder s);
}

