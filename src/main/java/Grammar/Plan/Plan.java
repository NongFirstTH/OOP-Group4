package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Expression.EvalError;

import java.util.Map;

public interface Plan{

    /**
     * Returns false if turn is ended otherwise true
     */
    boolean eval(Map<String, Long> bindings, Player p, Territory t) throws EvalError;

    void prettyPrint(StringBuilder s, int tab);
}