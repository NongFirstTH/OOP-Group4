package Grammar.Expression;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public interface Expression {
    long eval(Game g) throws EvalError;
    void prettyPrint(StringBuilder s);
}

