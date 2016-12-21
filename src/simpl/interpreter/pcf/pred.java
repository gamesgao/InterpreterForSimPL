package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.parser.Symbol;
import simpl.parser.ast.Add;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;

public class pred extends FunValue {

    public pred() {
        // TODO
        super(Env.empty, Symbol.symbol("predParameter"), new Add(new Name(Symbol.symbol("predParameter")), new IntegerLiteral(-1)));
    }
}
