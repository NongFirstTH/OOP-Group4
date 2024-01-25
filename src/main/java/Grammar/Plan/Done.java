package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;

public record Done () implements Plan {
    @Override
    public boolean eval(HashMap<String, Integer> bindings, Player p, Territory t) {
        return false;
    }
}
