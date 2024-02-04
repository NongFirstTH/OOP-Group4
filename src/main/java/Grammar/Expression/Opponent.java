package Grammar.Expression;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record Opponent() implements Expression {
    @Override
    public long eval(Game g){
        return g.getPlayer().opponent(g.getTerritory());
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("opponent");
    }
}