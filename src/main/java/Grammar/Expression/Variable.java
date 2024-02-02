package Grammar.Expression;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record Variable(String name) implements Expression {
    @Override
    public double eval(Map<String, Integer> bindings, Player p, Territory t) throws EvalError {
        if (bindings.containsKey(name))
            return bindings.get(name);
        throw new EvalError("undefined variable: " + name);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }
}

