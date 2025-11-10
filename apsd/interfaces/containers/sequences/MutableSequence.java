package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.iterators.MutableIterator;

// import apsd.interfaces.containers.base.MutableIterableContainer;
// import apsd.interfaces.containers.iterators.MutableForwardIterator;

/**
 * Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione.
 */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> { // Must extend Sequence and MutableIterableContainer

    // SetAt
    default void SetAt(Data dat, Natural posNat) { /* Mezzo duplicato di GetAt() */

        long size = Size().ToLong();

        long pos = ExcIfOutOfBound(posNat);
        long halfSize = Size().ToLong() / 2;

        if (pos > halfSize) {
            MutableBackwardIterator<Data> iterator = BIterator();
            long currentPos = Size().ToLong() - 1;

            while (currentPos > pos && iterator.IsValid()) {
                iterator.Prev();
                currentPos--;
            }

            if (iterator.IsValid()) { // Non si sa mai
                iterator.SetCurrent(dat);
            }
        } else {
            MutableForwardIterator<Data> iterator = FIterator();
            long currentPos = 0;

            while (currentPos < pos && iterator.IsValid()) {
                iterator.Next();
                currentPos++;
            }

            if (iterator.IsValid()) { // Non si sa mai
                iterator.SetCurrent(dat);
            }
        }
    }

    // GetNSetAt
    default Data GetNSetAt(Data dat, Natural posNat) {

        long size = Size().ToLong();

        long pos = ExcIfOutOfBound(posNat);
        long halfSize = Size().ToLong() / 2;

        if (pos > halfSize) {
            MutableBackwardIterator<Data> iterator = BIterator();
            long currentPos = Size().ToLong() - 1;

            while (currentPos > pos && iterator.IsValid()) {
                iterator.Prev();
                currentPos--;
            }

            Data returnDat = iterator.GetCurrent();
            iterator.SetCurrent(dat);
            return returnDat;
        } else {
            MutableForwardIterator<Data> iterator = FIterator();
            long currentPos = 0;

            while (currentPos < pos && iterator.IsValid()) {
                iterator.Next();
                currentPos++;
            }

            Data returnDat = iterator.GetCurrent();
            iterator.SetCurrent(dat);
            return returnDat;
        }
    }

    // SetFirst
    default void SetFirst(Data dat) {

        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        FIterator().SetCurrent(dat);
    }

    // GetNSetFirst
    default Data GetNSetFirst(Data dat) {

        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        MutableForwardIterator<Data> iterator = FIterator();

        Data returnDat = iterator.GetCurrent();
        iterator.SetCurrent(dat);
        return returnDat;
    }

    // SetLast
    default void SetLast(Data dat) {

        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        BIterator().SetCurrent(dat);
    }

    // GetNSetLast
    default Data GetNSetLast(Data dat) {

        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        MutableBackwardIterator<Data> iterator = BIterator();

        Data returnDat = iterator.GetCurrent();
        iterator.SetCurrent(dat);
        return returnDat;
    }

    // Swap
    default void Swap(Natural pos1, Natural pos2) { /* Valuta di usare le Traverse in maniera studiata */

        if(IsEmpty()){
            throw new IndexOutOfBoundsException("The container is empty!");
        }

        long lpos1 = ExcIfOutOfBound(pos1);
        long lpos2 = ExcIfOutOfBound(pos2);

        long lowestPos = Math.min(lpos1, lpos2);
        long highestPos = Math.max(lpos1, lpos2);

        if(lowestPos == highestPos){
            return;
        }

        if(lowestPos >= Size().ToLong() || highestPos >= Size().ToLong()){
            throw new IndexOutOfBoundsException("Index out of bounds: " + lowestPos + " or " + highestPos + "; Size: " + Size() + "!");
        }

        Data dat1 = null;
        Data dat2 = null;

        MutableIterator<Data> iterator1 = null;
        MutableIterator<Data> iterator2 = null;

        /* Le inizializzazioni sopra non resteranno mai null! */

        long halfSize = Size().ToLong() / 2;
        long pos = lowestPos;

        if(pos > halfSize){
            MutableBackwardIterator<Data> iterator = BIterator();
            long currentPos = Size().ToLong() - 1;

            while(currentPos > pos && iterator.IsValid()){
                iterator.Prev();
                currentPos--;
            }

            if(iterator.IsValid()){
                dat1 = iterator.GetCurrent();
                iterator1 = iterator;
            }
        } else {
            MutableForwardIterator<Data> iterator = FIterator();
            long currentPos = 0;

            while(currentPos < pos && iterator.IsValid()){
                iterator.Next();
                currentPos++;
            }

            if(iterator.IsValid()){
                dat1 = iterator.GetCurrent();
                iterator1 = iterator;
            }
        }

        pos = highestPos;
        if(pos > halfSize){
            MutableBackwardIterator<Data> iterator = BIterator();
            long currentPos = Size().ToLong() - 1;

            while(currentPos > pos && iterator.IsValid()){
                iterator.Prev();
                currentPos--;
            }

            if(iterator.IsValid()){
                dat2 = iterator.GetCurrent();
                iterator2 = iterator;
            }
        } else {
            MutableForwardIterator<Data> iterator = FIterator();

            long currentPos = 0;

            while(currentPos < pos && iterator.IsValid()){
                iterator.Next();
                currentPos++;
            }

            if(iterator.IsValid()){
                dat2 = iterator.GetCurrent();
                iterator2 = iterator;
            }
        }

        iterator1.SetCurrent(dat2);
        iterator2.SetCurrent(dat1);

    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    @Override
    MutableSequence<Data> SubSequence(Natural from, Natural to);

    // ...

}
