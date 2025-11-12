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
    default Data GetAt(Natural posNat) { /* La parte di ricerca potremmo mettera il un metodo di Utility, per evitare duplicazione. */

        long size = Size().ToLong();

        long pos = ExcIfOutOfBound(posNat);
        long halfSize = size / 2;

        Box<Data> dataBox = new Box<Data>();
        Box<Long> indexBox = new Box<Long>();

        if (pos > halfSize) {
            long s = Size().ToLong() - 1;
            indexBox.Set(s);

            TraverseBackward(currentElement -> {
                if (indexBox.Get() == pos) {
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
                if (indexBox.Get() == pos) {
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
    default Data GetFirst() {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        return FIterator().GetCurrent();
    }

    // GetLast
    default Data GetLast() {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        return BIterator().GetCurrent();
    }

    // Search
    default Natural Search(Data dat) {

        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        Box<Long> posBox = new Box<Long>(0L);
        Box<Boolean> foundBox = new Box<Boolean>();

        TraverseForward(currentElement -> {
            if (Objects.equals(currentElement, dat)) {
                foundBox.Set(true);
                return true;
            } else {
                posBox.Set(posBox.Get() + 1);
                return false;
            }
        });

        if (foundBox.Get() == false) {
            return null;
        }

        return Natural.Of(posBox.Get());
    }

    // IsInBound
    default boolean IsInBound(Natural num) {
        if (num == null) {
            throw new NullPointerException("Natural number can't be null!");
        }

        long idx = num.ToLong();
        return idx < Size().ToLong();
    }

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
    Sequence<Data> SubSequence(Natural from, Natural to);

    /* Possibile metodo per evitare duplicazione nella ricerca tramite posizione */
    /*
    private MutableIterator<Data> getIteratorAt(long pos) {
        long halfSize = Size().ToLong() / 2;

        if (pos > halfSize) {
            MutableBackwardIterator<Data> iterator = BIterator();
            long currentPos = Size().ToLong() - 1;

            while (currentPos > pos && iterator.IsValid()) {
                iterator.Prev();
                currentPos--;
            }

            // Grazie al controllo dei limiti nei metodi pubblici,
            // iterator.IsValid() qui sarà sempre vero.
            return iterator; // Avviene l'upcasting a MutableIterator

        } else {
            MutableForwardIterator<Data> iterator = FIterator();
            long currentPos = 0;

            while (currentPos < pos && iterator.IsValid()) {
                iterator.Next();
                currentPos++;
            }

            // Anche qui, IsValid() sarà sempre vero.
            return iterator; // Avviene l'upcasting a MutableIterator
        }
    }
     */
}
