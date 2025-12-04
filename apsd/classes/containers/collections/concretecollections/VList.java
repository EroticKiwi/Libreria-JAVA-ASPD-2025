package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.MutableSequence;
// import apsd.interfaces.containers.sequences.MutableSequence;

/**
 * Object: Concrete list implementation on (dynamic circular) vector.
 */
public class VList<Data> extends VChainBase<Data> implements List<Data> { // Must extend VChainBase and implement List

    public VList() {
        super();
    }

    public VList(TraversableContainer<Data> con) {
        super(con);
    }

    protected VList(DynVector<Data> vec) {
        super(vec);
    }

    // NewChain
    @Override
    protected VList<Data> NewChain(DynVector<Data> vec) {
        return new VList<>(vec);
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableIterableContainer         */
    /* ************************************************************************ */

    // ...
    @Override
    public MutableForwardIterator<Data> FIterator() {
        return vec.FIterator();
    }

    @Override
    public MutableBackwardIterator<Data> BIterator() {
        return vec.BIterator();
    }

    /* ************************************************************************ */
    /* Override specific member functions from MutableSequence                  */
    /* ************************************************************************ */

    // ...
    @Override
    public void SetAt(Data dat, Natural pos) {
        vec.SetAt(dat, pos);
    }

    @Override
    public MutableSequence<Data> SubSequence(Natural from, Natural to) {
        return (MutableSequence<Data>) super.SubSequence(from, to);
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableAtSequence             */
    /* ************************************************************************ */

    // ...
    @Override
    public void InsertAt(Data dat, Natural pos){
        vec.InsertAt(dat, pos);
    }

}
