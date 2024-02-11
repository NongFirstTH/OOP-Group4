package Grammar.Expression;

import GamePlay.Game;
import Grammar.Plan.Direction;

public record Nearby(Direction direction) implements Expression {
    @Override
    public long eval(Game g) throws EvalError {
        return  g.getPlayer().nearby(g.getTerritory(), direction);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("nearby ").append(direction);
    }
}
