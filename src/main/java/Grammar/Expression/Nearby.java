package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;
import java.util.Map;

public record Nearby(String command, String direction) implements Expression {
    @Override
    public int eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
//        int x = distance enemy from citycrew
//       int y = digit deposit that region
//        if(){
//
//        }
        return  0;
    }
    @Override
    public void prettyPrint(StringBuilder s) {

    }
}
