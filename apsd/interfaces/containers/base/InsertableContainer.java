package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/**
 * Interface: Container con supporto all'inserimento di un dato.
 */
public interface InsertableContainer<Data> extends Container { // Must extend Container

    // Insert
    public boolean Insert(Data dat);

    // InsertAll
    default boolean InsertAll(TraversableContainer<Data> con) {

        Box<Boolean> insertedAll =  new Box<Boolean>(true);

        con.TraverseForward(currentElement -> {
            if(!Insert(currentElement)){
                insertedAll.Set(false);
            }

            return false;
        });

        return insertedAll.Get();
    }

    // InsertSome
    default boolean InsertSome(TraversableContainer<Data> con) {

        Box<Boolean> insertedAtLeastOne =  new Box<Boolean>(false);

        con.TraverseForward(currentElement -> {
            if(Insert(currentElement)){
                insertedAtLeastOne.Set(true);
            }

            return false;
        });

        return insertedAtLeastOne.Get();

    }
}
