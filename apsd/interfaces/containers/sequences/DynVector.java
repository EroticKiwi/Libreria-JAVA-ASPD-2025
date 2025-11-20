package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

import java.security.InvalidParameterException;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data> { // Must extend ResizableContainer, InsertableAtSequence, RemovableAtSequence, and Vector

    /* Userà gli array, dunque operazioni At = O(1) */

    /* ************************************************************************ */
    /* Override specific member functions from InsertableAtSequence             */
    /* ************************************************************************ */

    // ...
    @Override
    default void InsertAt(Data dat, Natural pos) { /* INCOMPLETO!!! TORNARE QUI!!! */
        if (pos.ToLong() < Size().ToLong()) {
            ShiftRight(pos); /* Shiftiamo a destra di una posizione */
        } else {
            Expand();
        }
        SetAt(dat, pos); /* Settiamo il valore alla posizione liberata */
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableAtSequence              */
    /* ************************************************************************ */

    @Override
    default Data AtNRemove(Natural pos) {
        Data dat = GetAt(pos);
        ShiftLeft(pos);
        return dat;
    }

    /* ************************************************************************ */
    /* Specific member functions of Vector                                       */
    /* ************************************************************************ */

    // ...

    @Override
    default void ShiftLeft(Natural pos, Natural num) {
        Vector.super.ShiftLeft(pos, num);
        Reduce();
    }

    @Override
    default void ShiftRight(Natural pos, Natural num) {
        Expand();
        Vector.super.ShiftRight(pos, num);
    }

    // SubVector
    @Override
    default DynVector<Data> SubVector(Natural from, Natural to) { /* INCOMPLETO !!! TORNARE QUI!!! SubVector inclusivo */

        long start = from.ToLong();
        long end = to.ToLong();
        long size = Size().ToLong();

        if (start > end || end >= size) {
            throw new InvalidParameterException("Indexes are out of bounds or in the incorrect order!");
        }

        long newSize = end - start + 1; /* Perchè è inclusivo! */

        apsd.classes.containers.sequences.DynVector<Data> newVector =
                new apsd.classes.containers.sequences.DynVector<Data>(Natural.Of(newSize));

        long oldArrIndex = from.ToLong();

        for (long i = 0; i < newSize; i++) {
            newVector.InsertLast(this.GetAt(Natural.Of(oldArrIndex)));
            oldArrIndex++;
        }

        return newVector;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    @Override
    Natural Size();

}
