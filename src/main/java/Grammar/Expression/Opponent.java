package Grammar.Expression;

import java.util.Map;

public record Opponent(String command) implements Expression {
    public int eval(Map<String, Integer> bindings) {
        return 99;
    }
}