package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<Data>> extends OrderedChain<Data>, SortedSequence<Data> { // Must extend OrderedChain and SortedSequence

    // SearchPredecessor
    default Natural SearchPredecessor(Data dat) {
        Natural pos = Search(dat);

        if (pos == null || pos.ToLong() == 0) {
            return null; /* Poi ci torniamo */
        }

        return Natural.Of(pos.ToLong() - 1);
    }

    // SearchSuccessor
    default Natural SearchSuccessor(Data dat) {
        Natural pos = Search(dat);
        if (pos == null) {
            return null; /* Poi ci torniamo */
        }

        return Natural.Of(pos.ToLong() + 1);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    default Natural Search(Data dat) { /* O(n) */

        /* Questo è ottimizzato per le Chain, che usando le liste dovranno scorrere tutti gli elementi per forza.
         Niente GetAt() siccome su liste è un operazione da O(n). Dunque usando una GetAt() avremmo avuto O(n * n). */
        ForwardIterator<Data> iterator = FIterator();

        Natural pos = Natural.ZERO;
        Data currentData;

        int compareResult = 0;

        while (iterator.IsValid()) {

            currentData = iterator.GetCurrent();
            compareResult = dat.compareTo(currentData);

            if (compareResult == 0) { /* Abbiamo trovato l'elemento che cercavamo */
                return pos;
            }

            if (compareResult < 0) { /* Se dat è più piccolo, significa che non è presente nella Chain. */
                return null;
            }

            iterator.Next();
            pos = pos.Increment();
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
    default Data Predecessor(Data dat) {

        ForwardIterator<Data> iterator = FIterator();
        Data currentData = null;

        while (iterator.IsValid()) {

            if (iterator.GetCurrent().compareTo(dat) >= 0) {
                break;
            }

            currentData = iterator.GetCurrent();
            iterator.Next();
        }

        return currentData;
    }

    @Override
    default Data Successor(Data dat) {

        BackwardIterator<Data> iterator = BIterator();
        Data currentData = null;

        while (iterator.IsValid()) {

            if (iterator.GetCurrent().compareTo(dat) <= 0) {
                break;
            }

            currentData = iterator.GetCurrent();
            iterator.Prev();
        }

        return currentData;
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
