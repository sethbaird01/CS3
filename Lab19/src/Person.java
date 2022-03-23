public class Person {
    private String name;
    String firstName;
    String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = firstName + lastName;
    }

    @Override
    public int hashCode() {
        // max number of characters final encoded int can be
        final int MAX_INT_LEN = (Integer.MAX_VALUE + "").length() - 1;
        // since MAX_VALUE is 2147483647, max len would be 9 characters

        // if name.length is 12, encoded int would be over limit (24)
        // 12 / 1 = 12 * 2 = 24
        // if every letter skipped, encoded int would be over limit (12)
        // 12 / 2 = 6 * 2 = 12
        // if every 3 letters are skipped, encoded int would be valid (8)
        // 12 / 3 = 4 * 2 = 8

        // therefore `((name.length() / n) * 2) <= MAX_INT_LEN` to find `n`
        // there's probably some way to get this expression in terms of n
        // but i'm going to use a while loop because it's more intuitive

        double n = 1.0;
        while (((name.length() / n) * 2) > MAX_INT_LEN) {
            n++;
        }

        String out = "";
        char[] arr = this.name.toCharArray();
        String l = "";
        for (int i = 0; i < arr.length; i++) {
                l += (int) arr[i];   
        }

        //pick each nth element from `l`
        for (int i = 0; i < arr.length; i++) {
            if(i % n == 0){
                out += l.charAt(i)+"";
            }
        }

        // System.out.println("encode "+this.name+" --> "+out);
        return Integer.valueOf(out);
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Person) obj).name);
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
