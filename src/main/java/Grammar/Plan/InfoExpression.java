package Grammar.Plan;

public record InfoExpression(String command, String dir)
        implements Expr {
    public int eval(Map<String, Integer> bindings)
}

