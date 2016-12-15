package simpl.typing;

import simpl.parser.Symbol;

public class DefaultTypeEnv extends TypeEnv {

    private TypeEnv E;

    public DefaultTypeEnv() {
        // TODO
        E = TypeEnv.empty;
        AllType fstPair = new AllType(true);
        AllType sndPair = new AllType(true);
        AllType hdPair = new AllType(true);
        AllType tlPair = new AllType(true);

        E = TypeEnv.of(E,Symbol.symbol("fst"), new ArrowType(new PairType(fstPair, new AllType(true)), fstPair));
        E = TypeEnv.of(E,Symbol.symbol("snd"), new ArrowType(new PairType(new AllType(true), sndPair), sndPair));
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
