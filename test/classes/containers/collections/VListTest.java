package test.classes.containers.collections;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

class VListTest {

    public static void main(String[] args) {
        TestNonEmpty();
    }

    private static void TestNonEmpty(){
        VList<Long> vlist = new VList<>();

        vlist.Insert(0L); // [0]
        vlist.InsertLast(4L); // [0,4]
        vlist.InsertFirst(5L); // [5,0,4]
        vlist.InsertLast(9L); // [5,0,4,9]
        vlist.InsertFirst(2L); // [2,5,0,4,9]
        vlist.InsertFirst(1L); // [1,2,5,0,4,9]
        vlist.Insert(0L); // [0,1,2,5,0,4,9]
        vlist.Insert(5L); // [5,0,1,2,5,0,4,9]
        vlist.InsertLast(1L); // [5,0,1,2,5,0,4,9,1]

        vlist.TraverseForward(currentElement ->{ // OTTENGO [5,0,1,2,5,0,4,9,1], CORRETTO
            System.out.println(currentElement);
            return false;
        });
        System.out.println("Size: " + vlist.Size()); // 9, CORRETTO

        System.out.println(vlist.FoldForward(null, 2L)); // 2, CORRETTO
        System.out.println(vlist.FoldForward(null, 8L)); // 8, CORRETTO

        System.out.println(vlist.FoldForward((dat, acc) -> acc + dat, 0L)); // 27, CORRETTO
        System.out.println(vlist.FoldForward((dat, acc) -> acc - dat, 0L)); // -27, CORRETTO

        MutableForwardIterator<Long> iterator = vlist.FIterator();

        while(iterator.IsValid()){
            System.out.print(iterator.DatNNext()); // [5,0,1,2,5,0,4,9,1], CORRETTO
        }

        vlist.InsertAt(6L, Natural.Of(2));

        System.out.println("\n");

        vlist.TraverseForward(currentElement ->{ // OTTENGO [5,0,6,1,2,5,0,4,9,1], CORRETTO
            System.out.println(currentElement);
            return false;
        });

        System.out.println("Size: " + vlist.Size()); // 10, CORRETTO
    }

}
