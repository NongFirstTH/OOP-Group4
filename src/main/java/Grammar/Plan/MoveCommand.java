package Grammar.Plan;

import GamePlay.Game;


public record MoveCommand (Direction dir) implements Plan {
    @Override
    public boolean eval(Game g) {
        return g.getPlayer().move(dir,g.getTerritory());
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("move ").append(dir).append("\n");
    }
}
