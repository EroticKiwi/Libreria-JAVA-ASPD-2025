package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WOrderedSetBase;
import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.SortedChain;

/**
 * Object: Wrapper ordered set implementation via ordered chain.
 */
public class WOrderedSet<Data extends Comparable<Data>> extends WOrderedSetBase<Data, SortedChain<Data>> { // Must extend WOrderedSetBase

    public WOrderedSet(){
        super();
    }

    public WOrderedSet(Chain<Data> chn){
        super(chn);
    }

    public WOrderedSet(TraversableContainer<Data> con){
        super(con);
    }

    public WOrderedSet(Chain<Data> chn, TraversableContainer<Data> con){

        if(chn == null || con == null){
            throw new IllegalArgumentException("Given Chain and container can't be null!");
        }

        VSortedChain<Data> sortedChain = new VSortedChain<Data>(chn);

        super(sortedChain, con); /* Credo si faccia così? */
    }

    @Override
    protected void ChainAlloc(){
        chn = new VSortedChain<>();
    }

}
