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

        if(IsEmpty()){
            return null;
        }

        Data dat = Top();
        Pop();
        return dat;
    }

    // SwapTop
    default void SwapTop(Data swapDat){

        if(swapDat == null){
            throw new IllegalArgumentException("Can't swap with null data!");
        }

        Pop();
        Push(swapDat);
    }

    // TopNSwap
    default Data TopNSwap(Data swapDat){

        if(swapDat == null){
            throw new IllegalArgumentException("Can't swap with null data!");
        }

        Data dat = TopNPop();
        Push(swapDat);
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
        while(!IsEmpty()){
            Pop();
        }
    }

    /* ************************************************************************ */
    /* Override specific member functions from InsertableContainer              */
    /* ************************************************************************ */

    // ...
    @Override
    default boolean Insert(Data dat){

        if(dat == null){
            throw new IllegalArgumentException("Can't insert null data!");
        }

        if(Size().ToLong() == Integer.MAX_VALUE){
            return false;
        }

        Push(dat);
        return true;
    }

}
