import java.security.KeyException;

public class Tester {

    public static void main(String[] args) throws KeyException {
        EmployeeDatabase e = new EmployeeDatabase();

        int[] IDs = { 10000, 10000, 10001, 10002, 10003, 10004, 10005, 99995, 99996, 99997, 99998, 99999, 55555};
        String[] names = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};

        for (int i = 0; i < names.length; i++) {
            e.put(IDs[i], new Employee(IDs[i], names[i])); //should create collisions
        }

        for (int i = 0; i < IDs.length; i++) {
            System.out.println((e.get(IDs[i]))); //should throw now found error
        }

        //tested working

        // System.out.println(e);
    }
}
