package com.websocket.demo.Grammar.Expression;
import com.websocket.demo.Grammar.Expression.EvalError;
import com.websocket.demo.Grammar.Expression.IntLit;
import com.websocket.demo.Grammar.Parse.SyntaxError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntLitTest {
    public long result(long num) throws SyntaxError, EvalError {
        IntLit a = new IntLit(num);
        return a.eval(null);
    }
    @Test
    public void TestEval() throws SyntaxError, EvalError {
        assertEquals(5,result(5));
        assertEquals(-7,result(-7));
        assertEquals(100000000,result(100000000));
    }

    public String resultPrettyPrint(long num){
        IntLit a = new IntLit(num);
        StringBuilder s = new StringBuilder();
        a.prettyPrint(s);
        return s.toString();
    }
    @Test
    public  void TestPrettyPrint(){
        assertEquals("2",resultPrettyPrint(2));
        assertEquals("-2",resultPrettyPrint(-2));
        assertEquals("6541232",resultPrettyPrint(6541232));
        assertEquals("-6541232",resultPrettyPrint(-6541232));
    }
}