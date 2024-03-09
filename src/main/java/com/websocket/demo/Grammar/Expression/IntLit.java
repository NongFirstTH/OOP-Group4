package com.websocket.demo.Grammar.Expression;

import com.websocket.demo.GamePlay.Game;

public record IntLit(long val) implements Expression {
    @Override
    public long eval(Game g) {
        return val;
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(val);
    }
}
