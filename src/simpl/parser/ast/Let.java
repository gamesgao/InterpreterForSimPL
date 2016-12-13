package simpl.parser.ast;

import simpl.interpreter.Env;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Let extends Expr {

    public Symbol x;
    public Expr e1, e2;

    public Let(Symbol x, Expr e1, Expr e2) {
        this.x = x;
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(let " + x + " = " + e1 + " in " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult e1Result = e1.typecheck(E);
        TypeResult e2Result = e2.typecheck(e1Result.s.compose(TypeEnv.of(E, x, e1Result.t)));
        Substitution S = e1Result.s.compose(e2Result.s);
        return TypeResult.of(S,S.apply(e2Result.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v1 = e1.eval(s);
        return e2.eval(State.of(new Env(s.E,x,v1),s.M,s.p,s.R));
    }
}
