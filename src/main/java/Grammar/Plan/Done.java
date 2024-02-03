package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record Done () implements Plan {
    @Override
    public boolean eval(Player p, Territory t) {
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("done\n");
    }
}
