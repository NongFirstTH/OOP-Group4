package Grammar.Plan;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;

public interface Plan{
    boolean eval(HashMap<String, Integer> bindings, Player p, Territory t);
}