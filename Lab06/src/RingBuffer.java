public class RingBuffer
{
    private double[] data;          // items in the buffer
    private int      first;         // index for the next dequeue or peek
    private int      last;          // index for the next enqueue
    private int      size;          // number of items in the buffer

    /** create an empty buffer, with given max capacity */
    public RingBuffer(int capacity) {
        this.data = new double[capacity+1];
        this.first = 0; //maybe
        this.last = 0; //maybe
        this.size = 0;
    }

    /** return number of items currently in the buffer */
    public int size() {
        return this.size; //REPLACE
    }

    /** is the buffer empty (size equals zero)? */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /** is the buffer full (size equals array capacity)? */
    public boolean isFull() {
        return this.size == this.data.length;
    }

    /** add item x to the end */
    public void enqueue(double x) {
        if(this.isFull()){
            throw new RuntimeException();
        }

        this.data[this.last] = x;
        this.last++;
        this.size++;

        if(this.last == this.data.length){
            this.last = 0;
        }
    }

    /** delete and return item from the front */
    public double dequeue() {
        double out = this.data[this.first];
        this.data[this.first] = 0.0;

        this.first++;
        this.size--;

        if(this.first == this.data.length){
            this.first = 0;
        }
        return out;
    }

    /** return (but do not delete) item from the front */
    public double peek() {
        double out = this.data[this.first];

        // if(out == 0.0){
        //     throw new RuntimeException();
        // }

        return out; //REPLACE
    }

    /** a simple test of the constructor and methods in RingBuffer */
    public static void main(String[] args) {
        int N = 100;
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        System.out.println(buffer.peek());

        /*
         * Your program should produce the following output:
         *
         *  Size after wrap-around is 100
			*  5050.0
         */
    }
}
