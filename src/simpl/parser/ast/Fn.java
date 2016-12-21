package simpl.parser.ast;

import simpl.interpreter.FunValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.*;

public class Fn extends Expr {

    public Symbol x;
    public Expr e;

    public Fn(Symbol x, Expr e) {
        this.x = x;
        this.e = e;
    }

    public String toString() {
        return "(fn " + x + "." + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeVar t = new TypeVar(true);
        TypeResult eResult = e.typecheck(TypeEnv.of(E,x,t));
        Substitution S = eResult.s;
        return TypeResult.of(S, S.apply(new ArrowType(t, S.apply(eResult.t))));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new FunValue(s.E,x,e);
    }
}
