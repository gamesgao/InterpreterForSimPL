package simpl.interpreter;

public class RefValue extends Value {

    public final int p;
    public final Value v;

    public boolean mark;

    public RefValue(int p, Value v) {
        this.p = p;
        this.v = v;
        this.mark = false;
    }

    public String toString() {
        return "ref@" + this.v;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if(other instanceof RefValue){
            return p == ((RefValue) other).p;
        }
        return false;
    }
}
