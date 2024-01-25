package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;
import Grammar.Plan.Plan;

import java.util.HashMap;

public record MoveCommand (String dir) implements Plan {
    @Override
    public boolean eval(HashMap<String, Integer> bindings, Player p, Territory t) {
        return true;
    }
}
