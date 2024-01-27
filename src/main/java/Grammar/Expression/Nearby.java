package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;
import java.util.Map;

public record Nearby(String command, String direction) implements Expression {
    @Override
    public int eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        return  p.nearby(direction);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("nearby ").append(direction);
    }
}
