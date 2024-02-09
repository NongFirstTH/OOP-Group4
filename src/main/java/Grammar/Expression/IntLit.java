package Grammar.Expression;

import GamePlay.Game;

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
