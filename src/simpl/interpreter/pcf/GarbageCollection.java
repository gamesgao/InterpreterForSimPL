package simpl.interpreter.pcf;

import simpl.interpreter.*;

/**
 * Created by games on 2016/12/12.
 */
public class GarbageCollection {
    private Mem M;
    private Env E;
    private RefC R;


    public void markAll(){
        //TODO
        E.mark(M);
    }

    public void sweep() throws InterruptedException {
        //TODO
        RefValue r = R.dequeue();
        while(!(r instanceof RefHead)){
            if(r.mark){
                r.mark = false;
                R.enqueue(r);
            }
            else{
                M.remove(r.p);
            }
        }
    }

    public GarbageCollection(Mem m, Env e, RefC R){
        this.E = e;
        this.M = m;
        this.R = R;
        this.R.enqueue(new RefHead());
    }

    public void run() throws InterruptedException {
        this.markAll();
        this.sweep();
    }




}
