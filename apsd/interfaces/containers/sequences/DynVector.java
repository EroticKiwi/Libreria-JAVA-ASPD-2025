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
        ShiftRight(pos); /* Shiftiamo a destra di una posizione */
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
    default void ShiftLeft(Natural pos, Natural num){
        Vector.super.ShiftLeft(pos, num);
        Reduce();
    }

    @Override
    default void ShiftRight(Natural pos, Natural num){
        Expand();
        Vector.super.ShiftRight(pos, num);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    @Override
    Natural Size();

}
