package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Reallocable;

/**
 * Interface: ClearableContainer che è anche Reallocable.
 */
// QUI SIZE E CAPACITY SONO ANCORA LA STESSA COSA!
public interface ReallocableContainer extends ClearableContainer, Reallocable { // Must extend ClearableContainer, Reallocable

    double GROW_FACTOR = 2.0; // Must be strictly greater than 1.
    double SHRINK_FACTOR = 2.0; // Must be strictly greater than 1.

    // Capacity
    Natural Capacity();

    // Grow
    default void Grow() {

        long currentCapacity = Capacity().ToLong();

        if (currentCapacity == 0) {
            Realloc(Natural.ONE);
            return;
        }

        long newCapacity = (long) Math.ceil(currentCapacity * GROW_FACTOR);

        Realloc(Natural.Of(newCapacity));
    }

    default void Grow(Natural capacityToAdd) { // Domanda al professore se a Grow passiamo il nuovo size oppure se gli passiamo di questo deve crescere

        long currentCapacity = Capacity().ToLong();

        if(currentCapacity == Long.MAX_VALUE){
            return;
        }

        long addCapacity = capacityToAdd.ToLong();

        currentCapacity += addCapacity;

        Realloc(Natural.Of(currentCapacity));
    }

    // Shrink
    default void Shrink() {

        long currentCapacity = Capacity().ToLong();

        if (currentCapacity == 0) {
            return;
        }

        long newCapacity = (long) Math.floor(currentCapacity / SHRINK_FACTOR);

        Realloc(Natural.Of(newCapacity));
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */
    @Override
    default Natural Size() {
        return Capacity();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */
    @Override
    default void Clear() {
        Realloc(Natural.ZERO);
    }
}
