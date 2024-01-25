package Grammar.Expression;

import java.util.Map;

public record Opponent(String name) implements Expression {
    public int eval(Map<String, Integer> bindings) {
        return 0;
    }
}