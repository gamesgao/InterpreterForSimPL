package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Rec extends Expr {

    public Symbol x;
    public Expr e;

    public Rec(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(rec " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar t = new TypeVar(true);
        TypeResult eResult = e.typecheck(TypeEnv.of(E, x, t));
        Substitution S = eResult.s;
        S = S.compose(S.apply(eResult.t).unify(t));
        return TypeResult.of(S, S.apply(t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return e.eval(State.of(new Env(s.E,x,new RecValue(s.E,x,e)),s.M,s.p));
    }
}
