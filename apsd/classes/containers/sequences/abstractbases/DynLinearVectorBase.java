package apsd.classes.containers.sequences.abstractbases;

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

    }

    /* ************************************************************************ */
    /* Override specific member functions from ResizableContainer               */
    /* ************************************************************************ */

    // ...

    @Override
    public void Expand(Natural num){
        Grow(num);
        size += num.ToLong();
    }

    @Override
    public void Reduce(Natural num){
        if(num.ToLong() > size){ /* Non si sa mai */
            throw new IllegalArgumentException("Cannot reduce vector size to a value greater than its current size.");
        }
        size -= num.ToLong();
        Shrink();
    }

}
