package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;


public record MoveCommand (Direction dir) implements Plan {
    @Override
    public boolean eval(Map<String, Integer> bindings, Player p, Territory t) {
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("move ").append(dir).append("\n");
    }

    public boolean move(Player player, Territory territory) {
        return player.move(dir,territory);
    }
}
