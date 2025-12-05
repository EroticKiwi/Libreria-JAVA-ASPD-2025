package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/**
 * Interface: TraversableContainer con supporto all'iterazione.
 */
public interface IterableContainer<Data> extends TraversableContainer<Data> { // Must extend TraversableContainer

    // FIterator

    ForwardIterator<Data> FIterator(); /* Creerà (o prenderà) un Iteratore e gli assegnerà la struttura su cui lavorare. */

    // BIterator

    BackwardIterator<Data> BIterator();

    // IsEqual
    default boolean IsEqual(IterableContainer<Data> otherContainer) {

        if (otherContainer == null || !Size().equals(otherContainer.Size())) {
            return false;
        }

        if (this == otherContainer) {
            return true;
        }

        ForwardIterator<Data> iterator = FIterator();

        /* Le size sono uguali */

        while (iterator.IsValid()) {

            if (!otherContainer.Exists(iterator.GetCurrent())) {
                return false;
            }

            iterator.Next();
        }

        return true;
    }

    /* ************************************************************************ */
    /* Override specific member functions from TraversableContainer             */
    /* ************************************************************************ */

    @Override
    default boolean TraverseForward(Predicate<Data> predicate) { /* If pred == false, scorre*/
        return FIterator().ForEachForward(predicate);
    }

    @Override
    default boolean TraverseBackward(Predicate<Data> predicate) {
        return BIterator().ForEachBackward(predicate);
    }
}
