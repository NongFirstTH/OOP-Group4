package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record Nearby(String command, String direction) implements Expression {
    @Override
    public double eval(Map<String, Integer> bindings, Player p, Territory t){
        return  p.nearby(t, direction);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("nearby ").append(direction);
    }
}
