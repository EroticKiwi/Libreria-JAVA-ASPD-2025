package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/**
 * Object: Abstract dynamic linear vector base implementation.
 */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> { // Must extend LinearVectorBase and implement DynVector

    protected long size = 0L;

    // DynLinearVectorBase

    public DynLinearVectorBase() {
        super();
    }

    public DynLinearVectorBase(Natural size) {
        super(size);
        this.size = size.ToLong();
    }

    public DynLinearVectorBase(TraversableContainer<Data> con) {
        super(con.Size());

        MutableNatural index = new MutableNatural(0L);

        con.TraverseForward(currentElement -> {
            SetAt(currentElement, index.ToNatural());
            index.Increment();
            return false;
        });

        size = con.Size().ToLong();
    }

    public DynLinearVectorBase(Data[] arr){
        super(arr);
        size = arr.length;
    }

    @Override
    protected void ArrayAlloc(Natural newsize) {
        super.ArrayAlloc(newsize);
        size = 0L;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    @Override
    public Natural Size() {
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

}
