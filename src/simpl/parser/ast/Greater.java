package simpl.parser.ast;

import simpl.interpreter.*;

public class Greater extends RelExpr {

    public Greater(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " > " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        IntValue v1 = (IntValue) l.eval(s);
        IntValue v2 = (IntValue) r.eval(s);
        return new BoolValue(v1.n > v2.n);
    }
}
