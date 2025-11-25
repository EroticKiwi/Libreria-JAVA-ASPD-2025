package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/**
 * Object: Abstract dynamic circular vector base implementation.
 */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data> { // Must extend CircularVectorBase and implement DynVector

    protected long size = 0L;

    // DynCircularVectorBase

    public DynCircularVectorBase() {
        super();
    }

    public DynCircularVectorBase(Natural size) {
        super(size);
    }

    public DynCircularVectorBase(TraversableContainer<Data> con) {
        super(con);
        size = con.Size().ToLong();
    }

    protected DynCircularVectorBase(Data[] arr) {
        super(arr);
        size = arr.length;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...
    @Override
    public Natural Size(){
        return Natural.Of(size);
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    public void Clear() {
        super.Clear();
        size = 0L;
    }

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // ...
    @Override
    public void Realloc(Natural num) {
        long tempSize = size;

        super.Realloc(num);

        size = tempSize;

        if (size > num.ToLong()) {
            size = num.ToLong();
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from ResizableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    public void Expand(Natural num) {
        Grow(num);
        size += num.ToLong();
    }

    @Override
    public void Reduce(Natural num) {
        if (num.ToLong() > size) { /* Non si sa mai */
            throw new IllegalArgumentException("Cannot reduce vector size to a value greater than its current size.");
        }
        size -= num.ToLong();
        Shrink();
    }

    /* ************************************************************************ */
    /* Specific member functions of Vector                                      */
    /* ************************************************************************ */

    // ...

    @Override
    protected void ArrayAlloc(Natural num) {
        super.ArrayAlloc(num);
        size = 0L;
    }

}
