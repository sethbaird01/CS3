import java.security.KeyException;
import java.util.Arrays;

public class EmployeeDatabase {

    private static final int BACKING_ARR_SIZE = 131;
    private Entry[] arr = new Entry[131];

    private class Entry {
        int ID;
        Employee e;

        private Entry(int ID, Employee e) {
            this.ID = ID;
            this.e = e;
        }

        @Override
        public String toString() {
            return this.e.toString();
        }
    }

    public Employee get(int ID) throws KeyException {
        int newIndex = hash(ID);
        if (arr[newIndex] == null) {
            throw new KeyException("Element not found");
        }
        while (arr[newIndex].e.ID != ID) {// collisions
            newIndex += (newIndex / 2);
        }
        return arr[newIndex].e;
    }

    public void put(int ID, Employee value) throws KeyException {
        int initHash = hash(ID);
        System.out.println("int "+ID+" hashes to "+ initHash);
        int chain = 0;
        while (arr[initHash] != null) {
            initHash += (initHash / 2);
            System.out.println("New index: "+ initHash);
            chain++;
            if (chain > 2) {
                System.out.println("Collision: " + (chain + 1) + " adjacent elements");
            }
            if (initHash > BACKING_ARR_SIZE - 1) {
                // wrap
                initHash = hash(ID);
            }
            if (chain >= BACKING_ARR_SIZE * 2) {
                // wrapped twice, nowhere to add new element
                throw new KeyException("Array full");
            }
        }
        arr[initHash] = new Entry(ID, value);

    }

    private static int hash(int in) {
        // returns hashed index
        return in % BACKING_ARR_SIZE;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }

}