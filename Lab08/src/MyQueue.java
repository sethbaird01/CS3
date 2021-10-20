public class MyQueue<T> implements QueueADT<T> {

    MyLinkedList<T> queue;

    public MyQueue() {
        queue = new MyLinkedList<T>();
    }

    public MyQueue(T... vals) {
        queue = new MyLinkedList<>(vals);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void offer(T item) { // what
        this.queue.add(item);
    }

    public T poll() {
        return this.queue.remove(0);
    }

    public int size() {
        return queue.size;
    }

    public void clear() {
        queue.head = null;
        queue.tail = null;
        queue.size = 0;
    }

    public T peek() {
        // return this.queue.get(this.queue.size() - 1);

        return this.queue.get(0);
    }

}
