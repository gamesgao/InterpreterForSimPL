package simpl.typing;

import simpl.parser.Symbol;

public class TypeVar extends Type {

    private static int tvcnt = 0;

    private boolean equalityType;
    private Symbol name;

    public TypeVar(boolean equalityType) {
        this.equalityType = equalityType;
        name = Symbol.symbol("tv" + ++tvcnt);
    }

    @Override
    public boolean isEqualityType() {
        return equalityType;
    }

    @Override
    public Substitution unify(Type t) throws TypeCircularityError {
        // TODO
        if(t.contains(this)) throw new TypeCircularityError();
        else{
            if(t instanceof TypeVar){
                return Substitution.of(this, t);
            }
            else{
                return Substitution.of(this, t);
            }
        }
    }

    public String toString() {
        return ""+name;
    }

    @Override
    public boolean contains(TypeVar tv) {
        // TODO
        if(tv.equals(this)) return true;
        return false;
    }

    @Override
    public Type replace(TypeVar a, Type t) {
        // TODO
        if(this.contains(a)) return t;
        return this;
    }
}
