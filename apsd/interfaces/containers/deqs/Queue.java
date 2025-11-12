package apsd.interfaces.containers.deqs;

// import apsd.interfaces.containers.base.ClearableContainer;
// import apsd.interfaces.containers.base.InsertableContainer;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer, InsertableContainer<Data> { // Must extend ClearableContainer and InsertableContainer

    // Head
    Data Head();

    // Dequeue
    void Dequeue();

    // HeadNDequeue
    default Data HeadNDequeue(){
        Data dat = Head();
        Dequeue();
        return dat;
    }

    // Enqueue
    void Enqueue(Data data);

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    default void Clear(){
        while(Head() != null){
            Dequeue();
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    // ...

    @Override
    default boolean Insert(Data dat){

        if(Size().ToLong() == Integer.MAX_VALUE){ /* Non saprei, potrebbe essere inutile. */
            return false;
        }

        Enqueue(dat);
        return true;
    }

}
