package simpl.typing;

public abstract class Type {

    public abstract boolean isEqualityType();

    public abstract Type replace(AllType a, Type t);

    public abstract boolean contains(AllType tv);

    public abstract Substitution unify(Type t) throws TypeError;

    public static final Type INT = new IntType();
    public static final Type BOOL = new BoolType();
    public static final Type UNIT = new UnitType();
}
