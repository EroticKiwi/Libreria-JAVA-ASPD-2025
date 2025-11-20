package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data> { // Must extend ClearableContainer and InsertableContainer

    // Top
    Data Top();

    // Pop
    void Pop();

    // TopNPop
    default Data TopNPop(){
        Data dat = Top();
        Pop();
        return dat;
    }

    // SwapTop
    default void SwapTop(Data swapDat){
        Data Top = Top();
        Pop();
        Push(swapDat);
        Push(Top);
    }

    // TopNSwap
    default Data TopNSwap(Data swapDat){
        Data dat = Top();
        SwapTop(swapDat);
        return dat;
    }

    // Push
    void Push(Data data);

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    // ...
    @Override
    default void Clear(){
        while(Top() != null){
            Pop();
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    // ...
    @Override
    default boolean Insert(Data dat){

        if(Size().ToLong() == Integer.MAX_VALUE){ /* Non saprei, potrebbe essere inutile o sbagliato. */
            return false;
        }

        Push(dat);
        return true;
    }

}
