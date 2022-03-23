import java.security.KeyException;

public class Tester {

    public static void main(String[] args) throws KeyException {
        PhoneBook e = new PhoneBook();

        int[] phones = { 1000525220, 10006340, 10001, 100026643, 10003, 10004, 10346005, 99995, 996644996, 99997, 999932338, 99999, 00000, 19919900};
        String[] names = { "sdfddsggasa", "segasegsvsdvsab", "wff", "dbdbdfswaw", "eafawwAWdsvv", "fFFWEfdsgnhn", "fse", "fse", "hkljflwqnw", "iIOIHONklkjla", "jlfknslew", "a", "llkqmmqmmm", "amalwOSLLl"};

        for (int i = 0; i < names.length; i++) {
            e.put(new Person(names[i], names[i]+"a"), 
            new PhoneNumber(phones[i])); //should create collisions
        }

        for (int i = 0; i < names.length; i++) {
            System.out.println((e.get(new Person(names[i], names[i]+"a"))));
        }

        System.out.println(e.size());
        e.remove(new Person(names[6], names[6]+"a"));
        System.out.println(e.size());
        System.out.println(e);
        System.out.println("collisions? "+ e.toString().contains("-->"));

        //all tested working
    }
}
