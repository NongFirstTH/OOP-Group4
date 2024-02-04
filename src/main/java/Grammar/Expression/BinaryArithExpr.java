package Grammar.Expression;

import GamePlay.Game;
import GamePlay.Player;
import GamePlay.Territory;

import java.util.Map;

public record BinaryArithExpr(Expression left, String op, Expression right) implements Expression {
    @Override
    public long eval(Game g) throws EvalError {
        long lv = left.eval(g);
        long rv = right.eval(g);
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

