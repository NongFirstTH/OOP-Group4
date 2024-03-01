package com.websocket.demo.Grammar.Parse;

import com.websocket.demo.Grammar.Expression.*;
import com.websocket.demo.Grammar.Expression.*;

public class ExpressionParser implements Parser<Expression> {

    private final PlanTokenizer tkz;

    public ExpressionParser(PlanTokenizer tkz) {
        this.tkz = tkz;
    }

    @Override
    public Expression parse() throws SyntaxError {
        return parseExpression();
    }

    private Expression parseExpression() throws SyntaxError {
        Expression e = parseTerm();
        while (tkz.hasNextToken()&&tkz.peek().matches("[+-]")) {
            String peek = tkz.consume();
            if (peek.equals("+")) e = new BinaryArithExpr(e, "+", parseTerm());
            else e = new BinaryArithExpr(e, "-", parseTerm());
        }
        return e;
    }

    private Expression parseTerm() throws SyntaxError {
        Expression e = parseFactor();
        while (tkz.hasNextToken()&&tkz.peek().matches("[*/%]")) {
            String peek = tkz.consume();
            if (peek.equals("*")) e = new BinaryArithExpr(e, "*", parseFactor());
            else if (peek.equals("/")) e = new BinaryArithExpr(e, "/", parseFactor());
            else e = new BinaryArithExpr(e, "%", parseFactor());
        }
        return e;
    }

    private Expression parseFactor() throws SyntaxError {
        Expression e = parsePower();
        if (tkz.hasNextToken() && tkz.peek("^")) {
            tkz.consume("^");
            e = new BinaryArithExpr(e, "^", parseFactor());
        }
        return e;
    }

    private Expression parsePower() throws SyntaxError {
        String peek = tkz.peek();
        if (isNumber(peek)) {
            return new IntLit(Integer.parseInt(tkz.consume()));
        } else if (peek.equals("(")){
            tkz.consume("(");
            Expression e = parseExpression();
            tkz.consume(")");
            return e;
        } else if (peek.equals("opponent")||peek.equals("nearby")){
            return parseInfoExpression();
        } else {
            return new Variable(tkz.consume());
        }
    }

    private Expression parseInfoExpression() throws SyntaxError {
        if(tkz.consume().equals("opponent")){
            return new Opponent();
        } else {
            return new Nearby(new DirectionParser(tkz).parse());
        }
    }
    private boolean isNumber(String s) {
        return s.matches("\\d+");
    }
}
