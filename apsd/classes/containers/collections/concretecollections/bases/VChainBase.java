package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
// import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/**
 * Object: Abstract list base implementation on (dynamic circular) vector.
 */
abstract public class VChainBase<Data> implements Chain<Data> { // Must implement Chain

    protected final DynVector<Data> vec; /* DynLinearVector o DynCircularVector */

    // VChainBase

    public VChainBase(){
        this.vec = new DynCircularVector<>();
    }

    public VChainBase(DynVector<Data> vec){
        this.vec = vec;
    }

    public VChainBase(TraversableContainer<Data> con){

        this();
        vec.Realloc(con.Size());

        con.TraverseForward(currentElement -> {
            vec.InsertLast(currentElement);
            //Insert(currentElement);
            return false;
        });
    }

    // NewChain
    protected abstract VChainBase<Data> NewChain(DynVector<Data> vec);

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...
    @Override
    public Natural Size(){
        return vec.Size();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    public void Clear(){
        vec.Clear();
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    public boolean Remove(Data dat){

        if(dat == null){
            throw new IllegalArgumentException("Cannot remove null value from VChainBase!");
        }

        if(IsEmpty()){
            return false;
        }

        Natural pos = vec.Search(dat);

        if(pos == null){
            return false;
        } else {
            return vec.AtNRemove(pos) != null;
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    // ...
    @Override
    public ForwardIterator<Data> FIterator(){
        return vec.FIterator();
    }

    @Override
    public BackwardIterator<Data> BIterator(){
        return vec.BIterator();
    }

    /* ************************************************************************ */
    /* Override specific member functions from Sequence                         */
    /* ************************************************************************ */

    // ...
    @Override
    public Data GetAt(Natural pos){
        return vec.GetAt(pos);
    }

    @Override
    public Sequence<Data> SubSequence(Natural from, Natural to){

    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableAtSequence              */
    /* ************************************************************************ */

    // ...
    @Override
    public Data AtNRemove(Natural pos){
        return vec.AtNRemove(pos);
    }

    /* ************************************************************************ */
    /* Override specific member functions from Collection                       */
    /* ************************************************************************ */

    // ...
    @Override
    public boolean Filter(Predicate<Data> fun){
        Natural oldsize = Size();
        if (fun != null) {
            ForwardIterator<Data> itr = FIterator();
            while (itr.IsValid()) {
                Data dat = itr.GetCurrent();
                if (fun.Apply(dat)) {
                    itr.Next();
                } else {
                    vec.RemoveAt(oldsize);
                }
            }
        }

        return !Size().equals(oldsize);
    }

}
