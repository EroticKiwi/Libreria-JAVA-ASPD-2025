package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends Set<Data>, RemovableAtSequence<Data> { // Must extend RemovableAtSequence

  // InsertIfAbsent
    default boolean InsertIfAbsent(Data dat){

        if(!Exists(dat)){
            return Insert(dat);
        }

        return false;
    }

  // RemoveOccurrences
    default void RemoveOccurrences(Data dat){

        if(dat == null){
            throw new IllegalArgumentException("Data parameter can't be null!");
        }

        Filter(currentElement -> {

            if(currentElement == dat){
                return false;
            }

            return true;
        });

        // Reduce();
    }

  // SubChain

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  // ...
    /*@Override
    default Natural Search(Data dat){

    }*/

}
