package simpl.parser.ast;

import simpl.interpreter.ConsValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.*;

public class Cons extends BinaryExpr {

    public Cons(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " :: " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lResult = l.typecheck(E);
        TypeResult rResult = r.typecheck(lResult.s.compose(E));
        Substitution S = lResult.s.compose(rResult.s);
        S = S.compose(S.apply(rResult.t).unify(new ListType(S.apply(lResult.t))));
        return TypeResult.of(S,S.apply(rResult.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new ConsValue(l.eval(s),r.eval(s));
    }
}
