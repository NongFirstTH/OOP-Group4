package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record NoStatement() implements Plan {
    @Override
    public boolean eval(Map<String, Long> bindings, Player p, Territory t) {
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
    }
}