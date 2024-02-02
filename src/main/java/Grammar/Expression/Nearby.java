package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Plan.Direction;

import java.util.HashMap;

public record Nearby(String command, Direction direction) implements Expression {
    @Override
    public double eval(HashMap<String, Integer> bindings, Player p, Territory t){
        return  p.nearby(t, direction);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("nearby ").append(direction);
    }
}
