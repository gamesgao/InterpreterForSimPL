package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult eResult = e.typecheck(E);
        TypeVar t = new TypeVar(true);
        Substitution S = eResult.s;
        S = S.compose(S.apply(eResult.t).unify(new RefType(t)));
        return TypeResult.of(S,S.apply(t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        RefValue v1 = (RefValue) e.eval(s);
        return s.M.get(v1.p);
    }
}
