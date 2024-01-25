package Grammar.Expression;

import java.util.Map;

public record Variable(String name)
        implements Expression {
    public int eval(Map<String, Integer> bindings) {
        return 0;
    }
}

