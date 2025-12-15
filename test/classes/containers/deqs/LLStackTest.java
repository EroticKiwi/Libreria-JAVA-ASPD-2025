package test.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.deqs.WStack;

class LLStackTest {

    public static void main(String[] args) {
        TestNonEmpty();
    }


    private static void TestNonEmpty(){
        WStack<Long> stack = new WStack<>(new LLList<>());

        stack.Insert(4L);
        stack.Push(0L);
        stack.Insert(3L);
        stack.Push(1L);
        stack.Insert(2L);

        System.out.println("Size: " + stack.Size()); // 5, CORRETTO
        System.out.println("Top: " + stack.Top()); // 2, CORRETTO

        System.out.println("Old Top: " + stack.TopNPop()); // 2, CORRETTO
        System.out.println("Top: " + stack.Top()); // 1, CORRETTO
        System.out.println("Size: " + stack.Size()); // 4, CORRETTO
    }
}
