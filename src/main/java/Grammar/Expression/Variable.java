package Grammar.Expression;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;
import java.util.Random;

public record Variable(String name) implements Expression {
    @Override
    public long eval(Player p, Territory t) throws EvalError {
        switch (name){
            case "rows" -> {
                return p.getRow();
            }
            case "cols" -> {
                return p.getCol();
            }
            case "currow" -> {
                return p.getCurrow();
            }
            case "curcol" -> {
                return p.getCurcol();
            }
            case "budget" -> {
                return p.getBudget();
            }
            case "deposit" -> {
                return (long) p.getDeposit();
            }
            case "interest" -> {
                return p.getInterest();
            }
            case "maxdeposit" -> {
                return p.getMaxDeposit();
            }
            case "random" -> {
                Random random = new Random();
                return random.nextInt(1000);
            }
        }
        if (p.bindings().containsKey(name))
            return p.bindings().get(name);
        return 0;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }
}

