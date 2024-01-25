package Grammar.Expression;

import java.util.Map;

public record Nearby(String name, String direction) implements Expression {
    public int eval(Map<String, Integer> bindings) {
        return 0;
    }
}
