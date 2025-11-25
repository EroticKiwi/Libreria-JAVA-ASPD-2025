package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/**
 * Interface: Iteratore all'indietro.
 */
public interface BackwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

    // Prev
    default void Prev(){
        Prev(Natural.ONE);
    }

    default void Prev(Natural num){

        if(!IsValid() || num == null){
            return;
        }

        long limit = num.ToLong();

        if (limit == 0) {
            return;
        }

        /* Vogliamo portare avanti l'iteratori di TOT elementi, allora continuiamo fino a che non abbiamo superato */
        /* TOT posizioni. Controlliamo sempre se l'iteratore è ancora valido, se non è valido ci fermiamo. */
        for(long i = 0; i < limit && IsValid(); i++){
            DatNPrev();
        }

    }

    // DataNPrev
    Data DatNPrev();

    // ForEachBackward
    default boolean ForEachBackward(Predicate<Data> fun){
        if(fun != null){
            while(IsValid()){
                if(fun.Apply(DatNPrev())){
                    return true;
                }
            }
        }
        return false;
    }

}
