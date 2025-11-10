package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;
import apsd.interfaces.traits.Predicate;

public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data> { // Must extend ReallocableContainer and MutableSequence

    default void ShiftLeft(Natural pos, Natural num) {
        long idx = ExcIfOutOfBound(pos);
        long size = Size().ToLong();
        long len = num.ToLong();
        len = (len <= size - idx) ? len : size - idx;
        if (len > 0) {
            long iniwrt = idx;
            long wrt = iniwrt;
            for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
                Natural natrdr = Natural.Of(rdr);
                SetAt(GetAt(natrdr), Natural.Of(wrt));
                SetAt(null, natrdr);
            }
            for (; wrt - iniwrt < len; wrt++) {
                /* Si occupa di mettere a null gli elementi nei casi limite in cui len è vicino a size. */
                SetAt(null, Natural.Of(wrt));
            }
        }
    }

    // ShiftLeft
    default void ShiftLeft(Natural num) {
        ShiftLeft(Natural.ZERO, num); // Shiftiamo di num posizioni a partire dalla posizione 0
    }

    // ShiftFirstLeft
    default void ShiftFirstLeft() {
        ShiftLeft(Natural.ONE); // Shiftiamo a sinistra di 1 in posizione 0
    }

    // ShiftLastLeft
    default void ShiftLastLeft() {
        //long num = Size().ToLong() - 1;
        ShiftLeft(Natural.Of(Size().ToLong() - 1), Natural.ONE); // Shiftiamo a sinistra di 1 a partire dall'ultima posizione
    }

    // ShiftRight
    default void ShiftRight(Natural pos, Natural num) {
        /* HA BISOGNO DI TESTING! */

        long idx = ExcIfOutOfBound(pos);
        long size = Size().ToLong();
        long len = num.ToLong();
        len = (len <= size - idx) ? len : size - idx;
        if (len > 0) {
            long wrt = size - 1;
            for (long rdr = wrt - len; rdr >= idx; rdr--, wrt--) {
                Natural natrdr = Natural.Of(rdr);
                SetAt(GetAt(natrdr), Natural.Of(wrt));
                SetAt(null, natrdr);
            }
            for (; idx <= wrt; idx++) {
                SetAt(null, Natural.Of(idx));
            }
        }
    }

    default void ShiftRight(Natural num) {
        ShiftRight(Natural.ZERO, num);
    }

    // ShiftFirstRight
    default void ShiftFirstRight() {
        ShiftRight(Natural.ONE);
    }

    // ShiftLastRight
    default void ShiftLastRight() {
        ShiftRight(Natural.Of(Size().ToLong() - 1), Natural.ONE);
    }

    // SubVector
    default Vector<Data> SubVector(Natural from, Natural to) {
        return (Vector<Data>) SubSequence(from, to); /* Al 99% è sbagliato */
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...
    default Natural Size(){

    }

}
