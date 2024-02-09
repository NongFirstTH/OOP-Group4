package Grammar.Expression;
import GamePlay.Game;
import java.util.Random;

public record Variable(String name) implements Expression {
    @Override
    public long eval(Game g) throws EvalError {
        switch (name){
            case "rows" -> {
                return g.getPlayer().getRow();
            }
            case "cols" -> {
                return g.getPlayer().getCol();
            }
            case "currow" -> {
                return g.getPlayer().getCurrow();
            }
            case "curcol" -> {
                return g.getPlayer().getCurcol();
            }
            case "budget" -> {
                return g.getPlayer().getBudget();
            }
            case "deposit" -> {
                return (long) g.getPlayer().getDeposit(g.getTerritory());
            }
            case "interest" -> {
                return g.getPlayer().getInterest(g.getTerritory());
            }
            case "maxdeposit" -> {
                return g.getMaxDeposit();
            }
            case "random" -> {
                Random random = new Random();
                return random.nextInt(1000);
            }
        }
        if (g.getPlayer().bindings().containsKey(name))
            return g.getPlayer().bindings().get(name);
        if(isIdentifier(name)) return 0;
        throw new EvalError(name);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }
    private boolean isIdentifier(String name) {
        return name.matches("[a-zA-Z][a-zA-Z0-9]*+");
    }
}

