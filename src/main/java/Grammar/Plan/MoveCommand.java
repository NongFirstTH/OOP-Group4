package Grammar.Plan;

import Grammar.Plan.Direction;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;


public record MoveCommand (Direction dir) implements Plan {
    @Override
    public boolean eval(Map<String, Long> bindings, Player p, Territory t) {
        return p.move(dir,t);
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("move ").append(dir).append("\n");
    }
}
