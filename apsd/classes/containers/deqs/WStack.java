package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;

import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

/**
 * Object: Wrapper stack implementation.
 */
public class WStack<Data> implements Stack<Data> { // Must implement Stack

    protected final List<Data> lst;

    public WStack() {
        this.lst = new VList<>();
    }

    public WStack(List<Data> lst) {
        this.lst = lst;
    }

    public WStack(TraversableContainer<Data> con) {
        this.lst = new VList<>(con);
    }

    public WStack(List<Data> lst, TraversableContainer<Data> con){

        if(lst == null || con == null){
            throw new IllegalArgumentException("The list and the container must be non-null!");
        }

        this.lst = lst;

        con.TraverseForward(currentElement -> {
            Push(currentElement);
            return false;
        });
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    @Override
    public Natural Size(){
        return lst.Size();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    @Override
    public void Clear(){
        lst.Clear();
    }

    /* ************************************************************************ */
    /* Override specific member functions from Stack                            */
    /* ************************************************************************ */

    @Override
    public Data Top(){
        return lst.GetLast();
    }

    @Override
    public void Pop(){
        lst.RemoveLast();
    }

    @Override
    public Data TopNPop(){
        if(IsEmpty()){
            return null;
        }

        return lst.LastNRemove();
    }

    @Override
    public void SwapTop(Data swapDat){
        if(swapDat == null){
            throw new IllegalArgumentException("Can't swap with null data!");
        }

        lst.SetLast(swapDat);
    }

    @Override
    public Data TopNSwap(Data swapDat){
        if(swapDat == null){
            throw new IllegalArgumentException("Can't swap with null data!");
        }

        return lst.GetNSetLast(swapDat);
    }

    @Override
    public void Push(Data dat){
        if(dat == null){
            throw new IllegalArgumentException("Can't insert null data!");
        }

        if(Size().ToLong() == Integer.MAX_VALUE){
            throw new IllegalStateException("The stack is full!");
        }

        lst.InsertLast(dat);
    }

}
