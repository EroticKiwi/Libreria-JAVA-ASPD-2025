package apsd.interfaces.containers.collections;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.RemovableAtSequence;
import apsd.interfaces.containers.sequences.Sequence;

public interface Chain<Data> extends Set<Data>, RemovableAtSequence<Data> { // Must extend RemovableAtSequence

    // InsertIfAbsent
    default boolean InsertIfAbsent(Data dat) {

        if (!Exists(dat)) {
            return Insert(dat);
        }

        return false;
    }

    // RemoveOccurrences
    default void RemoveOccurrences(Data dat) {

        if (IsEmpty()) {
            return;
        }

        if (dat == null) {
            throw new IllegalArgumentException("Data parameter can't be null!");
        }

        Filter(currentElement -> {

            if (currentElement.equals(dat)) {
                return false;
            }

            return true;
        });
    }

    // SubChain
    default Chain<Data> SubChain(Natural from, Natural to) {
        return (Chain<Data>) SubSequence(from, to);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    default Natural Search(Data dat) {

        ForwardIterator<Data> iterator = FIterator();
        MutableNatural index = new MutableNatural(0);

        while (iterator.IsValid() && iterator.GetCurrent() != dat) {
            iterator.Next();
            index.Increment();
        }

        return index.ToNatural();
    }

}
