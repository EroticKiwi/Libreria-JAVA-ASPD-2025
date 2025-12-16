package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

// import apsd.interfaces.containers.iterators.ForwardIterator;

/**
 * Object: Concrete sorted chain implementation on linked-list.
 */
public class LLSortedChain<Data extends Comparable<Data>> extends LLChainBase<Data> implements SortedChain<Data> { // Must extend LLChainBase and implement SortedChain

    public LLSortedChain() { }

    public LLSortedChain(LLSortedChain<Data> chn) {
        super(chn);
    }

    public LLSortedChain(TraversableContainer<Data> con) {
        /* Va fatto l'ordinamento dei dati */
        con.TraverseForward(currentElement -> {
            Insert(currentElement);
            return false;
        });
    }

    protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail) {
        super(size, head, tail);
    }

    // NewChain
    @Override
    protected LLSortedChain<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
        return new LLSortedChain<>(size, head, tail);
    }

    /* ************************************************************************ */
    /* Specific member functions of LLSortedChain                               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    // ...

    @Override
    public boolean Insert(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("Cannot insert null value in LLSortedChain!");
        }

        if (size.ToLong() == Integer.MAX_VALUE) {
            throw new IllegalStateException("The container has already reached max capacity!");
        }

        if (headref.IsNull()) { /* Inserimento in testa con lista vuota */
            headref.Set(new LLNode<>(dat, null));
            tailref.Set(headref.Get());
            size.Increment();
            return true;
        }

        ForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        LLNode<Data> currentNode = null;
        LLNode<Data> prevNode = null;
        int compareResult = 0;


        while (iterator.IsValid()) {

            currentNode = iterator.GetCurrent().Get();
            compareResult = dat.compareTo(currentNode.Get()); /* if dat < currentNode.Get() ci fermiamo e inseriamo */

            if (compareResult <= 0) { /* Inseriamo il nuovo nodo prima di currentNode */

                LLNode<Data> newNode = new LLNode<>(dat, currentNode);

                if (prevNode == null) { /* Inserimento in testa con lista non vuota */
                    headref.Set(newNode);
                } else {
                    prevNode.SetNext(newNode);
                }

                size.Increment();
                return true;
            }

            /* Il nuovo nodo è più grande di currentNode */

            if (currentNode.GetNext().IsNull()) { /* Inserimento in coda */
                tailref.Set(new LLNode<>(dat, null));
                currentNode.SetNext(tailref.Get());
                size.Increment();
                return true;
            }

            prevNode = currentNode;
            iterator.Next();
        }

        return false;
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    public boolean Remove(Data dat) {
        if (dat == null) {
            throw new IllegalArgumentException("Cannot remove null value from LLSortedChain!");
        }

        if (IsEmpty()) {
            return false;
        }

        MutableForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        LLNode<Data> currentNode = null;
        LLNode<Data> prevNode = null;
        int compareResult = 0;

        while (iterator.IsValid()) {

            currentNode = iterator.GetCurrent().Get();
            compareResult = dat.compareTo(currentNode.Get());

            if (compareResult < 0) { /* Abbiamo passato il dato, dunque non è presente */
                return false;
            }

            if (compareResult == 0) { /* Dato trovato */

                if (prevNode == null) { /* Rimozione in testa*/
                    headref.Set(currentNode.GetNext().Get());
                    if (size.ToLong() <= 2) {
                        tailref.Set(headref.Get());
                    }
                } else { /* Rimozione in mezzo */
                    prevNode.SetNext(currentNode.GetNext().Get());
                    if (currentNode.GetNext().IsNull()) { /* Rimozione in coda */
                        tailref.Set(prevNode);
                    }
                }

                size.Decrement();
                return true;
            }

            prevNode = currentNode;
            iterator.Next();
        }

        return false;
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    /* Questo è ottimizzato per le Liste, che dovranno scorrere tutti gli elementi per forza.
       Niente GetAt() siccome su liste è un operazione da O(n). Dunque usando una GetAt() avremmo avuto O(n * n). */
    @Override
    public Natural Search(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("Dat cannot be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        ForwardIterator<Data> iterator = FIterator();

        MutableNatural pos = new MutableNatural(0L);
        Data currentData;

        int compareResult = dat.compareTo(headref.Get().Get());

        if (compareResult == 0) {
            return Natural.ZERO;
        } else if (compareResult < 0) {
            return null;
        }

        compareResult = dat.compareTo(tailref.Get().Get());

        if (compareResult == 0) {
            return Natural.Of(size.ToLong() - 1);
        } else if (compareResult > 0) {
            return null;
        }

        while (iterator.IsValid()) {

            currentData = iterator.GetCurrent();
            compareResult = dat.compareTo(currentData);

            if (compareResult == 0) { /* Abbiamo trovato l'elemento che cercavamo */
                return pos.ToNatural();
            }

            if (compareResult < 0) { /* Se dat è più piccolo, significa che non è presente nella Chain. */
                return null;
            }

            iterator.Next();
            pos.Increment();
        }

        return null;
    }

    /* ************************************************************************ */
    /* Override specific member functions from SortedSequence                   */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from OrderedSet                       */
    /* ************************************************************************ */

    @Override
    public Data Predecessor(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        if (dat.compareTo(headref.Get().Get()) <= 0) {
            return null;
        }

        ForwardIterator<Data> iterator = FIterator();
        Data predecessor = null;

        while (iterator.IsValid()) {

            if (iterator.GetCurrent().compareTo(dat) >= 0) {
                break;
            }

            predecessor = iterator.GetCurrent();
            iterator.Next();
        }

        return predecessor;
    }

    @Override
    public Data Successor(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        if (dat.compareTo(tailref.Get().Get()) >= 0) {
            return null;
        }

        ForwardIterator<Data> iterator = FIterator();
        Data successor = null;

        while (iterator.IsValid()) {

            if (iterator.GetCurrent().compareTo(dat) > 0) {
                successor = iterator.GetCurrent();
                break;
            }

            iterator.Next();
        }

        return successor;
    }

    @Override
    public void RemovePredecessor(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return;
        }

        if (dat.compareTo(headref.Get().Get()) <= 0) {
            return;
        }

        ForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        LLNode<Data> predPredecessor = null;
        LLNode<Data> predecessor = null;

        while (iterator.IsValid()) {

            if (iterator.GetCurrent().Get().Get().compareTo(dat) >= 0) {
                break;
            }

            predPredecessor = predecessor;
            predecessor = iterator.GetCurrent().Get();
            iterator.Next();
        }

        if (predecessor == null) {
            return;
        }

        if (predPredecessor != null) { /* Rimozione in mezzo alla lista*/
            predPredecessor.SetNext(predecessor.GetNext().Get());
            if (predecessor.GetNext().IsNull()) { /* Abbiamo rimosso la coda, va aggiornata */
                tailref.Set(predPredecessor);
            }
        } else { /* Rimozione in testa */
            headref.Set(predecessor.GetNext().Get());
            if (Size().ToLong() <= 2) {
                tailref.Set(headref.Get());
            }
        }

        size.Decrement();
    }

    @Override
    public void RemoveSuccessor(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return;
        }

        if (dat.compareTo(tailref.Get().Get()) >= 0) {
            return;
        }

        ForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        LLNode<Data> current = null;
        LLNode<Data> predecessor = null;

        while (iterator.IsValid()) {

            current = iterator.GetCurrent().Get();

            if (current.Get().compareTo(dat) > 0) {
                break;
            }

            predecessor = current;
            iterator.Next();
        }

        if (predecessor == null) { /* Rimozione in testa */
            headref.Set(current.GetNext().Get());
            if (Size().ToLong() <= 2) {
                tailref.Set(headref.Get());
            }
        } else { /* Rimozione in mezzo alla lista */
            predecessor.SetNext(current.GetNext().Get());
            if (predecessor.GetNext().IsNull()) { /* Rimozione in coda */
                tailref.Set(predecessor);
            }
        }

        size.Decrement();
    }

    @Override
    public Data PredecessorNRemove(Data dat) {


        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        if (dat.compareTo(headref.Get().Get()) <= 0) {
            return null;
        }

        ForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        LLNode<Data> predPredecessor = null;
        LLNode<Data> predecessor = null;

        while (iterator.IsValid()) {

            if (iterator.GetCurrent().Get().Get().compareTo(dat) >= 0) {
                break;
            }

            predPredecessor = predecessor;
            predecessor = iterator.GetCurrent().Get();
            iterator.Next();
        }

        if (predecessor == null) {
            return null;
        }

        if (predPredecessor != null) { /* Rimozione in mezzo alla lista*/
            predPredecessor.SetNext(predecessor.GetNext().Get());
            if (predecessor.GetNext().IsNull()) { /* Abbiamo rimosso la coda, va aggiornata */
                tailref.Set(predPredecessor);
            }
        } else { /* Rimozione in testa */
            headref.Set(predecessor.GetNext().Get());
            if (Size().ToLong() <= 2) {
                tailref.Set(headref.Get());
            }
        }

        size.Decrement();

        return predecessor.Get();
    }

    @Override
    public Data SuccessorNRemove(Data dat) {


        if (dat == null) {
            throw new IllegalArgumentException("dat cannot be null!");
        }

        if (IsEmpty()) {
            return null;
        }

        if (dat.compareTo(tailref.Get().Get()) >= 0) {
            return null;
        }

        ForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        LLNode<Data> current = null;
        LLNode<Data> predecessor = null;

        while (iterator.IsValid()) {

            current = iterator.GetCurrent().Get();

            if (current.Get().compareTo(dat) > 0) {
                break;
            }

            predecessor = current;
            iterator.Next();
        }

        if (predecessor == null) { /* Rimozione in testa */
            headref.Set(current.GetNext().Get());
            if (Size().ToLong() <= 2) {
                tailref.Set(headref.Get());
            }
        } else { /* Rimozione in mezzo alla lista */
            predecessor.SetNext(current.GetNext().Get());
            if (predecessor.GetNext().IsNull()) { /* Rimozione in coda */
                tailref.Set(predecessor);
            }
        }

        size.Decrement();

        return current.Get();
    }

    /* ************************************************************************ */
    /* Override specific member functions from Chain                            */
    /* ************************************************************************ */

    // ...

    @Override
    public boolean InsertIfAbsent(Data dat) {

        if (dat == null) {
            throw new IllegalArgumentException("Cannot insert null value in LLSortedChain!");
        }

        if (Search(dat) == null) {
            return Insert(dat);
        }

        return false;
    }

    @Override
    public void RemoveOccurrences(Data dat) {

        if (IsEmpty()) {
            return;
        }

        if (dat == null) {
            throw new IllegalArgumentException("Data parameter can't be null!");
        }

        int compareResult = dat.compareTo(headref.Get().Get());

        if (compareResult < 0) { /* Cerchiamo un dato più piccolo della testa, non potrà mai essere nella lista */
            return;
        }

        compareResult = dat.compareTo(tailref.Get().Get());

        if (compareResult > 0) { /* Cerchiamo un dato più grande della coda, non potrà mai essere nella lista */
            return;
        }

        ForwardIterator<Box<LLNode<Data>>> iterator = FRefIterator();

        LLNode<Data> currentNode = null;
        LLNode<Data> prevNode = null;

        while(iterator.IsValid()){

            if(iterator.GetCurrent().IsNull()){ /* Lavorando con le box, dopo una rimozione potremmo avere problemi */
                return;
            }

            currentNode = iterator.GetCurrent().Get();
            compareResult = dat.compareTo(currentNode.Get());

            if(compareResult < 0){
                return;
            }

            if(compareResult == 0){
                if(prevNode == null){ /* Rimozione in testa */
                    headref.Set(currentNode.GetNext().Get());
                    if(size.ToLong() <= 2){
                        tailref.Set(headref.Get());
                    }
                } else { /* Rimozione in mezzo */
                    prevNode.SetNext(currentNode.GetNext().Get());
                    if(currentNode.GetNext().IsNull()){ /* Rimozione in coda */
                        tailref.Set(prevNode);
                    }
                }

                size.Decrement();
            } else {
                prevNode = currentNode;
                iterator.Next();
            }
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from SortedChain                      */
    /* ************************************************************************ */

    @Override
    public Natural SearchPredecessor(Data dat) {

        if (IsEmpty()) {
            throw new IllegalArgumentException("The container is empty!");
        }

        if (size.ToLong() == 1) {
            return null;
        }

        int compareResult = dat.compareTo(tailref.Get().Get());

        /* Size >=, head & tail != null && head != tail 2*/
        if (compareResult == 0) {
            return Natural.Of(size.ToLong() - 2);
        }

        if (compareResult > 0) { /* Dat è troppo grande per essere nella lista */
            return null;
        }

        /* Il dato cercato deve essere per forza o la testa o un dato che viene PRIMA della coda */

        Natural pos = Search(dat);
        if (pos == null || pos.ToLong() == 0) {
            return null;
        }

        return Natural.Of(pos.ToLong() - 1);
    }

    @Override
    public Natural SearchSuccessor(Data dat) {

        if (IsEmpty()) {
            throw new IllegalArgumentException("The container is empty!");
        }

        if (size.ToLong() <= 1) {
            return null;
        }

        int compareResult = dat.compareTo(tailref.Get().Get());

        /* Size >=, head & tail != null && head != tail 2*/
        if (compareResult >= 0) {
            return null;
        }

        /* Il dato cercato deve essere per forza o la testa o un dato che viene PRIMA della coda */

        Natural pos = Search(dat);
        if (pos == null) {
            return null;
        }

        return Natural.Of(pos.ToLong() + 1);
    }

}
