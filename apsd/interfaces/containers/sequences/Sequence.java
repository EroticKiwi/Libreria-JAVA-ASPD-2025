package apsd.interfaces.containers.sequences;

// import apsd.classes.utilities.Box;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;

import java.util.Objects;

/**
 * Interface: IterableContainer con supporto alla lettura e ricerca tramite
 * posizione.
 */
public interface Sequence<Data> extends IterableContainer<Data> { // Must extend IterableContainer

    // GetAt
    default Data GetAt(Natural posNat){ /* La parte di ricerca potremmo mettera il un metodo di Utility, per evitare duplicazione. */

        long size = Size().ToLong();

        if(posNat.ToLong() >= size){
            // Forse dovremmo lanciare un eccezione
        }

        long halfSize = size / 2;
        long pos = posNat.ToLong();

        Box<Data> dataBox = new Box<Data>();
        Box<Long> indexBox = new Box<Long>();

        if(pos > halfSize){
            long s = Size().ToLong() - 1;
            indexBox.Set(s);

            TraverseBackward(currentElement -> {
                if(indexBox.Get() == pos){
                    dataBox.Set(currentElement);
                    return true;
                } else {
                    indexBox.Set(indexBox.Get() - 1);
                    return false;
                }
            });
        } else {
            indexBox.Set(0L);

            TraverseForward(currentElement -> {
                if(indexBox.Get() == pos){
                    dataBox.Set(currentElement);
                    return true;
                } else {
                    indexBox.Set(indexBox.Get() + 1);
                    return false;
                }

            });
        }

        return dataBox.Get();
    }

    // GetFirst
    default Data GetFirst(){
        if(Size().ToLong() == 0){
            // Forse dovremmo lanciare un eccezione
        }

        return FIterator().GetCurrent();
    }

    // GetLast
    default Data GetLast(){
        if(Size().ToLong() == 0){
            // Forse dovremmo lanciare un eccezione
        }

        return BIterator().GetCurrent();
    }

    // Search
    default Natural Search(Data dat){
        if(Size().ToLong() == 0){
            // Forse dovremmo lanciare un eccezione
        }

        Box<Long> posBox = new Box<Long>(0L);

        TraverseForward(currentElement -> {
            if(Objects.equals(currentElement, dat)){
                return true;
            } else {
                posBox.Set(posBox.Get() + 1);
                return false;
            }
        });

        return Natural.Of(posBox.Get());
    }

    // IsInBound

    
    default long ExcIfOutOfBound(Natural num) {
        if (num == null) {
            throw new NullPointerException("Natural number cannot be null!");
        }
        long idx = num.ToLong();
        if (idx >= Size().ToLong()) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
        }
        return idx;
    }

    // SubSequence
}
