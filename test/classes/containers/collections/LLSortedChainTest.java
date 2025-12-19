package test.classes.containers.collections;

import apsd.classes.containers.collections.concretecollections.LLSortedChain;

class LLSortedChainTest {

    static void main(String[] args) {
        LLSortedChain<Long> chain = new LLSortedChain<>();
        chain.Insert(5L);
        chain.Insert(3L);
        chain.Insert(3L);
        chain.Insert(8L);

        chain.TraverseForward(currentElement -> {
           System.out.println("currentElement: " + currentElement);
           return false;
        });

        System.out.println("Size: " + chain.Size());
        System.out.println("--------------");

        chain.Remove(5L);
        chain.TraverseForward(currentElement -> {
           System.out.println("currentElement: " + currentElement);
           return false;
        });

        System.out.println("Size: " + chain.Size());

        System.out.println("--------------");

        // chain.RemoveSuccessor(5L);
        //System.out.println(chain.PredecessorNRemove(5L) + "\n");
        //System.out.println(chain.SuccessorNRemove(5L) + "\n");

        chain.RemoveOccurrences(3L);

        chain.TraverseForward(currentElement -> {
           System.out.println("currentElement: " + currentElement);
           return false;
        });
        System.out.println("Size: " + chain.Size());
    }

}
