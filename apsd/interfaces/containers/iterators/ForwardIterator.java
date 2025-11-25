package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/**
 * Interface: Iteratore in avanti.
 */
public interface ForwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

    // Next
    default void Next(){
        Next(Natural.ONE);
    }

    default void Next(Natural num){

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
            DatNNext();
        }
    }

    // DataNNext
    Data DatNNext();

    default boolean ForEachForward(Predicate<Data> fun) {
        if (fun != null) {
            while (IsValid()) { /* Sfoglierà sempre tutta la lista */
                if (fun.Apply(DatNNext())) {
                    return true;
                }
            }
        }
        return false;
    }

}
