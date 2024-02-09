package Grammar.Plan;

import GamePlay.Game;
import Grammar.Expression.EvalError;

public interface Plan{

    /**
     * Returns false if turn is ended otherwise true
     */
    boolean eval(Game g) throws EvalError;

    void prettyPrint(StringBuilder s, int tab);
}