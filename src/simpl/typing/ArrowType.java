package simpl.typing;

public final class ArrowType extends Type {

    public Type t1, t2;

    public ArrowType(Type t1, Type t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public boolean isEqualityType() {
        // TODO
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        // TODO
        if(t instanceof AllType){
            return t.unify(this);
        }
        if(t instanceof ArrowType){
            return ((ArrowType) t).t1.unify(t1).compose(((ArrowType) t).t2.unify(t2));
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(AllType tv) {
        // TODO

        return t1.contains(tv) || t2.contains(tv) ;
    }

    @Override
    public Type replace(AllType a, Type t) {
        // TODO
        if(t1.contains(a)){
            t1 =  t1.replace(a, t);
        }
        if(t2.contains(a)){
            t2 = t2.replace(a, t);
        }
        return this;
    }

    public String toString() {
        return "(" + t1 + " -> " + t2 + ")";
    }
}
