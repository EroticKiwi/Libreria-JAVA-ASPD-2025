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
        long idx = ExcIfOutOfBound(pos);
        return arr[(int)((start + idx) % arr.length)]; /* pos = 0 equivarrà sempre alla start */
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    // ...
    @Override
    public void SetAt(Data dat, Natural pos) {
        long idx = ExcIfOutOfBound(pos);
        arr[(int)((start + idx) % arr.length)] = dat; /* pos = 0 equivarrà sempre alla start */
    }

    /* ************************************************************************ */
    /* Specific member functions of Vector                                      */
    /* ************************************************************************ */

    // ...
    @Override
    public void ShiftLeft(Natural pos, Natural num){
        long idx = ExcIfOutOfBound(pos);
        long size = Size().ToLong();
        long len = num.ToLong();
        len = (len <= size - idx) ? len : size - idx;
        if(idx < size - (idx + len)){ /* size - (idx + len) = numero di elementi a destra */
            /* Il numero di elementi a sinistra è minore del numero di elementi a destra */
            /* Per evitare spostamenti inutili, ci conviene spostare la TESTA */
            long iniwrt = idx - 1 + len;
            long wrt = iniwrt;
            for(long rdr = wrt - len; rdr >= 0; rdr--, wrt--){
                Natural natrdr = Natural.Of(rdr);
                SetAt(GetAt(natrdr), Natural.Of(wrt));
                SetAt(null, natrdr);
            }
            for(; iniwrt - wrt < len; wrt--){
                SetAt(null, Natural.Of(wrt));
            }
            start = (start + len) % arr.length;
        } else {
            super.ShiftLeft(pos, num);
        }
    }

    @Override
    public void ShiftRight(Natural pos, Natural num) {
        long idx = ExcIfOutOfBound(pos);
        long size = Size().ToLong();
        long len = num.ToLong();
        len = (len <= size - idx) ? len : size - idx;

        if (idx < size - (idx + len)) {
            start = (start - len + arr.length) % arr.length;
            long wrt = 0;
            long rdr = len;
            for (; wrt < idx; wrt++, rdr++) {
                Natural natrdr = Natural.Of(rdr);
                Natural natwrt = Natural.Of(wrt);
                SetAt(GetAt(natrdr), natwrt);
                SetAt(null, natrdr);
            }
            for (; wrt < idx + len; wrt++) {
                SetAt(null, Natural.Of(wrt));
            }
        } else {
            super.ShiftRight(pos, num);
        }
    }

}
