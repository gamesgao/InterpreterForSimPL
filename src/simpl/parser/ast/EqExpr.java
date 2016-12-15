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
        Substitution S = lResult.s.compose(rResult.s);
        Type lt = S.apply(lResult.t);
        Type rt = S.apply(rResult.t);
        if(lt.isEqualityType() && rt.isEqualityType()){
            return TypeResult.of(S.compose(lt.unify(rt)), Type.BOOL);
        }
        throw new TypeMismatchError();
    }
}
