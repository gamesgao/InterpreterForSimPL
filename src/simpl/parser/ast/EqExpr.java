package simpl.parser.ast;

import simpl.typing.*;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult lResult = l.typecheck(E);
        TypeResult rResult = r.typecheck(lResult.s.compose(E));
        if(lResult.t.isEqualityType() && rResult.t.isEqualityType()){
            return TypeResult.of(lResult.s.compose(rResult.s.compose(lResult.t.unify(rResult.t))), Type.BOOL);
        }
        throw new TypeMismatchError();
    }
}
