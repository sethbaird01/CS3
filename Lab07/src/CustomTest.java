public class CustomTest {

    //my test runner, accounts for edge cases and out of bounds
    public static void main(String[] args) {

        MyLinkedList a = new MyLinkedList(99);
        if (a.get(0) == 99 && a.tail == a.head) {
            System.out.println("1-arg constructor + tail works");
        } else {
            System.out.println("error");
        }

        MyLinkedList b = new MyLinkedList();
        b.add(1);
        b.add(2);
        if (b.get(0) == 1 && b.get(1) == 2) {
            System.out.println("1-arg add and get functions work");
        } else {
            System.out.println("error");
        }

        b.remove(1);
        b.remove(0);
        if (b.toString().equals("[]") && b.isEmpty() && b.size() == 0) {
            System.out.println("Remove 0 and non-0 works");
            System.out.println("ToString and isEmpty work");
            System.out.println("Size works on edge case");
        } else {
            System.out.println("error");
        }

        if (b.toString().equals(new MyLinkedList().toString()) && (b.isEmpty() && new MyLinkedList().isEmpty())) {
            System.out.println("ToString and isEmpty works in edge case");
        } else {
            System.out.println("error");
        }

        b.add(99, 0);
        if (b.toString().equals("[99]") && b.get(0) == 99) {
            System.out.println("2-arg add works");
        } else {
            System.out.println("error");
        }

        try {
            b.add(99, 99);
        } catch (Exception e) {
            System.out.println("2-arg add handles edge case");
        }

        if (b.contains(99)) {
            System.out.println("Contains works when element present");
        } else {
            System.out.println("error");
        }

        if (!b.contains(100)) {
            System.out.println("Contains works when element not present");
        } else {
            System.out.println("error");
        }

        if (b.get(0) == 99) {
            System.out.println("Get works");
        } else {
            System.out.println("error");
        }

        try {
            b.get(99);
        } catch (Exception e) {
            System.out.println("Get handles edge case");
        }

        if (b.indexOf(99) == 0) {
            System.out.println("IndexOf works");
        } else {
            System.out.println("error");
        }

        try {
            b.indexOf(0);
        } catch (Exception e) {
            System.out.println("IndexOf handles edge case");
        }

        b.set(100, 0);
        if (b.get(0) == 100) {
            System.out.println("Set works");
        } else {
            System.out.println("error");
        }

        try {
            b.set(100, 100);
        } catch (Exception e) {
            System.out.println("Set handles edge case");
        }

        b.add(99);
        b.remove(0);

        if (b.size == 1) {
            System.out.println("Size works in normal case");
        } else {
            System.out.println("error");
        }

        b.remove(0);

        if (b.sizeRecursive(b.head) == 0) {
            System.out.println("SizeRecursive works with 0 elements");
        }

        b.add(99);
        b.add(99);

        if (b.sizeRecursive(b.head) == 2) {
            System.out.println("SizeRecursive works in normal case");
        }

    }

}
