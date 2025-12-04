package apsd.classes.containers.collections.concretecollections.bases;

// import apsd.classes.containers.sequences.Vector;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.traits.Predicate;
import apsd.interfaces.containers.sequences.Sequence;

import java.lang.IllegalArgumentException;

/**
 * Object: Abstract chain base implementation on linked-list.
 */
abstract public class LLChainBase<Data> implements Chain<Data> { // Must implement Chain

    protected final MutableNatural size = new MutableNatural();
    protected final Box<LLNode<Data>> headref = new Box<>();
    protected final Box<LLNode<Data>> tailref = new Box<>();

    // LLChainBase

    public LLChainBase() { }

    public LLChainBase(TraversableContainer<Data> con) {
        size.Assign(con.Size());
        final Box<Boolean> first = new Box<>(true);
        con.TraverseForward(dat -> {
            LLNode<Data> node = new LLNode<>(dat);
            if (first.Get()) {
                headref.Set(node);
                first.Set(false);
            } else {
                tailref.Get().SetNext(node);
            }
            tailref.Set(node);
            return false;
        });
    }

    public LLChainBase(long size, LLNode<Data> head, LLNode<Data> tail) {
        this.size.Assign(size);
        headref.Set(head);
        tailref.Set(tail);
    }

    // NewChain
    protected abstract LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail);

    /* ************************************************************************ */
    /* Specific member functions from LLChainBase                               */
    /* ************************************************************************ */

    // ...
    protected MutableForwardIterator<Box<LLNode<Data>>> FRefIterator() {
        return new MutableForwardIterator<Box<LLNode<Data>>>() {

            final Box<LLNode<Data>> origin = headref;
            Box<LLNode<Data>> current = origin;

            @Override
            public boolean IsValid() {
                return current.Get() != null;
            }

            @Override
            public void Reset() {
                current = origin;
            }

            @Override
            public Box<LLNode<Data>> GetCurrent() {
                if(!IsValid()){ throw new IllegalStateException("The iterator has terminated."); }
                return current;
            }

            @Override
            public void SetCurrent(Box<LLNode<Data>> node) {
                if(!IsValid()){ throw new IllegalStateException("The iterator has terminated."); }
                current = node;
            }

            @Override
            public Box<LLNode<Data>> DatNNext() {
                if(!IsValid()){ throw new IllegalStateException("The iterator has terminated."); }
                LLNode<Data> node = current.Get();
                current = current.Get().GetNext();
                return new Box<LLNode<Data>>(node);
            }
        };
    }

    protected MutableBackwardIterator<Box<LLNode<Data>>> BRefIterator() {
        return new MutableBackwardIterator<Box<LLNode<Data>>>() {

            final Box<LLNode<Data>> origin = tailref;
            Box<LLNode<Data>> current = origin;

            @Override
            public boolean IsValid() {
                return current.Get() != null;
            }

            @Override
            public void Reset() {
                current = origin;
            }

            @Override
            public Box<LLNode<Data>> GetCurrent() {
                if(!IsValid()){ throw new IllegalStateException("The iterator has terminated."); }
                return current;
            }

            @Override
            public void SetCurrent(Box<LLNode<Data>> node) {
                if(!IsValid()){ throw new IllegalStateException("The iterator has terminated."); }
                current = node;
            }

            @Override
            public Box<LLNode<Data>> DatNPrev() {

                if(!IsValid()){ throw new IllegalStateException("The iterator has terminated."); }

                Box<LLNode<Data>> dat = current;
                Box<LLNode<Data>> tempBox = headref;
                Box<LLNode<Data>> prev = null;

                while (!tempBox.IsNull() && tempBox != current) {
                    prev = tempBox;
                    tempBox = tempBox.Get().GetNext();
                }

                /* Alla fine tempBox sarà uguale a current o a null */
                current = prev;
                return dat;
            }
        };
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...
    @Override
    public Natural Size() {
        return size.ToNatural();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    public void Clear() {
        headref.Set(null);
        tailref.Set(null);
        size.Assign(Natural.ZERO);
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    @Override
    public boolean Remove(Data dat) {
        if (dat == null) return false;
        final Box<LLNode<Data>> prd = new Box<>();
        return FRefIterator().ForEachForward(cur -> {
            LLNode<Data> node = cur.Get();
            if (node.Get().equals(dat)) {
                cur.Set(node.GetNext().Get());
                if (tailref.Get() == node) {
                    tailref.Set(prd.Get());
                }
                size.Decrement();
                return true;
            }
            prd.Set(node);
            return false;
        });
    }

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    // ...

    @Override
    public ForwardIterator<Data> FIterator() {
        return new ForwardIterator<Data>() {

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
            public Data DatNNext() {
                return iterator.DatNNext().Get().Get(); /* Box -> LLNode -> Data */
            }

        };
    }

    @Override
    public BackwardIterator<Data> BIterator() {
        return new BackwardIterator<Data>() {

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
                return iterator.GetCurrent().Get().Get();
            }

            @Override
            public Data DatNPrev() {
                return iterator.DatNPrev().Get().Get();
            }
        };
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...

    @Override
    public Data GetFirst() {
        if (headref.Get() == null) return null;
        LLNode<Data> node = headref.Get();
        return node.Get();
    }

    @Override
    public Data GetLast() {
        if (tailref.Get() == null) return null;
        LLNode<Data> node = tailref.Get();
        return node.Get();
    }

    @Override
    public Sequence<Data> SubSequence(Natural from, Natural to) {

        if(from == null || to == null){
            throw new IllegalArgumentException("Indices cannot be null!");
        }

        long start = from.ToLong();
        long end = to.ToLong();

        if(start < 0 || end > size.ToLong() || start > end || end >= Size().ToLong()){
            throw new IllegalArgumentException("Invalid subsequence range.");
        }

        LLNode<Data> headNode = null;
        LLNode<Data> prevNode = null;
        LLNode<Data> currentNode = null;
        LLNode<Data> tailNode = null;
        long index = 0;
        long size = end - start + 1;

        MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        for(int i = 0; i < start; i++){
            iterator.Next();
        }

        while(iterator.IsValid() && index < size){

            currentNode = new LLNode<>(iterator.GetCurrent().Get().Get());
            if(prevNode == null){
                headNode = currentNode;
            } else {
                prevNode.SetNext(currentNode);
            }


            prevNode = currentNode;
            iterator.Next();
            index++;
        }

        tailNode = currentNode;

        return NewChain(end - start, headNode, tailNode);
    }


    /* ************************************************************************ */
    /* Override specific member functions from RemovableAtSequence              */
    /* ************************************************************************ */

    // ...
    @Override
    public Data AtNRemove(Natural pos) {

        long limit = ExcIfOutOfBound(pos);
        long index = 0L;

        /* Caso base primo elemento */
            if (limit == 0 || size.equals(MutableNatural.ONE)) {
            LLNode<Data> node = headref.Get();
            headref.Set(headref.Get().GetNext().Get()); /* Le box sono solo un puntatore ai nodi. Rimangono invariate.*/
            /* Sono i nodi contenuti nelle box a sapere il proprio dato e il proprio successore */

            /* Se headref è null significa che il nodo rimosso era il solo e l'unico, dunque va nullata anche la tail */
            if (headref.IsNull()) {
                tailref.Set(null);
            }

            size.Decrement();
            return node.Get();
        }

        MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();
        Box<LLNode<Data>> prev = null;

        while (iterator.IsValid() && index < limit) {

            if (index == limit - 1) {
                prev = iterator.GetCurrent();
            }

            iterator.Next();
            index++;
        }
        LLNode<Data> prevNode = prev.Get(); /* Prev non potrà mai essere null qui */
        LLNode<Data> current = iterator.GetCurrent().Get();
        LLNode<Data> nextNode = iterator.GetCurrent().Get().GetNext().Get();

        if(nextNode == null){ /* Rimozione coda */
            tailref.Set(prevNode);
            tailref.Get().SetNext(null);
        } else {
            prevNode.SetNext(nextNode);
        }

        size.Decrement();
        return current.Get();
    }

    /* I metodi di RemovableAtSequence chiamano AtNRemove(Natural pos) che qui gestiscono già tutti i casi possibili */
    /*@Override
    public void RemoveFirst(){
        if(headref.Get() == null){ return; }

        AtNRemove(Natural.ZERO);
    }

    @Override
    public void RemoveLast(){
        if(tailref.Get() == null){ return; }

        AtNRemove(Natural.Of(size.ToLong() - 1));
    }

    // FirstNRemove

    // LastNRemove
     */

    /* ************************************************************************ */
    /* Override specific member functions from Collection                       */
    /* ************************************************************************ */

    // ...

    @Override
    public boolean Filter(Predicate<Data> fun) {
        /* Scorre tutti gli elementi della lista, applicando una funzione per ognuno di essi. */
        /* Quando poi la funzione ritorna false allora cancelliamo l'elemento corrente, resettando l'iteratore. */
        /* In LLChainBase, siccome stiamo usando le Box, non abbiamo il problema del reset dell'iteratore. */

        if (fun == null) {
            throw new IllegalArgumentException("Filter function cannot be null!");
        }

        Natural originalSize = Size();

        MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        Box<LLNode<Data>> prev = null;

        Box<LLNode<Data>> current = null;
        LLNode<Data> node = null;

        while (iterator.IsValid()) {
            current = iterator.GetCurrent();
            node = current.Get();

            if (fun.Apply(node.Get())) {
                prev = current;
                iterator.Next();
            } else {

                current.Set(current.Get().GetNext().Get());

                /* Decommentare se ci sono problemi nei test! */
                /*if(prev == null){
                    headref.Set(current.Get());
                }*/

                if (tailref.Get() == node) {
                    if (prev == null) {
                        tailref.Set(null);
                    } else {
                        tailref.Set(prev.Get());
                    }
                }

                size.Decrement();
            }
        }

        return !Size().equals(originalSize);
    }

}
