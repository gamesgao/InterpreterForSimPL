package simpl.interpreter.pcf;

import simpl.interpreter.Env;
import simpl.interpreter.Mem;
import simpl.interpreter.RefValue;
import simpl.interpreter.Value;

/**
 * Created by games on 2016/12/12.
 */
public class GarbageCollection {
    private Mem M;
    private Env E;


    public void markAll(){
        //TODO
        Env nowE = E;
        Value v = E.get();
        if(v instanceof RefValue){

        }

    }

    public void sweep(){
        //TODO
    }

    public GarbageCollection(Mem m, Env e){
        this.E = e;
        this.M = m;
    }

    public void run(){
        this.markAll();
        this.sweep();
    }




}
