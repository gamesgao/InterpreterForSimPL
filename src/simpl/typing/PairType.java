package simpl.typing;

public final class PairType extends Type {

    public Type t1, t2;

    public PairType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if(t instanceof TypeVar){
            return t.unify(this);
        }
        if(t instanceof PairType){
            return ((PairType) t).t1.unify(t1).compose(((PairType) t).t2.unify(t2));
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        return t1.contains(tv)||t2.contains(tv);
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        if(t1.contains(a)){
            t1 = t1.replace(a, t);
        }
        if(t2.contains(a)){
            t2 = t2.replace(a, t);
        }
        return this;
    }

    public String toString() {
        return "(" + t1 + " * " + t2 + ")";
    }
}
