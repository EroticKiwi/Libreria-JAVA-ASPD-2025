package apsd.interfaces.containers.sequences;

// import apsd.classes.utilities.Natural;

import apsd.classes.utilities.Natural;

/**
 * Interface: Sequence con supporto alla rimozione di un dato tramite posizione.
 */
public interface RemovableAtSequence<Data> extends Sequence<Data> { // Must extend Sequence

    // AtNRemove
    Data AtNRemove(Natural pos);

    // RemoveAt
    default void RemoveAt(Natural pos) {
        AtNRemove(pos);
    }

    // RemoveFirst
    default void RemoveFirst() {
        AtNRemove(Natural.ZERO);
    }

    // FirstNRemove
    default Data FirstNRemove() {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }
        return AtNRemove(Natural.ZERO);
    }

    // RemoveLast
    default void RemoveLast() {

        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        AtNRemove(Natural.Of(Size().ToLong() - 1));
    }

    // LastNRemove
    default Data LastNRemove() {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        return AtNRemove(Natural.Of(Size().ToLong() - 1));
    }

}
