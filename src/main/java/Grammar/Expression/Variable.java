package Grammar.Expression;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;

public record Variable(String name) implements Expression {
    public int eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        if (bindings.containsKey(name))
            return bindings.get(name);
        throw new EvalError("undefined variable: " + name);
    }
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }
}

