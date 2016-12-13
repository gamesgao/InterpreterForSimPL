package simpl.interpreter;

import simpl.typing.RefType;

public class RefValue extends Value {

    public final int p;

    public boolean mark;

    public RefValue(int p) {
        this.p = p;
        this.mark = false;
    }

    public String toString() {
        return "ref@" + p;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if(other instanceof RefValue){
            return p == ((RefValue) other).p;
        }
        return false;
    }

    @Override
    public void mark(Mem M) {
        this.mark = true;
        //TODO
        M.get(this.p).mark(M);
    }
}
