package Grammar.Parse;

import Grammar.Plan.Direction;

import static Grammar.Plan.Direction.*;

public class DirectionParser implements Parser<Direction>{

    private final PlanTokenizer tkz;

    public DirectionParser(PlanTokenizer tkz) {
        this.tkz = tkz;
    }

    @Override
    public Direction parse() throws SyntaxError {
        return parseDirection();
    }

    private Direction parseDirection() throws SyntaxError {
        String peek = tkz.consume();

        return switch (peek) {
            case "up" -> up;
            case "down" -> down;
            case "upleft" -> upleft;
            case "upright" -> upright;
            case "downleft" -> downleft;
            case "downright" -> downright;
            default -> throw new SyntaxError("Expected direction: " + peek);
        };
    }
}
