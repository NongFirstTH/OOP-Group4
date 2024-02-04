package Grammar.Plan;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record Relocate () implements Plan {
    @Override
    public boolean eval(Game g) {
        return g.getPlayer().relocate();
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("relocate\n");
    }
}
