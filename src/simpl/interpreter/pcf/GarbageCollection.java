package simpl.interpreter.pcf;

import simpl.interpreter.*;

/**
 *  Created by games on 2016/12/12.
 */
public class GarbageCollection {
    private Mem M;
    private Env E;
    private RefC R;


    private void markAll(){
        //TODO
        E.mark(M);
    }

    private void sweep(){
        //TODO
        try {
            RefValue r = R.dequeue();
            while(!(r instanceof RefHead)){
                if(r.mark){
                    r.mark = false;
                    R.enqueue(r);
                }
                else{
                    M.remove(r.p);
                }
                r = R.dequeue();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public GarbageCollection(Mem m, Env e, RefC R){
        this.E = e;
        this.M = m;
        this.R = R;
        this.R.enqueue(new RefHead());
    }

    public void run(){
        this.markAll();
        this.sweep();
    }




}
