package com.websocket.demo.Grammar.Parse;

import com.websocket.demo.Grammar.Plan.Direction;

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
            case "up" -> Direction.up;
            case "down" -> Direction.down;
            case "upleft" -> Direction.upleft;
            case "upright" -> Direction.upright;
            case "downleft" -> Direction.downleft;
            case "downright" -> Direction.downright;
            default -> throw new SyntaxError("Expected direction: " + peek);
        };
    }
}
