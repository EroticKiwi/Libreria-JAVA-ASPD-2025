package test.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.LLList;
import apsd.classes.containers.deqs.WQueue;

class LLQueueTest {

    public static void main(String[] args) {
        // TestEmpty();
        TestNonEmpty();
    }

    private static void TestEmpty(){
        WQueue<Long> queue = new WQueue<>(new LLList<>());
        System.out.println("Head is: " + queue.Head()); // Ritorna null, CORRETTO
        queue.Dequeue();
        System.out.println("Old Head " + queue.HeadNDequeue()); // Ritorna null, CORRETTO
    }

    private static void TestNonEmpty(){
        WQueue<Long> queue = new WQueue<>(new LLList<>());
        System.out.println("Size: " + queue.Size());
        queue.Enqueue(4L);
        System.out.println("Size: " + queue.Size());
    }

}
