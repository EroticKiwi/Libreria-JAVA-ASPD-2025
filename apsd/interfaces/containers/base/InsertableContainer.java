package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/**
 * Interface: Container con supporto all'inserimento di un dato.
 */
public interface InsertableContainer<Data> extends Container { // Must extend Container

    // Insert
    public boolean Insert(Data dat);

    // InsertAll
    default boolean InsertAll(TraversableContainer<Data> con) { /* Inserisce tutti, si ferma se uno non viene inserito e ritorna false */
        boolean finalState = con.TraverseForward(currentElement -> {
            return !Insert(currentElement);
        });

        return !finalState;
    }

    // InsertSome
    default boolean InsertSome(TraversableContainer<Data> con) { /* Se inserisce almeno uno ritorna true, altrimenti false */

        Box<Boolean> finalState = new Box<>(false);

        con.TraverseForward(currentElement -> {

            if(Insert(currentElement)){
                finalState.Set(true);
            }

            return false;
        });

        return finalState.Get();
    }
}
