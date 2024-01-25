package Grammar.Expression;

import java.util.Map;

public record IntLit(int val) implements Expression {
    public int eval(Map<String, Integer> bindings) {
        return 0;
    }
}
