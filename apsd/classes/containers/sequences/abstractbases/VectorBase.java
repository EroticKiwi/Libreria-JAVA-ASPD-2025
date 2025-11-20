package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Vector;

import java.security.InvalidParameterException;

/**
 * Object: Abstract vector base implementation.
 */
abstract public class VectorBase<Data> implements Vector<Data> { // Must implement Vector

    protected Data[] arr;

    protected VectorBase() { /* Chiamato dalle classi figlie */
        ArrayAlloc(Natural.ZERO);
    }

    // VectorBase
    public VectorBase(Natural size) { /* Chiamato da chi vuole creare un vettore */
        ArrayAlloc(size);
    }

    // NewVector
    protected abstract void NewVector(Data[] arr);

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...

    @Override
    public void Clear() {
        ArrayAlloc(Natural.ZERO);
    }

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer               */
    /* ************************************************************************ */

    // ...

    @SuppressWarnings("unchecked")
    protected void ArrayAlloc(Natural newsize) {
        long size = newsize.ToLong();
        if (size >= Integer.MAX_VALUE) {
            throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!");
        }
        arr = (Data[]) new Object[(int) size];
    }

    @Override
    public Natural Capacity() {
        return Natural.Of(arr.length);
    }

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...

    @Override
    public abstract Data GetAt(Natural pos);

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    // ...

    @Override
    public abstract void SetAt(Data dat, Natural pos);

    @Override
    public MutableSequence<Data> SubSequence(Natural from, Natural to) {
        return this.SubVector(from, to);
    }

}
