package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.parser.ast.Add;
import simpl.parser.ast.Expr;
import simpl.parser.ast.IntegerLiteral;
import simpl.parser.ast.Name;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class pred extends FunValue {

    public pred() {
        // TODO
        super(Env.empty, Symbol.symbol("predParameter"), new Add(new Name(Symbol.symbol("predParameter")), new IntegerLiteral(-1)));
    }
}
