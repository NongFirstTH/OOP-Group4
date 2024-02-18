package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;

public class GameFactory {

    public Game newGame0P() throws SyntaxError, EvalError {
        return new Game(10, 10);
    }

    public Game newGame1P(int row, int col) throws SyntaxError, EvalError {
        Game g = new Game(10, 10);
        g.addPlayerToTestOnly("p1" ,row, col, null);
        return g;
    }

    public Game newGame2P(int row1, int col1, int row2, int col2) throws SyntaxError, EvalError {
        Game g = new Game(10, 10);
        g.addPlayerToTestOnly("p1" ,row1, col1, null);
        g.addPlayerToTestOnly("p2" ,row2, col2, null);
        return g;
    }
}
