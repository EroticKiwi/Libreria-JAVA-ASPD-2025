package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/**
 * Object: Abstract (static) linear vector base implementation.
 */
abstract public class LinearVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

    // LinearVectorBase

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // ...

    @Override
    public void Realloc(Natural num){ /* Potremmo dover cambiare il modificatore d'accesso a protected o private */

        long newSize = num.ToLong() * (long) GROW_FACTOR;

        ArrayAlloc(Natural.Of(newSize));
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...

    @Override
    public Data GetAt(Natural pos) {

        if(pos.ToLong() < 0 || pos.ToLong() >= Size().ToLong()){
            return null; /* O lanciare eccezione */
        }

        int index = (int) pos.ToLong();

        return arr[index];
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    // ...

    @Override
    public void SetAt(Data dat, Natural pos) {

        if(pos.ToLong() < 0 || pos.ToLong() >= Size().ToLong()){
            return; /* O lanciare eccezione */
        }

        int index = (int) pos.ToLong();

        arr[index] = dat;
    }

}
