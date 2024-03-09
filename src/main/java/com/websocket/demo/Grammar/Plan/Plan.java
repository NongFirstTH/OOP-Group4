package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.Expression.EvalError;

public interface Plan{

    /**
     * Returns false if turn is ended otherwise true
     */
    boolean eval(Game g) throws EvalError;

    void prettyPrint(StringBuilder s, int tab);
}