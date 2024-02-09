package Grammar.Expression;

import GamePlay.Game;

public interface Expression {
    long eval(Game g) throws EvalError;
    void prettyPrint(StringBuilder s);
}

