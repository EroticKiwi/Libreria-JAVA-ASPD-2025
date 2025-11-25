package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/**
 * Object: Abstract (static) circular vector base implementation.
 */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

    protected long start = 0L; /* Il punto di ingresso dell'array circolare */

    // CircularVectorBase

    public CircularVectorBase() {
        super();
    }

    public CircularVectorBase(Natural size) {
        super(size);
    }

    public CircularVectorBase(TraversableContainer<Data> con) {
        super(con);
        start = 0L;
    }

    public CircularVectorBase(Data[] arr) {
        this.arr = arr;
        start = 0L;
    }

    @Override
    protected void ArrayAlloc(Natural newsize) {
        super.ArrayAlloc(newsize);
        start = 0L;
    }

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // ...
    @Override
    public void Realloc(Natural num) { /* Va aggiustata la modifica di start */
        Data[] oldArr = arr;

        long oldStart = start;
        ArrayAlloc(num);

        if(oldArr == null || oldArr.length == 0){
            return;
        }
        
        long limit = oldArr.length > num.ToLong() ? num.ToLong() : oldArr.length;

        for(int i = 0; i < limit; i++){
            arr[i] = oldArr[(int)(oldStart + i) % oldArr.length];
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    public Data GetAt(Natural pos) {
        return arr[(int)(start + pos.ToLong()) % arr.length];
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    // ...
    @Override
    public void SetAt(Data dat, Natural pos) {
        arr[(int)(start + pos.ToLong()) % arr.length] = dat;
    }

    /* ************************************************************************ */
    /* Specific member functions of Vector                                      */
    /* ************************************************************************ */

    // ...
    @Override
    public void ShiftRight(Natural pos, Natural num){ /* Sbagliato */
        long safePos = ExcIfOutOfBound(pos);
        long size = Size().ToLong();
        long idx = (start + safePos) % size;
        long len = num.ToLong();
        if(len > 0){
            long wrt = (idx + len) % size;
            long rdr = idx;
            for(long i = 0; i < len; i++){

                arr[(int)wrt] = arr[(int)rdr];
                arr[(int)rdr] = null;

                rdr = (rdr + 1) % size;
                wrt = (wrt + 1) % size;
            }
        }
    }

}
