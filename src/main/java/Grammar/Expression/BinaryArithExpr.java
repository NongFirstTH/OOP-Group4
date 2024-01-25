package Grammar.Expression;

import java.util.Map;

//test
public record BinaryArithExpr(Expression left, String op, Expression right)
        implements Expression {
    public int eval(Map<String, Integer> bindings) {
        return 0;
    }
}

