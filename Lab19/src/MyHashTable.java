import java.util.Arrays;

public class MyHashTable<K, V> implements MyIMap<K, V> {

    private static final int BACKING_ARR_SIZE = 131;
    @SuppressWarnings("unchecked")
    private Entry<K, V>[] arr = (MyHashTable<K, V>.Entry<K, V>[]) new Object[131];
    private int size = 0;

    @SuppressWarnings("hiding")
    private class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null; // extraneous?
        }

        @Override
        public String toString() {
            return (this.key == null ? "" : (this.key.toString() + (this.next == null ? "" : " --> [" +  this.next.key + "]")));
        }
    }

    public V get(K key) {
        int defaultIndex = hash(key) % BACKING_ARR_SIZE;
        Entry<K, V> current = arr[defaultIndex];
        while (!current.key.equals(key)) {// keep going
            if (current.next == null) {
                System.out.println("Element not found");
                return null;
            }
            current = current.next;
        }
        return current.value;
    }

    public V put(K key, V value) {
        size++;
        int insertIndex = hash(key) % BACKING_ARR_SIZE;
        Entry<K, V> inEntry = new Entry<K, V>(key, value);

        //no collision case
        if(this.arr[insertIndex] == null){
            this.arr[insertIndex] = inEntry;
            return value;
        }

        //collision case
        else{
            Entry<K, V> current = this.arr[insertIndex];
            while(current.next != null ){
                current = current.next;
            }
            //now .next is null
            current.next = inEntry;
            return value;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public V remove(K key) {
        int defaultIndex = hash(key) % BACKING_ARR_SIZE;

        // delete first element case
        if(arr[defaultIndex].equals(key)){
            V temp = arr[defaultIndex].value;

            //no next element case
            if(arr[defaultIndex].next == null){
                arr[defaultIndex] = null;
                this.size--;
                return temp;
            }
            //has next element case
            else{
                arr[defaultIndex] = arr[defaultIndex].next;
                this.size--;
                return temp;
            }
        }

        // delete element from collision chain case
        Entry<K, V> current = arr[defaultIndex];

        //looking to delete current.next
        while(!current.next.key.equals(key)){
            if(current.next == null){
                System.out.println("Element not found");
                //no size change
                return null;
            }
            current = current.next;
        }
        //element found and is located at current.next
        //so pass over that reference and print out chains

        // System.out.println("Before chain: " + current);
        current.next = current.next.next;
        size--;
        // System.out.println("After chain: " + current);
        return current.value;
    }

    private static int hash(Object in) {
        // returns hashed index
        return in.hashCode() % BACKING_ARR_SIZE;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.arr);
    }

}
