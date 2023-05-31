package graph;

import java.util.LinkedList;

public class Queue<E> {

    private LinkedList<E> linkedList;

    public Queue() {
        linkedList = new LinkedList<>();
    }

    public void add(E item) {
        linkedList.add(item);
    }

    public E remove() {
        return linkedList.remove();
    }

    public void enqueue(E item) {
        linkedList.addLast(item);
    }

    public E dequeue() {
        return linkedList.removeFirst();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

}
