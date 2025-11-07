package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;

// import apsd.interfaces.containers.base.MutableIterableContainer;
// import apsd.interfaces.containers.iterators.MutableForwardIterator;

/**
 * Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione.
 */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> { // Must extend Sequence and MutableIterableContainer

    // SetAt
    default void SetAt(Data dat, Natural posNat) { /* Mezzo duplicato di GetAt() */

        long size = Size().ToLong();

        if (posNat.ToLong() >= size) {
            // Forse dovremmo lanciare un eccezione
        }

        long halfSize = Size().ToLong() / 2;
        long pos = posNat.ToLong();

        Box<Long> indexBox = new Box<Long>();

        if (pos > halfSize) {
            long s = Size().ToLong() - 1;
            indexBox.Set(s);

            TraverseBackward(currentElement -> {
                if (indexBox.Get() == pos) {
                    currentElement = dat;
                    return true;
                } else {
                    indexBox.Set(indexBox.Get() - 1);
                    return false;
                }
            });
        } else {
            indexBox.Set(0L);

            TraverseForward(currentElement -> {
                if (indexBox.Get() == pos) {
                    currentElement = dat;
                    return true;
                } else {
                    indexBox.Set(indexBox.Get() + 1);
                    return false;
                }
            });
        }
    }

    // GetNSetAt

    // SetFirst
    default void SetFirst(Data dat){
        SetAt(dat, Natural.ZERO);
    }

    // GetNSetFirst

    // SetLast
    default void SetLast(Data dat){
        Natural pos = Natural.Of(Size().ToLong() - 1);
        SetAt(dat, pos);
    }

    // GetNSetLast

    // Swap

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...

}
