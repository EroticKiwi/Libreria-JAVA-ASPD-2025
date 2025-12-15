package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data> { // Must extend ResizableContainer, InsertableAtSequence, RemovableAtSequence, and Vector

    /* Userà gli array, dunque operazioni At = O(1) */

    /* ************************************************************************ */
    /* Override specific member functions from InsertableAtSequence             */
    /* ************************************************************************ */

    // ...
    @Override
    default void InsertAt(Data dat, Natural pos) {

        if(pos.ToLong() > Size().ToLong()){ /* Non possiamo inserire ad una posizione maggiore della Size() */
            throw new IndexOutOfBoundsException("InsertAt position out of bounds");
        }

        ShiftRight(pos, Natural.ONE); /* Sposta elemento a destra di una posizione (Non avviene nessuna Shift al primo insert) */
        SetAt(dat, pos); /* Setta alla posizione precedentemente occupata dall'elemento ora spostato */
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableAtSequence              */
    /* ************************************************************************ */

    @Override
    default Data AtNRemove(Natural pos) {

        if(IsEmpty()){
            return null;
        }

        Data dat = GetAt(pos);
        ShiftLeft(pos, Natural.ONE);
        return dat;
    }

    /* ************************************************************************ */
    /* Specific member functions of Vector                                       */
    /* ************************************************************************ */

    // ...

    @Override
    default void ShiftLeft(Natural pos, Natural num) {
        Vector.super.ShiftLeft(pos, num);
        Reduce(num);
    }

    @Override
    default void ShiftRight(Natural pos, Natural num) {
        Expand(num);
        Vector.super.ShiftRight(pos, num);
    }

    // SubVector
    @Override
    default DynVector<Data> SubVector(Natural from, Natural to) { /* INCOMPLETO !!! TORNARE QUI!!! SubVector inclusivo */
        return (DynVector<Data>) SubSequence(from, to);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    @Override
    Natural Size();

}
