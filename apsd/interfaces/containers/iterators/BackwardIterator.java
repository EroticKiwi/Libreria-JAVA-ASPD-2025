package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/**
 * Interface: Iteratore all'indietro.
 */
public interface BackwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

    // Prev
    void Prev();

    // DataNPrev
    Data DataNPrev();

    // ForEachBackward
    boolean ForEachBackward(Predicate<Data> fun){
        if(fun != null){
            while(IsValid()){
                if(fun.Apply(DataNPrev())){
                    return true;
                }
            }
        }
        return false;
    }

}
