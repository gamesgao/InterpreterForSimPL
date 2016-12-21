package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Assign extends BinaryExpr {

    public Assign(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return l + " := " + r;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lResult = l.typecheck(E);
        TypeResult rResult = r.typecheck(lResult.s.compose(E));
        Substitution S = lResult.s.compose(rResult.s);
        S = S.compose(S.apply(lResult.t).unify(new RefType(S.apply(rResult.t))));
        return TypeResult.of(S, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue v1 = (RefValue) l.eval(s);
        Value v2 = r.eval(s);
        s.M.put(v1.p,v2);
        return Value.UNIT;
    }
}
