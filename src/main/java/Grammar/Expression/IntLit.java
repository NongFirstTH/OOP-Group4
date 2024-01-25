package Grammar.Expression;

public record IntLit(int val) implements Expr {
    public int eval(Map<String, Integer> bindings)
}
