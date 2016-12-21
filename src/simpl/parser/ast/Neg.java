package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Neg extends UnaryExpr {

    public Neg(Expr e) {
        super(e);
    }

    public String toString() {
        return "~" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult eResult = e.typecheck(E);
        Substitution S = eResult.s;
        S = S.compose(S.apply(eResult.t).unify(Type.INT));
        return TypeResult.of(S, Type.INT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        IntValue v = (IntValue) e.eval(s);
        return new IntValue(-v.n);
    }
}
