package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;
import java.util.Map;

public record IntLit(int val) implements Expression {
    @Override
    public int eval(HashMap<String, Integer> bindings, Player p, Territory t) {
        return val;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(val);
    }
}
