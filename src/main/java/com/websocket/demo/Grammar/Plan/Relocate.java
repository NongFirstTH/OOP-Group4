package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;

public record Relocate () implements Plan {
    @Override
    public boolean eval(Game g) {
        g.getPlayer().relocate(g.getTerritory());
        return false;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("relocate\n");
    }
}
