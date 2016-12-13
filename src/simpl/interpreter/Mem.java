package simpl.interpreter;

import java.util.HashMap;

public class Mem extends HashMap<Integer, Value> {

    private static final long serialVersionUID = -1155291135560730618L;

    private final static int MAX_RAM = 5;

    public boolean overFlow(){
        return this.size() >= MAX_RAM;
    }
}
