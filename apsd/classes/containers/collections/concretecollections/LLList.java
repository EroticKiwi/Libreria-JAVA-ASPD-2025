package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;

import java.lang.IllegalArgumentException;
// import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
// import apsd.interfaces.containers.sequences.MutableSequence;

/**
 * Object: Concrete list implementation on linked-list.
 */
public class LLList<Data> extends LLChainBase<Data> implements List<Data> { // Must extend LLChainBase and implement List

    public LLList() { }

    public LLList(TraversableContainer<Data> con) {
        super(con);
    }

    protected LLList(long size, LLNode<Data> head, LLNode<Data> tail) {
        super(size, head, tail);
    }

    // NewChain
    @Override
    protected LLList<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
        return new LLList<>(size, head, tail);
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableIterableContainer         */
    /* ************************************************************************ */

    // ...

    @Override
    public MutableForwardIterator<Data> FIterator() {
        return new MutableForwardIterator<Data>() {

            final MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

            @Override
            public boolean IsValid() {
                return iterator.IsValid();
            }

            @Override
            public void Reset() {
                iterator.Reset();
            }

            @Override
            public Data GetCurrent() {
                return iterator.GetCurrent().Get().Get(); /* Box -> LLNode -> Data */
            }

            @Override
            public void SetCurrent(Data dat){
                iterator.GetCurrent().Get().Set(dat);
            }

            @Override
            public Data DatNNext() {
                return iterator.DatNNext().Get().Get(); /* Box -> LLNode -> Data */
            }
        };
    }

    @Override
    public MutableBackwardIterator<Data> BIterator() {
        return new MutableBackwardIterator<Data>() {

            final MutableBackwardIterator<Box<LLNode<Data>>> iterator = BRefIterator();

            @Override
            public boolean IsValid() {
                return iterator.IsValid();
            }

            @Override
            public void Reset() {
                iterator.Reset();
            }

            @Override
            public Data GetCurrent() {
                return iterator.GetCurrent().Get().Get(); /* Box -> LLNode -> Data */
            }

            @Override
            public void SetCurrent(Data dat){
                iterator.GetCurrent().Get().Set(dat);
            }

            @Override
            public Data DatNPrev() {
                return iterator.DatNPrev().Get().Get(); /* Box -> LLNode -> Data */
            }
        };
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    @Override
    public MutableSequence<Data> SubSequence(Natural from, Natural to) {
        return (MutableSequence<Data>) super.SubSequence(from, to);
    }

    // ...
    @Override
    public void SetAt(Data dat, Natural pos) {
        if (dat == null) {
            throw new IllegalArgumentException("Cannot set null value in LLList!");
        }

        long limit = ExcIfOutOfBound(pos);
        long size = this.size.ToLong();

        if (limit <= size / 2) {
            long index = 0;
            MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

            while (iterator.IsValid() && index < limit) {
                iterator.Next();
                index++;
            }

            iterator.GetCurrent().Get().Set(dat);
        } else {
            long index = size - 1;
            MutableBackwardIterator<Box<LLNode<Data>>> iterator = BRefIterator();

            while (iterator.IsValid() && index > limit) {
                iterator.Prev();
                index--;
            }

            iterator.GetCurrent().Get().Set(dat);
        }
    }

    @Override
    public void SetFirst(Data dat) {
        SetAt(dat, Natural.ZERO);
    }

    @Override
    public void SetLast(Data dat) {
        if (IsEmpty()) {
            throw new IndexOutOfBoundsException("The container is empty!");
        }
        Natural pos = Natural.Of(this.size.ToLong() - 1);
        SetAt(dat, pos);
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableAtSequence             */
    /* ************************************************************************ */

    // ...
    @Override
    public void InsertAt(Data dat, Natural pos) {

        if (dat == null || pos == null) {
            throw new IllegalArgumentException("Cannot insert null value in LLList!");
        }

        if (pos.ToLong() == 0) {
            LLNode<Data> newNode = new LLNode<>(dat, headref.Get());
            headref.Set(newNode);
            if (IsEmpty()) {
                tailref.Set(newNode);
            }
            this.Size().Increment();
            return;
        }

        if (pos.ToLong() == size.ToLong()) {
            LLNode<Data> newNode = new LLNode<>(dat, null);
            tailref.Get().SetNext(newNode);
            tailref.Set(newNode);
            this.Size().Increment();
            return;
        }

        long limit = ExcIfOutOfBound(pos);
        long size = this.size.ToLong();

        if (limit <= size / 2) {
            long index = 0;
            MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

            LLNode<Data> prevNode = headref.Get(); /* Per sicurezza, e per far stare zitto il compilatore */

            while (iterator.IsValid() && index < limit) {
                prevNode = iterator.GetCurrent().Get();
                iterator.Next();
                index++;
            }

            LLNode<Data> nextNode = iterator.GetCurrent().Get();
            LLNode<Data> newNode = new LLNode<>(dat, nextNode);
            prevNode.SetNext(newNode);
        } else {
            long index = size - 1;
            MutableBackwardIterator<Box<LLNode<Data>>> iterator = BRefIterator();

            LLNode<Data> nextNode = tailref.Get(); /* Per sicurezza, e per far stare zitto il compilatore */

            while (iterator.IsValid() && index > limit) {
                nextNode = iterator.GetCurrent().Get();
                iterator.Prev();
                index--;
            }

            LLNode<Data> prevNode = iterator.GetCurrent().Get();
            LLNode<Data> newNode = new LLNode<>(dat, nextNode);
            prevNode.SetNext(newNode);
        }

        this.Size().Increment();
    }

    @Override
    public void InsertFirst(Data dat) {
        InsertAt(dat, Natural.ZERO);
    }

    @Override
    public void InsertLast(Data dat) {
        Natural pos = Natural.Of(this.size.ToLong());
        InsertAt(dat, pos);
    }

}
