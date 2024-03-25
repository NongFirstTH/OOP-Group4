package com.websocket.demo.Grammar.Plan;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.EvalError;

public interface Plan {

    /**
     * Evaluates the plan in the context of the specified game.
     *
     * @param g the game
     * @return true if the plan is still valid and the turn is not ended, false otherwise
     * @throws EvalError if there is an error evaluating the plan
     */
    boolean eval(Game g) throws EvalError;

    /**
     * Adds this statement to a new line of the provided StringBuilder with proper indentation.
     *
     * @param s   the StringBuilder to add the statement to
     * @param tab the number of tabs for indentation
     */
    void prettyPrint(StringBuilder s, int tab);
}
