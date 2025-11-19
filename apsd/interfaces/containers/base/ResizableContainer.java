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
        Expand(Natural.ONE);
    }

    // Reduce
    void Reduce(Natural num);

    default void Reduce() {
        Reduce(Natural.ONE);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    @Override
    public abstract Natural Size();

    /* ************************************************************************ */
    /* Override specific member functions from ReallocableContainer             */
    /* ************************************************************************ */

    // ...

    @Override
    default void Grow(Natural num){ /* Controlla se abbiamo oltrepassato il Threshold, se sì allora facciamo una Grow*/

        long neededSpace = Size().ToLong() + num.ToLong();

        if(neededSpace <= Capacity().ToLong()){
            return;
        }

        long doubleCapacity = (long)(Capacity().ToLong() * GROW_FACTOR);

        if(doubleCapacity >= neededSpace){ /* Semplicemente moltiplicando abbiamo abbastanza spazio. */
            ReallocableContainer.super.Grow();
        } else { /* Moltiplicare per 2 non è sufficiente, dobbiamo aggiungere precisamente lo spazio richiesto */
            ReallocableContainer.super.Grow(num);
        }
    }

    @Override
    default void Shrink() { /* Controlla se siamo di molto al di sotto del Threshold, se sì facciamo una Shrink*/
        if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong())
            ReallocableContainer.super.Shrink();
    }

}
