package simpl.typing;

final class UnitType extends Type {

    protected UnitType() {
    }

    @Override
    public boolean isEqualityType() {
        return false;
    }

    @Override
    public Substitution unify(Type t) throws TypeError {
        if (t instanceof AllType) {
            return t.unify(this);
        }
        if (t instanceof UnitType) {
            return Substitution.IDENTITY;
        }
        throw new TypeMismatchError();
    }

    @Override
    public boolean contains(AllType tv) {
        return false;
    }

    @Override
    public Type replace(AllType a, Type t) {
        return Type.UNIT;
    }

    public String toString() {
        return "unit";
    }
}
