package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Plan.Direction;

import java.util.Map;

public record Nearby(Direction direction) implements Expression {
    @Override
    public long eval(Player p, Territory t){
        return  p.nearby(t, direction);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("nearby ").append(direction);
    }
}
