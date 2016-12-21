package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.Eq;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;

public class iszero extends FunValue {

    public iszero() {
        // TODO
        super(Env.empty, Symbol.symbol("iszeroParameter"), new Eq(new Name(Symbol.symbol("iszeroParameter")), new IntegerLiteral(0)));
    }
}
