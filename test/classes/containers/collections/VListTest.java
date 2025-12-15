package test.classes.containers.collections;

import apsd.classes.containers.collections.concretecollections.VList;

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

    }

}
