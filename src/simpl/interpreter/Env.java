package simpl.interpreter;

import simpl.parser.Symbol;

public class Env {

    private final Env E;
    private final Symbol x;
    private final Value v;

    private Env() {
        E = null;
        x = null;
        v = null;
    }

    public static Env empty = new Env() {
        public Value get(Symbol y) {
            return null;
        }

        public Env clone() {
            return this;
        }

        public void mark(Mem M){
            return;
        }
    };

    public Env(Env E, Symbol x, Value v) {
        this.E = E;
        this.x = x;
        this.v = v;
    }

    public Value get(Symbol y) {
        // TODO
        if(y.equals(x)){
            return v;
        }
        else{
            return E.get(y);
        }
    }

    public void mark(Mem M){
        //TODO
        v.mark(M);
        E.mark(M);
    }

    public Env clone() {
        // TODO
        return null;
    }
}
