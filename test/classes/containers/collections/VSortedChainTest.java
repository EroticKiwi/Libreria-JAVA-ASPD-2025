package test.classes.containers.collections;

import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.iterators.ForwardIterator;

class VSortedChainTest {

    public static void main(String[] args) {
        TestNonEmpty();
    }

    private static void TestNonEmpty(){

        VSortedChain<Long> vChain = new VSortedChain<>();

        vChain.Insert(1L);
        vChain.Insert(3L);
        vChain.Insert(2L);
        vChain.Insert(5L);
        vChain.Insert(2L);
        vChain.Insert(9L);
        vChain.Insert(2L);

        vChain.RemoveOccurrences(2L);

        vChain.TraverseForward(currentElement -> { // 1,3,5,9 CORRETTO
            System.out.println(currentElement);
            return false;
        });

        System.out.println("Size: " + vChain.Size());

        ForwardIterator<Long> iterator = vChain.FIterator();
        while(iterator.IsValid()){ // 1,3,5,9 CORRETTO
            System.out.println(iterator.DatNNext());
        }

        System.out.println("Predecessor of 3: " + vChain.Predecessor(3L)); // 1, CORRETTO
        System.out.println("Successor of 3: " + vChain.Successor(3L)); // 5, CORRETTO
        System.out.println("Predecessor of 1: " + vChain.Predecessor(1L)); // null, CORRETTO
        System.out.println("Successor of 9: " + vChain.Successor(9L)); // null, CORRETTO

        vChain.Insert(0L);

        System.out.println("Predecessor of 1: " + vChain.Predecessor(1L)); // 0, CORRETTO

        iterator.Reset();
        while(iterator.IsValid()){ // 0,1,3,5,9 CORRETTO
            System.out.println(iterator.DatNNext());
        }
    }

}
