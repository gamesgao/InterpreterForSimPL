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

public class Loop extends Expr {

    public Expr e1, e2;

    public Loop(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String toString() {
        return "(while " + e1 + " do " + e2 + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult e1Result = e1.typecheck(E);
        TypeResult e2Result = e2.typecheck(e1Result.s.compose(E));
        Substitution S = e1Result.s.compose(e2Result.s);
        S = S.compose(S.apply(e1Result.t).unify(Type.BOOL));
        return TypeResult.of(S, Type.UNIT);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        if(e1.eval(s).equals(new BoolValue(true))){
            Seq e = new Seq(e2,new Loop(e1,e2));
            return e.eval(s);
        }
        else{
            return Value.UNIT;
        }
    }
}
