package Grammar.Expression;

import GamePlay.Player;
import GamePlay.Territory;

import java.util.HashMap;

public record BinaryArithExpr(Expression left, String op, Expression right) implements Expression {
    @Override
    public int eval(HashMap<String, Integer> bindings, Player p, Territory t) throws EvalError {
        int lv = left.eval(bindings,p,t);
        int rv = right.eval(bindings,p,t);
        if (op.equals("+")) return lv + rv;
        if (op.equals("-")) return lv - rv;
        if (op.equals("*")) return lv * rv;
        if (op.equals("/")) return lv / rv;
        if (op.equals("%")) return lv % rv;
        throw new EvalError("unknown op: " + op);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
    }
}

