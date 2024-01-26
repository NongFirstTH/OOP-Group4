package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;
import java.util.Map;

record SpecialVariables(String name) implements Expression {
    public int eval(Map<String, Integer> bindings){
        return 0;
    }

    @Override
    public int eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
}
