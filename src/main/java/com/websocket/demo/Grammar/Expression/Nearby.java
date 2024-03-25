package com.websocket.demo.Grammar.Expression;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Plan.Direction;

public record Nearby(Direction direction) implements Expression {
    @Override
    public long eval(Game g) throws EvalError {
        return  g.getPlayer().nearby(g.getTerritory(), direction);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("nearby ").append(direction);
    }
}
