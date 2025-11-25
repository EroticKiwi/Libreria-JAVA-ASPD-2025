package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
// import apsd.classes.utilities.Box;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;

import java.security.InvalidParameterException;
// import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
// import apsd.interfaces.containers.sequences.MutableSequence;

/**
 * Object: Concrete list implementation on linked-list.
 */
public class LLList<Data> extends LLChainBase<Data> implements List<Data> { // Must extend LLChainBase and implement List

    public LLList() {
    }

    public LLList(TraversableContainer<Data> con) {
        super(con);
    }

    protected LLList(long size, LLNode<Data> head, LLNode<Data> tail) {

        /* Il costruttore è protetto dunque siamo NOI a chiamarlo internamente. Testiamo e vediamo come va. */
        /*if (size == 0 || head == null || tail == null) {
            throw new InvalidParameterException("Invalid parameters for LLList constructor!");
        }

        // size > 0 && head != null && tail != null

        if(size == 1 && head != tail){
            throw new InvalidParameterException("Provided size is 1 but head and tail are different nodes!");
        }

        // size > 1 || head == tail*/

        NewChain(size, head, tail);
    }

    // NewChain
    @Override
    protected LLList<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
        /* Essendo il metodo protected, solo all'interno del codice della libreria viene chiamato. */
        /* Assumo che sia tutto corretto a livello di parametri passati. */

        headref.Set(head);
        tailref.Set(head); /* Se size == 1 allora head e tail devono essere uguali per forza */
        this.size.Assign(size);
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableIterableContainer         */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    // ...
    @Override
    public void SetAt(Data dat, Natural pos) {
        if (dat == null) {
            throw new InvalidParameterException("Cannot set null value in LLList!");
        }

        long limit = ExcIfOutOfBound(pos);
        long size = this.size.ToLong();

        if (limit <= size / 2) {
            long index = 0;
            MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

            while(iterator.IsValid() && index < limit){
                iterator.Next();
                index++;
            }

            iterator.GetCurrent().Get().Set(dat);
        } else {
            long index = size - 1;
            MutableBackwardIterator<Box<LLNode<Data>>> iterator = BRefIterator();

            while(iterator.IsValid() && index > limit){
                iterator.Prev();
                index--;
            }

            iterator.GetCurrent().Get().Set(dat);
        }
    }

    @Override
    public void SetFirst(Data dat){
        SetAt(dat, Natural.ZERO);
    }

    @Override
    public void SetLast(Data dat){
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
            throw new InvalidParameterException("Cannot insert null value in LLList!");
        }

        if(pos.ToLong() == 0){
            LLNode<Data> newNode = new LLNode<>(dat, headref.Get());
            headref.Set(newNode);
            this.Size().Increment();
            return;
        }

        if(pos.ToLong() == size.ToLong()){
            LLNode<Data> newNode = new LLNode<>(dat, null);
            tailref.Get().SetNext(newNode);
            tailref.Set(newNode);
            this.Size().Increment();
            return;
        }

        long limit = ExcIfOutOfBound(pos);
        long size = this.size.ToLong();

        if(limit <= size / 2){
            long index = 0;
            MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

            LLNode<Data> prevNode = headref.Get(); /* Per sicurezza, e per far stare zitto il compilatore */

            while(iterator.IsValid() && index < limit){
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

            while(iterator.IsValid() && index > limit){
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
    public void InsertFirst(Data dat){
        InsertAt(dat, Natural.ZERO);
    }

    @Override
    public void InsertLast(Data dat){
        Natural pos = Natural.Of(this.size.ToLong());
        InsertAt(dat, pos);
    }

}
