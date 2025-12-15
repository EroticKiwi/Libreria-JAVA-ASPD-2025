package test.classes.containers.sequences;

import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;

class DynCircularVectorTest {

    public static void main(String[] args) {
        // TestInsert();
        TestOrder();
    }

    private void TestInsert(){
        DynCircularVector<Long> dynVector = new DynCircularVector<Long>(Natural.Of(2));
        System.out.println(dynVector.Size());

        dynVector.SetAt(10L, Natural.Of(0));
        System.out.println(dynVector.Size());
    }

    private static void TestOrder(){
        DynCircularVector<Long> dynVector = new DynCircularVector<Long>();

        VSortedChain<Long> vSortedChain = new VSortedChain<Long>(dynVector);

        vSortedChain.Insert(4L);
        vSortedChain.Insert(0L);
        vSortedChain.Insert(3L);
        vSortedChain.Insert(1L);
        vSortedChain.Insert(2L);
        vSortedChain.InsertIfAbsent(2L);

        /* Mi aspetto ora [0, 1, 2, 3, 4]. CORRETTO, L'INSERIMENTO SU VSORTEDCHAIN FUNZIONA */

        /* vSortedChain.TraverseForward(currentElement ->{
            System.out.println(currentElement);
            return false;
        });*/

        vSortedChain.RemoveMin();

        System.out.println("MinNRemove: " +vSortedChain.MinNRemove()); /* Restituisce 1. CORRETTO. */

        vSortedChain.InsertIfAbsent(-1L);
        vSortedChain.InsertIfAbsent(1L);

        System.out.println("Min: " + vSortedChain.Min());
        System.out.println("MaxNRemove: " + vSortedChain.MaxNRemove());
        System.out.println("Size: " + vSortedChain.Size());

        vSortedChain.InsertIfAbsent(6L);
        System.out.println("Size: " + vSortedChain.Size());
        System.out.println("Max: " + vSortedChain.Max());
        vSortedChain.InsertIfAbsent(7L);
        System.out.println("Size: " + vSortedChain.Size());

        System.out.println("Does 6 exist? " + vSortedChain.Exists(6L));
        System.out.println("Does 8 exist? " + vSortedChain.Exists(8L));
        System.out.println("Does 0 exist? " + vSortedChain.Exists(0L));

        System.out.println("Can we remove 5? " + vSortedChain.Remove(5L)); /* false, CORRETTO. */
        System.out.println("Can we remove 2? " + vSortedChain.Remove(2L)); /* true, CORRETTO. */

        System.out.println("Does 5 exist? " + vSortedChain.Exists(5L));
        System.out.println("Does 2 exist? " + vSortedChain.Exists(2L));

        /*vSortedChain.TraverseForward(currentElement ->{
            System.out.println(currentElement);
            return false;
        });*/

        /* Ora abbiamo [-1, 1, 3, 6, 7] */

        vSortedChain.RemoveMax();

        System.out.println("Max: " + vSortedChain.Max()); /* ritorna 6, CORRETTO */

        /*vSortedChain.TraverseForward(currentElement ->{
            System.out.println(currentElement);
            return false;
        });*/

        /* Ora abbiamo [-1, 1, 3, 6] */

        System.out.println("Predecessor of 4: " + vSortedChain.Predecessor(4L)); /* Ritorna 3, CORRETTO */
        System.out.println("Successor of 4: " + vSortedChain.Successor(4L)); /* Ritorna 6, CORRETTO */

    }

}