package Grammar.Expression;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record IntLit(long val) implements Expression {
    @Override
    public long eval(Game g) {
        return val;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(val);
    }
}
