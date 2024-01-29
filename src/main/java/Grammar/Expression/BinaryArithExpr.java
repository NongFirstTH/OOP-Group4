package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;

public record BinaryArithExpr(Expression left, String op, Expression right) implements Expression {
    @Override
    public double eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        double lv = left.eval(bindings,p,t);
        double rv = right.eval(bindings,p,t);
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
                return (int) Math.pow(lv, rv);
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

