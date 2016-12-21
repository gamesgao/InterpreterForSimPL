package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lResult = l.typecheck(E);
        TypeResult rResult = r.typecheck(lResult.s.compose(E));
        Substitution S = lResult.s.compose(rResult.s);
        S = S.compose(S.apply(lResult.t).unify(Type.BOOL));
        S.compose(S.apply(rResult.t).unify(Type.BOOL));
        return TypeResult.of(S, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        if(l.eval(s).equals(new BoolValue(true))){
            return r.eval(s);
        }
        else{
            return new BoolValue(false);
        }
    }
}
