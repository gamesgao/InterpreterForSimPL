package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.snd;
import simpl.interpreter.lib.tl;
import simpl.typing.*;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lResult = l.typecheck(E);
        TypeResult rResult = r.typecheck(lResult.s.compose(E));
        TypeVar a = new TypeVar(true);
        Substitution S = lResult.s.compose(rResult.s);
        S = S.compose(S.apply(lResult.t).unify(new ArrowType(S.apply(rResult.t), a)));
        return TypeResult.of(S,S.apply(a));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        FunValue v1 = (FunValue) l.eval(s);
        Value v2 = r.eval(s);
        if(v1 instanceof fst){
            return ((PairValue) v2).v1;
        }
        if(v1 instanceof snd){
            return ((PairValue) v2).v2;
        }
        if(v1 instanceof hd){
            return ((ConsValue) v2).v1;
        }
        if(v1 instanceof tl){
            return ((ConsValue) v2).v2;
        }
        return v1.e.eval(State.of(new Env(v1.E, v1.x, v2), s.M,s.p));
    }
}
