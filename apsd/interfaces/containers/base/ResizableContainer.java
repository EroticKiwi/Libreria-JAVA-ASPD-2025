package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/**
 * Interface: ReallocableContainer che è espandibile e riducibile.
 */
public interface ResizableContainer extends ReallocableContainer { // Must extend ReallocableContainer

    double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

    // Expand
    void Expand(Natural num);

    default void Expand() {

    }

    // Reduce
    void Reduce(Natural num);

    default void Reduce() {
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // ...

    @Override
    public abstract Natural Size();

    @Override
    default void Grow(){ /* Controlla se abbiamo oltrepassato il Threshold, se sì allora facciamo una Grow*/
        if ((long) (THRESHOLD_FACTOR * GROW_FACTOR * Size().ToLong()) >= Capacity().ToLong())
            ReallocableContainer.super.Grow();
    }

    @Override
    default void Shrink() { /* Controlla se siamo di molto al di sotto del Threshold, se sì facciamo una Shrink*/
        if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong())
            ReallocableContainer.super.Shrink();
    }

}
