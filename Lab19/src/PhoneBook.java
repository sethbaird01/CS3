import java.util.Arrays;

public class PhoneBook implements IMap {

    private static final int BACKING_ARR_SIZE = 131;
    private Entry[] arr = new Entry[131];
    private int size = 0;

    private class Entry {
        Person e;
        PhoneNumber p;
        Entry next;

        private Entry(Person e, PhoneNumber p) {
            this.e = e;
            this.p = p;
            this.next = null; // extraneous?
        }

        @Override
        public String toString() {
            return (this.e == null ? "" : (this.e.toString() + (this.next == null ? "" : " --> [" +  this.next.e + "]")));
        }
    }

    public PhoneBook() {
        super();
    }

    public PhoneNumber get(Person key) {
        int defaultIndex = hash(key) % BACKING_ARR_SIZE;
        Entry current = arr[defaultIndex];
        while (!current.e.equals(key)) {// keep going
            if (current.next == null) {
                System.out.println("Element not found");
                return null;
            }
            current = current.next;
        }
        return current.p;
    }

    public PhoneNumber put(Person key, PhoneNumber value) {
        size++;
        int insertIndex = hash(key) % BACKING_ARR_SIZE;
        Entry inEntry = new Entry(key, value);

        //no collision case
        if(this.arr[insertIndex] == null){
            this.arr[insertIndex] = inEntry;
            return value;
        }

        //collision case
        else{
            Entry current = this.arr[insertIndex];
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
    public PhoneNumber remove(Person key) {
        int defaultIndex = hash(key) % BACKING_ARR_SIZE;

        // delete first element case
        if(arr[defaultIndex].equals(key)){
            PhoneNumber temp = arr[defaultIndex].p;

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
        Entry current = arr[defaultIndex];

        //looking to delete current.next
        while(!current.next.e.equals(key)){
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
        return current.p;
    }

    private static int hash(Person in) {
        // returns hashed index
        return in.encode() % BACKING_ARR_SIZE;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.arr);
    }

}
