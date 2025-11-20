package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data> { // Must extend MutableSequence, InsertableAtSequence, and Chain

    // SubList

    /* ************************************************************************ */
    /* Override specific member functions from ExtensibleContainer              */
    /* ************************************************************************ */

    @Override
    /* Poi ci torniamo, non sono convinto del try catch. Vediamo come implementiamo Insert dopo. */
    default boolean Insert(Data dat){

        try{
            InsertLast(dat);
            return true;
        } catch (Exception e){
            return false;
        }

    }

}
