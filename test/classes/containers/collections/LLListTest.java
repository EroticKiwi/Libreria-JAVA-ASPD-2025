package test.classes.containers.collections;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.utilities.Natural;

class LLListTest {

    static void main(String[] args) {
        TestEmpty();
    }

    static void TestEmpty(){
        LLList<Long> list = new LLList<>();

        // list.InsertAt(5L, Natural.Of(2)); // IndexOutOfBounds, CORRETTO
        list.InsertAt(2L, list.Size());

        list.TraverseForward(currentElement -> { // 2, CORRETTO
            System.out.println(currentElement);
            return false;
        });

        System.out.println("-----------");

        list.InsertAt(9L, Natural.Of(0));
        list.Insert(52L);
        list.Insert(9L);

        list.TraverseForward(currentElement -> { // [9,2,52,9] , CORRETTO
            System.out.println(currentElement);
            return false;
        });

        list.RemoveOccurrences(9L);

        System.out.println("-----------");

        list.TraverseForward(currentElement -> { // [9,2,52,9] , CORRETTO
            System.out.println(currentElement);
            return false;
        });
    }

}
