package Grammar.Expression;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Plan.Direction;

import java.util.Map;

public record Nearby(Direction direction) implements Expression {
    @Override
    public long eval(Game g){
        return  g.getPlayer().nearby(g.getTerritory(), direction);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("nearby ").append(direction);
    }
}
