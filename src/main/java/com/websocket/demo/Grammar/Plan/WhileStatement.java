package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.EvalError;
import com.websocket.demo.Grammar.Expression.Expression;

public record WhileStatement (Expression expr, Plan p1) implements Plan {
    @Override
    public boolean eval(Game g) throws EvalError {
        for (int counter = 0; counter < 10000 && expr.eval(g)>0; counter++) {
            if(!p1.eval(g)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
        s.append("\t".repeat(Math.max(0, tab))).append("while (");
        expr.prettyPrint(s);
        s.append(") {\n");
        p1.prettyPrint(s, tab+1);
        s.append("\t".repeat(Math.max(0, tab))).append("}\n");
    }
}