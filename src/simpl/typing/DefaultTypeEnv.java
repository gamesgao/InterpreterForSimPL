package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        E = TypeEnv.empty;
        TypeVar fstPair = new TypeVar(true);
        TypeVar sndPair = new TypeVar(true);
        TypeVar hdPair = new TypeVar(true);
        TypeVar tlPair = new TypeVar(true);

        E = TypeEnv.of(E,Symbol.symbol("fst"), new ArrowType(new PairType(fstPair, new TypeVar(true)), fstPair));
        E = TypeEnv.of(E,Symbol.symbol("snd"), new ArrowType(new PairType(new TypeVar(true), sndPair), sndPair));
        E = TypeEnv.of(E,Symbol.symbol("hd"), new ArrowType(new ListType(hdPair), hdPair));
        E = TypeEnv.of(E, Symbol.symbol("tl"), new ArrowType(new ListType(tlPair), new ListType(tlPair)));
        E = TypeEnv.of(E, Symbol.symbol("iszero"), new ArrowType(Type.INT, Type.BOOL));
        E = TypeEnv.of(E, Symbol.symbol("pred"), new ArrowType(Type.INT, Type.INT));
        E = TypeEnv.of(E, Symbol.symbol("succ"), new ArrowType(Type.INT, Type.INT));

    }

    @Override
    public Type get(Symbol x) {
        return E.get(x);
    }
}
