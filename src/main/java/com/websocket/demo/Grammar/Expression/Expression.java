package com.websocket.demo.Grammar.Expression;

import com.websocket.demo.GamePlay.Game;

public interface Expression {
    long eval(Game g) throws EvalError;
    void prettyPrint(StringBuilder s);
}

