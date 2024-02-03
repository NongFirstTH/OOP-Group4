package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public interface Expression {
    long eval(Player p, Territory t) throws EvalError;
    void prettyPrint(StringBuilder s);
}

