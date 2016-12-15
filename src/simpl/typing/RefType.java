package simpl.typing;

public final class RefType extends Type {

    public Type t;

    public RefType(Type t) {
        this.t = t;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return true;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if(t instanceof AllType){
            return t.unify(this);
        }
        if(t instanceof RefType){
            return ((RefType) t).t.unify(this.t);
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(AllType tv) {
        // TODO
        return t.contains(tv);
    }

    @Override
    public Type replace(AllType a, Type t) {
        // TODO
        if(this.t.contains(a)){
            this.t=this.t.replace(a, t);
        }
        return this;
    }

    public String toString() {
        return t + " ref";
    }
}
