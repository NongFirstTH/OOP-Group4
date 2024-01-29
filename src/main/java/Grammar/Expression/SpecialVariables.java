package Grammar.Expression;
import GamePlay.Player;
import GamePlay.Territory;
import java.util.HashMap;
import java.util.Random;

record SpecialVariables(String var) implements Expression {
    @Override
    public double eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        switch (var){
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
                return p.getDeposit();
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
        throw new EvalError("unknown variable: " + var);
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(var);
    }
}
