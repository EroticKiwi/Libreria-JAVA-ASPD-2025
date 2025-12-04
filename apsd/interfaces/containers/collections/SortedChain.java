package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.SortedSequence;

import java.security.IllegalArgumentException;

public interface SortedChain<Data extends Comparable<Data>> extends OrderedChain<Data>, SortedSequence<Data> { // Must extend OrderedChain and SortedSequence

    // SearchPredecessor
    default Natural SearchPredecessor(Data dat) {

        if (Size().ToLong() <= 1) {
            return null;
        }

        Natural pos = Search(dat);

        if (pos == null || pos.ToLong() == 0) {
            return null;
        }

        return Natural.Of(pos.ToLong() - 1);
    }

    // SearchSuccessor
    default Natural SearchSuccessor(Data dat) {

        if (Size().ToLong() <= 1) {
            return null;
        }

        Natural pos = Search(dat);
        if (pos == null || pos.ToLong() == Size().ToLong() - 1) {
            return null;
        }

        return Natural.Of(pos.ToLong() + 1);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    default Natural Search(Data dat) { /* RICERCA BINARIA PER VETTORI */

        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if(IsEmpty()){
           return null;
        }

        Data currentData = null;

        long min = 0;
        long max = Size().ToLong() - 1;
        long center;

        int compareResult = 0;

        while (min <= max) {
            center = (min + max) / 2;

            currentData = GetAt(Natural.Of(center));
            compareResult = dat.compareTo(currentData);

            if (compareResult == 0) {
                return Natural.Of(center);
            }

            if(compareResult < 0){
                max = center - 1;
            } else {
                min = center + 1;
            }

        }

        return null;
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
    default Data Predecessor(Data dat) { /* Possiamo aggiustare le funzioni per usare la ricerca binaria (Per i vettori)*/

        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        Natural pos = SearchPredecessor(dat);
        if(pos == null){
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
        if(pos == null){
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
}
