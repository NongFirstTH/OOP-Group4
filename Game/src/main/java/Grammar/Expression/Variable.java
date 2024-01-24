package Grammar.Expression;

public record Variable(String name)
        implements Expr {
    public int eval(Map<String, Integer> bindings)
}

