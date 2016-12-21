package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult eResult = e.typecheck(E);
        Substitution S = eResult.s;
        return TypeResult.of(S, S.apply(new RefType(S.apply(eResult.t))));
    }

    @Override
    public Value eval(State s) throws RuntimeError{
        // TODO
        int p = s.p.get();
        s.p.set(p+1);
        Value v = e.eval(s);
        RefValue r = new RefValue(p, v);
        s.M.put(p,v);
        return r;
    }
}
