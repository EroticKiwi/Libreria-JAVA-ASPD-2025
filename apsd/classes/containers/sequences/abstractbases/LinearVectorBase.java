package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/**
 * Object: Abstract (static) linear vector base implementation.
 */
abstract public class LinearVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

    // LinearVectorBase

    protected LinearVectorBase(){
        super();
    }

    public LinearVectorBase(Natural size){
        super(size);
    }

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // ...

    @Override
    public void Realloc(Natural num){
        Data[] oldArr = arr;
        ArrayAlloc(num);

        if(oldArr == null || oldArr.length == 0){
            return;
        }

        long limit = oldArr.length > num.ToLong() ? num.ToLong() : oldArr.length;

        for(int i = 0; i < limit; i++){
            arr[i] = oldArr[i];
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...

    @Override
    public Data GetAt(Natural pos) {

        if(pos.ToLong() >= Size().ToLong()){
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
