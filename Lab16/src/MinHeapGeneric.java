
import java.util.Arrays;

public class MinHeapGeneric<T extends Comparable<T> > {
    // backing array
    private T[] heap;

    private static final int DEFAULT_CAPACITY = 8;
    private int max;
    private int size;

    public MinHeapGeneric() {
        this.max = DEFAULT_CAPACITY;
		//cast required, can't make a T[] directly
		//  as it's not known at runtime what T will be
		this.heap = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MinHeapGeneric(T... nums) {
        this.heap = (T[]) new Comparable[nums.length + DEFAULT_CAPACITY];
        this.max = nums.length;
        for (int x = 0; x < nums.length; x++) {
            this.heap[x] = nums[x];
        }
        this.size = this.max;
        buildHeap(0);
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public T peekMinimum() {
        return this.heap[0];
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void doubleCapacity() {
        // copied from myStack lab
        this.heap = Arrays.copyOf(this.heap, this.heap.length * 2);
    }

    public void insert(T value) {
        if (this.size == this.max) {
            this.doubleCapacity();
        }

        this.heap[this.size] = value;
        this.bubbleUp(this.size);
        this.size++;
    }

    private void bubbleUp(int index) {
        int parentIndex = this.getParentIndex(index);
        if (parentIndex < 0 || parentIndex > this.size) {
            return;
        }
        if (this.heap[parentIndex].compareTo(this.heap[index]) <= 0) {
            return;
        }

        this.swap(parentIndex, index);
        this.bubbleUp(parentIndex);
    }

    private void swap(int a, int b) {
        T temp = this.heap[a];
        this.heap[a] = this.heap[b];
        this.heap[b] = temp;
    }

    public T popMinimum() {
        T temp = this.heap[0];
        this.heap[0] = this.heap[this.size - 1];
        siftDown(0);
        this.size -= 1;
        return temp;
    }

    private void siftDown(int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int less = left;
        if (right < this.size && this.heap[right].compareTo(this.heap[left]) < 0){
            less = right;
        }
        if (left >= this.size || this.heap[index].compareTo(this.heap[less]) <= 0) {
            return;
        }

        swap(index, less);
        siftDown(less);
    }

    private void buildHeap(Integer nums) {
        if (nums >= this.max / 2) {
            return;
        }

        this.buildHeap(this.getLeftChildIndex(nums));
        this.buildHeap(this.getRightChildIndex(nums));
        this.siftDown(nums);
    }

    @Override
    public String toString() {
        String output = "";

        for (int i = 0; i <= this.getSize() - 1; i++) // modification here to work with 0-based backing array
            output += this.heap[i] + ", ";

        return output.substring(0, output.lastIndexOf(",")); // lazily truncate last comma
    }

    /**
     * method borrowed with minor modifications from the internet somewhere, for
     * printing
     */
    public void display() {
        int nBlanks = 32, itemsPerRow = 1, column = 0, j = 0;// modification here as well
        String dots = "...............................";
        System.out.println(dots + dots);
        while (j <= this.getSize() - 1) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');

            System.out.print((heap[j] == null) ? "" : heap[j]);

            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');

            j++;
        }
        System.out.println("\n" + dots + dots);
    }

}
