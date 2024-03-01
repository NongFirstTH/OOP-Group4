package com.websocket.demo.GamePlay;

import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void gameInit () throws SyntaxError, EvalError {
        String s = """
                m=20
                n=15
                init_plan_min=5
                init_plan_sec=0
                init_budget=10000
                init_center_dep=100
                plan_rev_min=30
                plan_rev_sec=0
                rev_cost=100
                max_dep=1000000
                interest_pct=5
                """;
        Game g = new Game(s);
    }
}