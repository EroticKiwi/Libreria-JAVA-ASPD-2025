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
        Box<Boolean> removedAll = new Box<Boolean>(true);

        con.TraverseForward(currentElement -> {
            if (!Remove(currentElement)) {
                removedAll.Set(false);
            }

            return false;
        });

        return removedAll.Get();
    }

    // RemoveSome
    default boolean RemoveSome(TraversableContainer<Data> con) {

        Box<Boolean> removedAtLeastOne = new Box<Boolean>(false);

        con.TraverseForward(currentElement -> {
            if (Remove(currentElement)) {
                removedAtLeastOne.Set(true);
            }

            return false;
        });

        return removedAtLeastOne.Get();

    }
}
