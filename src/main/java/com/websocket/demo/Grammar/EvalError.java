package com.websocket.demo.Grammar;

public class EvalError extends Throwable {
    public EvalError(String s) {
        super("EvalError: "+s);
    }
}
