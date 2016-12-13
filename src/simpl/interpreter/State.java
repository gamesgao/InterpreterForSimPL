package simpl.interpreter;

public class State {

    public final Env E;
    public final Mem M;
    public final Int p;
    public final RefC R;

    protected State(Env E, Mem M, Int p, RefC R) {
        this.E = E;
        this.M = M;
        this.p = p;
        this.R = R;
    }

    public static State of(Env E, Mem M, Int p, RefC R) {
        return new State(E, M, p, R);
    }
}
