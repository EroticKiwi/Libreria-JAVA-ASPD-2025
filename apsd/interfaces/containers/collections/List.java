package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data> { // Must extend MutableSequence, InsertableAtSequence, and Chain

    // SubList
    default List<Data> SubList(Natural from, Natural to) {
        return (List<Data>) SubSequence(from, to);
    }

    /* ************************************************************************ */
    /* Override specific member functions from ExtensibleContainer              */
    /* ************************************************************************ */

    @Override
    default boolean Insert(Data dat){
        if(Size().ToLong() == Integer.MAX_VALUE){
            return false;
        }

        InsertLast(dat);
        return true;
    }

}
