package com.websocket.demo.Grammar.Expression;

import com.websocket.demo.GamePlay.Game;
import com.websocket.demo.Grammar.EvalError;

public interface Expression {

    /**
     * Evaluates the expression in the context of the specified game.
     *
     * @param g the game
     * @return the result of evaluating the expression
     * @throws EvalError if there is an error evaluating the expression
     */
    long eval(Game g) throws EvalError;

    /**
     * Generates a representation of the expression and appends it to the specified StringBuilder.
     *
     * @param s the StringBuilder to which the representation is appended
     */
    void prettyPrint(StringBuilder s);
}