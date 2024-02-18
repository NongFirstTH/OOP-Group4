package GamePlay;

import Grammar.Expression.EvalError;
import Grammar.Parse.SyntaxError;

public class GameFactory {

    public Game newGame0P() throws SyntaxError, EvalError {
        return new Game(10, 10);
    }

    public Game newGame1P() throws SyntaxError, EvalError {
        Game g = new Game(10, 10);
        g.addPlayer("p1" ,5, 5, null);
        return g;
    }

    public Game newGame2P(int row1, int col1, int row2, int col2) throws SyntaxError, EvalError {
        Game g = new Game(10, 10);
        g.addPlayer("p1" ,row1, col1, null);
        g.addPlayer("p2" ,row2, col2, null);
        return g;
    }
}
