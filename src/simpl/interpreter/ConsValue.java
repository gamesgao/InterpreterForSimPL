package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String toString() {
        // TODO
        return "list@" + this.getLength();
    }

    public int getLength(){
        //TODO
        if(v2 instanceof NilValue){
            return 1;
        }
        else return ((ConsValue) v2).getLength()+1;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if(other instanceof ConsValue){
            return v1.equals(((ConsValue) other).v1) && v2.equals(((ConsValue) other).v2);
        }
        return false;
    }
}
