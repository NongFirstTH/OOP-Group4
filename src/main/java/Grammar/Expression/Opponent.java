package Grammar.Expression;

import GamePlay.Game;
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