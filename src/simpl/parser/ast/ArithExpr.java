package simpl.parser.ast;

import simpl.typing.*;

public abstract class ArithExpr extends BinaryExpr {

    public ArithExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lResult = l.typecheck(E);
        TypeResult rResult = r.typecheck(lResult.s.compose(E));
        Substitution S = lResult.s.compose(rResult.s);
        S = S.compose(S.apply(lResult.t).unify(Type.INT));
        S = S.compose(S.apply(rResult.t).unify(Type.INT));
        return TypeResult.of(S, Type.INT);
    }
}
