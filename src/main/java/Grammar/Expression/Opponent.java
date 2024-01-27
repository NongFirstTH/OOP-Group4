package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;
import java.util.Map;

public record Opponent(String command) implements Expression {
    @Override
    public int eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        return p.opponent();
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("opponent ");
    }
}