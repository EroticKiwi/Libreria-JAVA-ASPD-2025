package apsd.interfaces.containers.collections;

// import apsd.interfaces.containers.base.IterableContainer;

import apsd.interfaces.containers.base.IterableContainer;

public interface Set<Data> extends Collection<Data> { // Must extend Collection

    // Union
    default void Union(Set<Data> otherSet) {

        if (otherSet == null) {
            throw new IllegalArgumentException("Set parameter can't be null!");
        }

        otherSet.TraverseForward(currentElement -> {
            Insert(currentElement);
            return false;
        });

    }

    // Difference
    default void Difference(Set<Data> otherSet) {

        if (otherSet == null) {
            throw new IllegalArgumentException("Set parameter can't be null!");
        }

        if (otherSet.IsEmpty()) {
            return;
        }

        Filter(currentElement -> {
            return !otherSet.Exists(currentElement);
        });
    }

    // Intersection
    default void Intersection(Set<Data> otherSet) {

        if (otherSet == null) {
            throw new IllegalArgumentException("Set parameter can't be null!");
        }

        if (otherSet.IsEmpty()) {
            return;
        }

        Filter(currentElement -> {
            return otherSet.Exists(currentElement);
        });
    }

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    // ...
    @Override
    default boolean IsEqual(IterableContainer<Data> otherContainer){

        if(otherContainer == null || !Size().equals(otherContainer.Size())){
            return false;
        }

        if(this == otherContainer){
            return true;
        }



    }

}
