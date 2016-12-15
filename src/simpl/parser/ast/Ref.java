package simpl.parser.ast;

import simpl.interpreter.*;
import simpl.interpreter.pcf.GarbageCollection;
import simpl.typing.*;

public class Ref extends UnaryExpr {

    public Ref(Expr e) {
        super(e);
    }

    public String toString() {
        return "(ref " + e + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult eResult = e.typecheck(E);
        Substitution S = eResult.s;
        return TypeResult.of(S, S.apply(new RefType(S.apply(eResult.t))));
    }

    @Override
    public Value eval(State s) throws RuntimeError{
        // TODO
        int p = s.p.get();
        s.p.set(p+1);
        RefValue r = new RefValue(p);
        Value v = e.eval(State.of(s.E, s.M, s.p,s.R));
        if(s.M.overFlow()){
            (new GarbageCollection(s.M, s.E, s.R)).run();
//            System.out.println(s.M.size());
        }
        s.M.put(p,v);
        s.R.enqueue(r);
        return r;
    }
}
