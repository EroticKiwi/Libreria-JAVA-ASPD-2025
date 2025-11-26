package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
// import apsd.interfaces.containers.iterators.ForwardIterator;

/**
 * Object: Concrete sorted chain implementation on linked-list.
 */
public class LLSortedChain<Data> extends LLChainBase<Data> implements SortedChain<Data> { // Must extend LLChainBase and implement SortedChain

    public LLSortedChain() { }

    public LLSortedChain(LLSortedChain<Data> chn){
        super(chn);
    }

    public LLSortedChain(TraversableContainer<Data> con){
        super();
    }

    protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail){
        NewChain(size, head, tail);
    }

    // NewChain
    @Override
    protected LLSortedChain<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
        //super.NewChain(size, head, tail);
    }

    /* ************************************************************************ */
    /* Specific member functions of LLSortedChain                               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    public Natural Search(Data dat){

    }

    /* ************************************************************************ */
    /* Override specific member functions from SortedSequence                   */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from OrderedSet                       */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Chain                            */
    /* ************************************************************************ */

    // ...

}
