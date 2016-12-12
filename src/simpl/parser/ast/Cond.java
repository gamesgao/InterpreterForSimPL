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

public class Cond extends Expr {

    public Expr e1, e2, e3;

    public Cond(Expr e1, Expr e2, Expr e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String toString() {
        return "(if " + e1 + " then " + e2 + " else " + e3 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult e1Result = e1.typecheck(E);
        TypeResult e2Result = e2.typecheck(e1Result.s.compose(E));
        TypeResult e3Result = e3.typecheck(e2Result.s.compose(e1Result.s.compose(E)));
        Substitution S = e1Result.s.compose(e2Result.s.compose(e3Result.s.compose(e1Result.t.unify(Type.BOOL).compose(e2Result.t.unify(e3Result.t)))));
        return TypeResult.of(S,S.apply(e2Result.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        if(e1.eval(s).equals(true)){
            return e2.eval(s);
        }
        else{
            return e3.eval(s);
        }
    }
}
