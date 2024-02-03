package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record Opponent() implements Expression {
    @Override
    public long eval(Player p, Territory t){
        return p.opponent(t);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("opponent");
    }
}