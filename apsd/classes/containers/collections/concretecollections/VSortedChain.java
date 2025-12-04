package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.sequences.DynVector;

/**
 * Object: Concrete set implementation via (dynamic circular) vector.
 */
public class VSortedChain<Data extends Comparable<Data>> extends VChainBase<Data> implements SortedChain<Data> { // Must extend VChainBase and implements SortedChain

    public VSortedChain() {
        super();
    }

    public VSortedChain(VSortedChain<Data> chn) {
        super(chn); /* Copia i dati */
        super(chn.vec); /* Trasferisce il vettore */
        /* Quale usare? */
    }

    public VSortedChain(TraversableContainer<Data> con) {
        super(con);
    }

    protected VSortedChain(DynVector<Data> vec) {
        super(vec);
    }

    // NewChain
    @Override
    protected VSortedChain<Data> NewChain(DynVector<Data> vec) {
        return new VSortedChain<>(vec);
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    // ...
    @Override
    public boolean Insert(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("Can't insert null data!");
        }

        if (Size().ToLong() == Integer.MAX_VALUE) {
            return false;
        }

        // 1) Ricerca binaria per capire dove inserire l'elemento
        // 2) vec.InsertAt();

        long bottom = 0;
        long top = Size().ToLong() - 1;
        long middle = 0;

        int compareResult = 0;

        Data currentDat;

        while (bottom <= top) {

            middle = (bottom + top) / 2;
            currentDat = GetAt(Natural.Of(middle));
            compareResult = dat.compareTo(currentDat);

            if (compareResult == 0) {
                vec.InsertAt(dat, Natural.Of(middle));
                return true;
            }

            if (compareResult < 0) { /* L'elemento che cerchiamo è più piccolo */
                top = middle - 1;
            }

            if (compareResult > 0) { /* L'elemento che cerchiamo è più grande */
                bottom = middle + 1;
            }

        }

        vec.InsertAt(dat, Natural.Of(bottom));
        return true;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Chain                            */
    /* ************************************************************************ */

    // ...
    @Override
    public boolean InsertIfAbsent(Data dat) {
        // 1) Ricerca binaria per capire se l'elemento è già presente
        // 2) Se non è presente, vec.InsertAt();
        /* Essendo la lista sorted, non ho bisogno di leggerla tutta per capire se l'elemento esiste già.*/
        /* Se arrivato alla posizione di inserimento noto che non esiste ne prima ne dopo, allora semplicemente */
        /* Vorrà dire che non esiste e che va inserito */

        if (dat == null) {
            throw new IllegalArgumentException("Can't insert null data!");
        }

        if (Size().ToLong() == Integer.MAX_VALUE) {
            return false;
        }

        long bottom = 0;
        long top = Size().ToLong() - 1;
        long middle = 0;

        int compareResult = 0;

        Data currentDat;

        while (bottom <= top) {

            middle = (bottom + top) / 2;
            currentDat = GetAt(Natural.Of(middle));
            compareResult = dat.compareTo(currentDat);

            if (compareResult == 0) {
                return false;
            }

            if (compareResult < 0) { /* L'elemento che cerchiamo è più piccolo */
                top = middle - 1;
            }

            if (compareResult > 0) { /* L'elemento che cerchiamo è più grande */
                bottom = middle + 1;
            }

        }

        vec.InsertAt(dat, Natural.Of(bottom));
        return true;
    }

    @Override
    public void RemoveOccurrences(Data dat) {
        // 1) Ricerca binaria per trovare la prima occorrenza
        // 2) Rimuovi tutte le occorrenze consecutive

        if (dat == null) {
            throw new IllegalArgumentException("Can't insert null data!");
        }

        if (Size().ToLong() == Integer.MAX_VALUE) {
            return;
        }

        long bottom = 0;
        long top = Size().ToLong() - 1;
        long middle = 0;

        int compareResult = 0;

        Data currentDat;

        while (bottom <= top) {

            middle = (bottom + top) / 2;
            currentDat = GetAt(Natural.Of(middle));
            compareResult = dat.compareTo(currentDat);

            if (compareResult == 0) {
                vec.RemoveAt(Natural.Of(middle));
            }

            if (compareResult < 0) { /* L'elemento che cerchiamo è più piccolo */
                top = middle - 1;
            }

            if (compareResult > 0) { /* L'elemento che cerchiamo è più grande */
                bottom = middle + 1;
            }

        }
    }

}
