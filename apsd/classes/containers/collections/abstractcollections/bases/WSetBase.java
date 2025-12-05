package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;
// import apsd.interfaces.containers.collections.Chain;
// import apsd.interfaces.containers.collections.Set;
// import apsd.interfaces.containers.iterators.BackwardIterator;
// import apsd.interfaces.containers.iterators.ForwardIterator;
// import apsd.interfaces.traits.Predicate;

/**
 * Object: Abstract wrapper set base implementation via chain.
 */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data> { // Must implement Set; Chn must extend Chain

    protected Chn chn;

    // WSetBase

    /*public WSetBase() { // Forse?
        this.chn = null;
    }*/

    public WSetBase(Chn chn) {
        this.chn = chn;
    }

    public WSetBase(TraversableContainer<Data> con) {

        if (con == null) {
            throw new IllegalArgumentException("The container given as parameter can't be null!");

        }


    }

    public WSetBase(Chn chn, TraversableContainer<Data> con) {

        if (chn == null || con == null) {
            throw new IllegalArgumentException("The chain and the container must be non-null!");
        }

        this.chn = chn;

        con.TraverseForward(currentElement -> {
            chn.InsertIfAbsent(currentElement);
            return false;
        });
    }

    // ChainAlloc
    protected abstract Chn ChainAlloc(); /* Da capire se deve ritornare void o una Chn (nell'UML è void) */

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    // ...

    @Override
    public Natural Size(){
        return chn.Size();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...

    @Override
    public void Clear(){
        chn.Clear();
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    // ...

    @Override
    public boolean Insert(Data dat){
        return chn.InsertIfAbsent(dat); /* POSSIBILE CAUSA DI LOOP INFINITO! */
    }

    /* ************************************************************************ */
    /* Override specific member functions from RemovableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    public boolean Remove(Data dat){
        return chn.Remove(dat);
    }

    /* ************************************************************************ */
    /* Override specific member functions from IterableContainer                */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Collection                       */
    /* ************************************************************************ */

    // ...

    /* ************************************************************************ */
    /* Override specific member functions from Set                              */
    /* ************************************************************************ */

    // ...

}
