package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.SortedSequence;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

public interface SortedChain<Data extends Comparable<? super Data>> extends OrderedChain<Data>, SortedSequence<Data> { // Must extend OrderedChain and SortedSequence

    // SearchPredecessor
    default Natural SearchPredecessor(Data dat) {

        if (Size().ToLong() <= 1) {
            return null;
        }

        MutableNatural datPos = new MutableNatural(); /* Dove si è fermato l'iteratore nella SearchList */

        Natural foundPos = SearchList(dat, currentElement ->{
            datPos.Increment();
            return false;
        });

        if(foundPos != null){
            long idx = foundPos.ToLong();
            if(idx == 0) return null;
            return Natural.Of(idx - 1);
        }

        if(datPos.ToLong() == 0){
            return null; /* Non esiste predecessore */
        }

        return Natural.Of(datPos.ToLong() - 1);
    }

    // SearchSuccessor
    default Natural SearchSuccessor(Data dat) {

        if (Size().ToLong() <= 1) {
            return null;
        }

        MutableNatural datPos = new MutableNatural(); /* Dove si è fermato l'iteratore nella SearchList */

        Natural foundPos = SearchList(dat, currentElement ->{
            datPos.Increment();
            return false;
        });

        if(foundPos != null){
           long nextIdx = foundPos.ToLong() + 1;
           if(nextIdx == Size().ToLong()) return null;
           return Natural.Of(nextIdx);
        }

        if(datPos.ToLong() == Size().ToLong()){
            return null;
        }

        return Natural.Of(datPos.ToLong());
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    default Natural Search(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("Data parameter can't be null!");
        }

        /* CounterFunc null poichè tanto ci ritorna la pos dell'elemento */
        return SearchList(dat, null);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Set                              */
    /* ************************************************************************ */

    default void Intersection(SortedChain<Data> chn) {
        Natural i = Natural.ZERO, j = Natural.ZERO;
        while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
            int cmp = GetAt(i).compareTo(chn.GetAt(j));
            if (cmp < 0) {
                RemoveAt(i);
            } else {
                j = j.Increment();
                if (cmp == 0) {
                    i = i.Increment();
                }
            }
        }
        while (i.compareTo(Size()) < 0) {
            RemoveAt(i);
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from OrderedSet                       */
    /* ************************************************************************ */

    // ...
    @Override
    default Data Min() {
        return FIterator().GetCurrent();
    }

    @Override
    default void RemoveMin() {
        RemoveAt(Natural.ZERO);
    }

    @Override
    default Data MinNRemove() {
        Data min = Min();
        RemoveMin();
        return min;
    }

    @Override
    default Data Max() {
        return BIterator().GetCurrent();
    }

    @Override
    default void RemoveMax() {
        RemoveAt(Size().Decrement());
    }

    @Override
    default Data MaxNRemove() {
        Data max = Max();
        RemoveMax();
        return max;
    }

    @Override
    default Data Predecessor(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        Natural pos = SearchPredecessor(dat);
        if (pos == null) {
            return null;
        } else {
            return GetAt(pos);
        }
    }

    @Override
    default Data Successor(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        Natural pos = SearchSuccessor(dat);
        if (pos == null) {
            return null;
        } else {
            return GetAt(pos);
        }
    }

    @Override
    default void RemovePredecessor(Data dat) {
        Natural predPos = SearchPredecessor(dat);
        if (predPos != null) {
            RemoveAt(predPos);
        }
    }

    @Override
    default void RemoveSuccessor(Data dat) {
        Natural succPos = SearchSuccessor(dat);
        if (succPos != null) {
            RemoveAt(succPos);
        }
    }

    @Override
    default Data PredecessorNRemove(Data data) {
        Data pred = Predecessor(data);

        if (pred != null) {
            Remove(pred);
        }

        return pred;
    }

    @Override
    default Data SuccessorNRemove(Data data) {
        Data successor = Successor(data);

        if (successor != null) {
            Remove(successor);
        }

        return successor;
    }

    /* Metodo di Utility */
    private Natural SearchList(Data datToFind, Predicate<Data> counterFunc){

        /* Indipendentemente da se l'elemento è presente o meno nella chain, sappiamo che l'iteratore si fermerà */
        /* Nella posizione in cui ci saremmo aspettato l'elemento. Dunque, se per esempio passiamo pos a currentFunc */
        /* Allora ci interessa pos-1 (predecessore) o pos+1 (successore). */
        /* Se l'elemento di cui ci interessa il pred o succ non è presente nella lista allora otteniamo null. */
        /* Se ci interessa solo l'elemento da cercare, non passiamo alcuna funzione contatore, e lasciamo fare alla Search. */

        if (datToFind == null) {
            throw new IllegalArgumentException("Data parameter can't be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        ForwardIterator<Data> iterator = FIterator();
        MutableNatural index = new MutableNatural(0);
        int compareResult = 0;

        while (iterator.IsValid()) {

            compareResult = datToFind.compareTo(iterator.GetCurrent());

            if(compareResult == 0) {
                return index.ToNatural();
            }

            if(compareResult < 0){
                break;
            }

            iterator.Next();
            index.Increment();

            if(counterFunc != null){
                /* La usiamo, se ci serve, per tenere traccia della posizione quando cerchiamo pred e succ. */
                /* Se cerchiamo pred o succ non è detto che l'elemento passato sia parte della chain. */
                /* Se cerchiamo solo l'elemento allora passiamo null, dato che la funzione ritorna già quello. */
                counterFunc.Apply(datToFind);
            }
        }

        return null;
    }

}
