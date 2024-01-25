package Grammar.Expression;

import java.util.Map;

public interface Expression {
    int eval(Map<String, Integer> bindings);
}

