package simpl.typing;

import simpl.parser.Symbol;

public class AllType extends Type {

//    private static int tvcnt = 0;

    private boolean equalityType;
//    private Symbol name;

    public AllType(boolean equalityType) {
        this.equalityType = equalityType;
//        name = Symbol.symbol("tv" + ++tvcnt);
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
            if(t instanceof AllType){
                return Substitution.of(this, t);
            }
            else{
                return Substitution.of(this, t);
            }
        }
    }

    public String toString() {
        return "All";
    }

    @Override
    public boolean contains(AllType tv) {
        // TODO
        if(tv.equals(this)) return true;
        return false;
    }

    @Override
    public Type replace(AllType a, Type t) {
        // TODO
        if(this.contains(a)) return t;
        return this;
    }
}
