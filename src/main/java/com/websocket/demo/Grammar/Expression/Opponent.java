package com.websocket.demo.Grammar.Expression;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.EvalError;

public record Opponent() implements Expression {
    @Override
    public long eval(Game g) throws EvalError {
        return g.getPlayer().opponent(g.getTerritory());
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("opponent");
    }
}