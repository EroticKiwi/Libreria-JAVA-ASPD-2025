package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;

import apsd.interfaces.containers.base.SortedIterableContainer;

/**
 * Interface: Sequence & SortedIterableContainer.
 */
public interface SortedSequence<Data extends Comparable<Data>> extends Sequence<Data>, SortedIterableContainer<Data> { // Must extend Sequence and SortedIterableContainer

    /* ************************************************************************ */
    /* Override specific member functions from MembershipContainer              */
    /* ************************************************************************ */

    // ...
    @Override
    default boolean Exists(Data dat) {
        return Search(dat) != null;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    default Natural Search(Data dat) {

        long bottom = 0;
        long top = Size().ToLong() - 1;
        long middle = (bottom + top) / 2;

        int compareResult = 0;

        Data currentDat;

        while (bottom <= top) { /* Continuiamo fino a che c'è un intervallo valido */

            currentDat = GetAt(Natural.Of(middle));
            compareResult = dat.compareTo(currentDat);

            if (compareResult == 0) { /* Abbiamo trovato l'elemento che cercavamo */
                Natural.Of(middle);
            }

            if (compareResult < 0) { /* L'elemento che cerchiamo è più piccolo */
                top = middle - 1;
            }

            if (compareResult > 0) { /* L'elemento che cerchiamo è più grande */
                bottom = middle + 1;
            }

        }

        return null;
    }

}
