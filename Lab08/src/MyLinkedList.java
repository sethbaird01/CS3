public class MyLinkedList<T> {

    ListNode<T> head;
    ListNode<T> tail;
    int size;

    private class ListNode<T> {
        T val;
        ListNode<T> next;

        public ListNode(T val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "" + this.toString();
        }
    }

    public MyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = this.head;
    }

    public MyLinkedList(T i) {
        this.size = 1;
        this.head = new ListNode<T>(i);
        this.tail = this.head;
    }

    public MyLinkedList(T... vals){ //tested working
        this.size = 0;
        this.tail = this.head;
        for (T i : vals) {
            this.add(i);
        }

    }

    public void add(T i) {
        this.size++;
        if (this.head == null) {
            this.head = new ListNode<T>(i);
            this.tail = this.head;
        } else {

            this.tail.next = new ListNode<T>(i);
            this.tail = this.tail.next;
        }

        // if (this.head == null) {
        // this.head = new ListNode(i);
        // this.tail = this.head;
        // } else {
        // ListNode current = this.head;
        // while (current.next != null) {
        // // stops when current.next is null, meaning that current is the last element,
        // // and that current.next can be assigned a new ListNode
        // current = current.next;
        // }
        // ListNode temp = new ListNode(i);
        // current.next = temp;
        // this.tail = current.next;
        // }

    }

    public void add(T i, int idx) throws IndexOutOfBoundsException {// tested working

        if (idx != 0 && idx > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }
        this.size++;

        if (this.head == null) {
            this.head = new ListNode<T>(i);
            this.tail = this.head;
            return;
        }

        int index = 0;
        ListNode<T> current = this.head;
        while (current.next != null) {
            // stops when current.next is null, meaning that current is the last element
            if (index + 1 == idx) {
                ListNode<T> n = current.next;
                current.next = new ListNode<T>(i);
                current.next.next = n;
            }
            current = current.next;
            index++;
        }
        this.tail = current;
        if (index == idx) {
            ListNode<T> n = current.next;
            current = new ListNode<T>(i);
            current.next = n;
        }

    }

    public boolean contains(T i) {// tested working
        if (this.head == null) {
            return false;
        }
        if (this.head.val.equals(i)) {
            return true;
        }

        ListNode<T> current = this.head;
        while (current.next != null) {
            if (current.val == i || current.next.val == i) {
                return true;
            }
            current = current.next;

        }
        return false;
    }

    public T get(int i) throws IndexOutOfBoundsException {// tested working
        int index = 0;
        if (this.head == null) {
            throw new IndexOutOfBoundsException();
        } else {
            ListNode<T> current = this.head;
            while (current.next != null) {
                if (index == i) {
                    return current.val;
                }
                current = current.next;
                index++;
            }
            if (index == i) {
                return current.val;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public int indexOf(T i) {// tested working
        int index = 0;
        if (this.head == null) {
            return -1;
        } else {
            ListNode<T> current = this.head;
            while (current.next != null) {
                if (current.val.equals(i)) {
                    return index;
                }
                current = current.next;
                index++;
            }
            if (current.val.equals(i)) {
                return index;
            }
        }
        return index == this.size() - 1 ? -1 : index;
    }

    // riddle: dew

    public void set(T i, int idx) throws IndexOutOfBoundsException { // tested working
        int index = 0;
        if (idx != 0 && this.head == null) {
            throw new IndexOutOfBoundsException();
        } else {
            ListNode<T> current = this.head;
            while (current.next != null) {
                if (index == idx) {
                    current.val = i;
                    break;
                }
                current = current.next;
                index++;
            }
            if (index == idx) {
                current.val = i;
            }
        }
        if (idx != 0 && index == this.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int size() { // tested working
        // int size = 0;
        // ListNode current = this.head;
        // while (current != null) {
        // size++;
        // current = current.next;
        // }
        // return size;

        return this.size;
    }

    public int sizeRecursive(ListNode<T> current) {
        if (current == null) {
            return 0;
        }
        return sizeRecursive(current.next) + 1;
    }

    public boolean isEmpty() { // tested working
        return this.head == null;
    }

    public T remove(int i) {// tested working
        int index = -1;
        T removed = null;
        if (i >= this.size() || i < 0) {// out of bounds
            return null;
        }
        if (this.head == null) {// invalid element
            return null;
        }
        if (i == 0) {// if element to remove is in the first index, things work differently
            removed = this.head.val;
            this.head = this.head.next;
            this.size--;
            return removed;

        } else {

            ListNode<T> current = this.head;
            while (current.next != null) {
                // stops when current.next is null, meaning that current is the last element.
                // current.next.next (target replacement element) could be null, but this should
                // be fine. meaning that the last element in the list can be removed

                if (index + 2 == i) {
                    // current is element before the one to be removed, so it's next value is set to
                    // 2 nodes ahead, effectively skipping the element in the middle
                    removed = current.next.val;
                    current.next = current.next.next;
                    if (index == this.size() - 1) {
                        // index is the last element, set tail
                        this.tail = current;
                    }
                    this.size--;
                    return removed;
                }
                current = current.next;
                index++;
            }

        }
        // find tail
        ListNode<T> current = this.head;
        while (current.next != null) {
            // stops when current.next is null, meaning that current is the last element,
            // and that current.next can be assigned a new ListNode
            current = current.next;
        }
        this.head = current.next;
        return removed;
    }

    @Override
    public String toString() { // tested working
        String out = "[";
        ListNode<T> current = this.head;
        while (current != null) {
            out += current.val + ", ";
            current = current.next;
        }
        out += "]";
        return out.replace(", ]", "]");
    }

}
