package Grammar.Parse;

import Grammar.Expression.*;
import Grammar.Plan.*;

import java.util.Map;

interface Parser {
    /** Attempts to parse the token stream
     *  given to this parser.
     *  throws: SyntaxError if the token
     *          stream cannot be parsed */
    Plan parse() throws SyntaxError;
}

public class PlanParser implements  Parser {
    private final PlanTokenizer tkz;

    public PlanParser(PlanTokenizer tkz) {
        this.tkz = tkz;
    }
    public Plan parse() throws SyntaxError {
        // begin parsing at start symbol
        Plan v = parsePlan();
        // reject if there is remaining token
        if (tkz.hasNextToken())
            throw new SyntaxError("leftover token");

        return v;
    }

    private Plan parsePlan() throws SyntaxError {
        Plan p = parseStatement();
        while (tkz.hasNextToken()&&!tkz.peek().matches("[\"then\"\"else\"]")) {
            p = new BinaryPlan(p, parseStatement());
        }
        return p;
    }

    private Plan parseStatement() throws SyntaxError {
        String peek = tkz.peek();
        return switch (peek) {
            case "{" -> parseBlockStatement();
            case "if" -> parseIfStatement();
            case "while" -> parseWhileStatement();
            default -> parseCommand();
        };
    }

    private Plan parseCommand() throws SyntaxError {
        String peek = tkz.peek();
        if (peek.equals("done")||peek.equals("relocate")||peek.equals("move")||peek.equals("shoot")||peek.equals("invest")||peek.equals("collect")) {
            return parseActionCommand();
        }
        return parseAssignmentStatement();
    }

    private Plan parseAssignmentStatement() throws SyntaxError {
        String identifier = tkz.consume();
        tkz.consume("=");
        return new AssignmentStatement(identifier, parseExpression());
    }

    private Plan parseActionCommand() throws SyntaxError {
        String peek = tkz.consume();
        return switch (peek) {
            case "done" -> new Done();
            case "relocate" -> new Relocate();
            case "move" -> new MoveCommand(tkz.consume());
            case "shoot" -> new AttackCommand(tkz.consume(), parseExpression());
            default -> new RegionCommand(peek, parseExpression());
        };
    }

    private Plan parseBlockStatement() throws SyntaxError {
        tkz.consume("{");
        Plan p = new NoStatement();
        while (tkz.hasNextToken()&&!tkz.peek().equals("}")) {
            p = new BinaryPlan(p, parseStatement());
        }
        tkz.consume("}");
        return p;
    }

    private Plan parseIfStatement() throws SyntaxError {
        tkz.consume("if");
        tkz.consume("(");
        Expression expr = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Plan p = parseStatement();
        tkz.consume("else");
        return new IfStatement(expr, p, parseStatement());
    }

    private Plan parseWhileStatement() throws SyntaxError {
        tkz.consume("while");
        tkz.consume("(");
        Expression expr = parseExpression();
        tkz.consume(")");
        return new WhileStatement(expr, parseStatement());
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
        if(tkz.peek().equals("opponent")){
            return new Opponent(tkz.consume());
        } else {
            return new Nearby(tkz.consume(), tkz.consume());
        }
    }
    private boolean isNumber(String s) {
        return s.matches("\\d+");
    }
}
