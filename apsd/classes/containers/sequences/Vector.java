package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.LinearVectorBase;
import apsd.classes.containers.sequences.abstractbases.VectorBase;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/**
 * Object: Concrete (static linear) vector implementation.
 */
public class Vector<Data> extends LinearVectorBase<Data> { // Must extend LinearVectorBase

    public Vector(){
        super();
    }

    public Vector(Natural inisize){
        super(inisize);
    }

    public Vector(TraversableContainer<Data> con){
        super(con.Size());

        MutableNatural index = new MutableNatural(0L);

        con.TraverseForward(currentElement -> {
            SetAt(currentElement, index.ToNatural());
            index.Increment();
            return false;
        });

    }

    protected Vector(Data[] arr){
        super(arr);
    }

    // NewVector
    @Override
    protected Vector<Data> NewVector(Data[] arr) {
        return new Vector<>(arr);
    }

}
