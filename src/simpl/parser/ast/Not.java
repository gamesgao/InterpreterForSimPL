package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Not extends UnaryExpr {

    public Not(Expr e) {
        super(e);
    }

    public String toString() {
        return "(not " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult eResult = e.typecheck(E);
        Substitution S = eResult.s;
        S = S.compose(S.apply(eResult.t).unify(Type.BOOL));
        return TypeResult.of(S, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        BoolValue v = (BoolValue) e.eval(s);
        return new BoolValue(!v.b);
    }
}
