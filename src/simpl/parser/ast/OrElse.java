package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class OrElse extends BinaryExpr {

    public OrElse(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " orelse " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lResult = l.typecheck(E);
        TypeResult rResult = r.typecheck(lResult.s.compose(E));
        Substitution S = lResult.s.compose(rResult.s);
        S = S.compose(S.apply(lResult.t).unify(Type.BOOL));
        S = S.compose(S.apply(rResult.t).unify(Type.BOOL));
        return TypeResult.of(S, Type.BOOL);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        if(l.eval(s).equals(new BoolValue(false))){
            return r.eval(s);
        }
        else{
            return new BoolValue(true);
        }
    }
}
