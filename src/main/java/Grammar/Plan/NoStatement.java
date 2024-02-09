package Grammar.Plan;

import GamePlay.Game;

public record NoStatement() implements Plan {
    @Override
    public boolean eval(Game g) {
        return true;
    }

    @Override
    public void prettyPrint(StringBuilder s, int tab) {
    }
}