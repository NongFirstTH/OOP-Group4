package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record Opponent(String command) implements Expression {
    @Override
    public double eval(Map<String, Integer> bindings, Player p, Territory t){
        return p.opponent(t);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("opponent");
    }
}