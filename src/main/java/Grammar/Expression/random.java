package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;

public record random() implements Expression {

    @Override
    public int eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
}
