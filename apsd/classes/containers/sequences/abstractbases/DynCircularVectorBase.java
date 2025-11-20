package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

import apsd.interfaces.containers.sequences.DynVector;

/**
 * Object: Abstract dynamic circular vector base implementation.
 */
abstract public class DynCircularVectorBase<Data> extends CircularVectorBase<Data> implements DynVector<Data> { // Must extend CircularVectorBase and implement DynVector

    protected long size = 0L;

    // DynCircularVectorBase

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // ...

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

}
