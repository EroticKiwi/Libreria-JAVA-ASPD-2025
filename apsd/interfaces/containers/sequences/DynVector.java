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
    default void InsertAt(Data dat, Natural pos) { /* Shift a destra a pos, espansione array e inserimento a posizione liberata*/
        ShiftRight(pos);
        SetAt(dat, pos);
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableAtSequence              */
    /* ************************************************************************ */

    @Override
    default Data AtNRemove(Natural pos) {
        Data dat = GetAt(pos);
        if (dat != null) {
            RemoveAt(pos);
        }
        return dat;
    }

    /* ************************************************************************ */
    /* Specific member functions of Vector                                       */
    /* ************************************************************************ */

    // ...

    @Override
    default void ShiftLeft(Natural pos, Natural num) {

    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    /* L'implementazione precedente di Size() non va bene per DynVector.
    Chi implementa DynVector dovrà farsi una sua Size(). */
    @Override
    Natural Size();

}
