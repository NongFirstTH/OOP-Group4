package Grammar.Expression;

public record BinaryArithExpr(Expr left, String op, Expr right)
        implements Expr {
    public int eval(Map<String, Integer> bindings)
}

