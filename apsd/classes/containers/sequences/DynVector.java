package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;

/**
 * Object: Concrete dynamic (linear) vector implementation.
 */
public class DynVector<Data> extends DynLinearVectorBase<Data> { // Must extend DynLinearVectorBase

    public DynVector() {
        super();
    }

    public DynVector(Natural inisize){
        super(inisize);
    }

    public DynVector(TraversableContainer<Data> con){
        super(con);
    }

    protected DynVector(Data[] arr){
        super(arr);
    }

    // NewVector
    @Override
    protected void NewVector(Data[] arr) {
        this.arr = arr;
        size = arr.length;
    }

}
