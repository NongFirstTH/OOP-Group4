package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record BinaryArithExpr(Expression left, String op, Expression right) implements Expression {
    @Override
    public long eval(Map<String, Integer> bindings, Player p, Territory t) throws EvalError {
        long lv = left.eval(bindings,p,t);
        long rv = right.eval(bindings,p,t);
        switch (op) {
            case "+" -> {
                return lv + rv;
            }
            case "-" -> {
                return lv - rv;
            }
            case "*" -> {
                return lv * rv;
            }
            case "/" -> {
                return lv / rv;
            }
            case "%" -> {
                return lv % rv;
            }
            case "^" -> {
                return (long) Math.pow(lv, rv);
            }
        }
        throw new EvalError("unknown op: " + op);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append('(');
        left.prettyPrint(s);
        s.append(" ").append(op).append(" ");
        right.prettyPrint(s);
        s.append(')');
    }
}

