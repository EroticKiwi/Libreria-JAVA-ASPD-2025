package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/**
 * Interface: Container con supporto alla rimozione di un dato.
 */
public interface RemovableContainer<Data> { // Must extend Container

    // Remove
    boolean Remove(Data dat);

    // RemoveAll
    default boolean RemoveAll(TraversableContainer<Data> con) {

    }

    // RemoveSome
    default boolean RemoveSome(TraversableContainer<Data> con) {

    }
}
