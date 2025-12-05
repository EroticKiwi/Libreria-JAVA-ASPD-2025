package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Queue;

import apsd.interfaces.containers.deqs.Queue;

/**
 * Object: Wrapper queue implementation.
 */
public class WQueue<Data> implements Queue<Data> { // Must implement Queue

    protected final List<Data> lst;

    public WQueue() {
        this.lst = new VList<>();
    }

    public WQueue(List<Data> lst) {
        this.lst = lst;
    }

    public WQueue(TraversableContainer<Data> con) {
        if (con == null) {
            throw new IllegalArgumentException("The container given as parameter can't be null!");
        }
        this.lst = new VList<>(con);
    }

    public WQueue(List<Data> lst, TraversableContainer<Data> con) {
        if (lst == null || con == null) {
            throw new IllegalArgumentException("The list and the container must be non-null!");
        }

        this.lst = lst;

        con.TraverseForward(currentElement -> {
            Enqueue(currentElement);
            return false;
        });
    }

    /* ************************************************************************ */
    /* Override specific member functions from Container                        */
    /* ************************************************************************ */

    @Override
    public Natural Size() {
        return lst.Size();
    }

    /* ************************************************************************ */
    /* Override specific member functions from ClearableContainer               */
    /* ************************************************************************ */

    @Override
    public void Clear() {
        lst.Clear();
    }

    /* ************************************************************************ */
    /* Override specific member functions from Queue                            */
    /* ************************************************************************ */

    @Override
    public Data Head(){
        return lst.GetFirst();
    }

    @Override
    public void Dequeue(){
        lst.RemoveFirst();
    }

    @Override
    public Data HeadNDequeue(){
        return lst.AtNRemove(Natural.ZERO);
    }

    @Override
    public void Enqueue(Data data){
        if(data == null){
            throw new IllegalArgumentException("Data can't be null");
        }

        if(Size().ToLong() == Integer.MAX_VALUE){
            throw new IllegalStateException("The queue is full!");
        }

        lst.InsertLast(data);
    }
}
