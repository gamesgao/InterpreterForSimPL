package simpl.parser.ast;

import com.sun.org.apache.regexp.internal.RE;
import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        return TypeResult.of(E.get(x));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value v = s.E.get(x);
        if(v instanceof RecValue){
            Rec e = new Rec(((RecValue) v).x,((RecValue) v).e);
            return e.eval(State.of(((RecValue) v).E,s.M,s.p,s.R));
        }
        else{
            return v;
        }
    }
}
