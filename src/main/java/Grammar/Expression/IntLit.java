package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record IntLit(long val) implements Expression {
    @Override
    public long eval(Map<String, Integer> bindings, Player p, Territory t) {
        return val;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(val);
    }
}
