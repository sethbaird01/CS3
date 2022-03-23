public class PhoneNumber {
    String num;

    public PhoneNumber(String num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        // max number of characters final encoded int can be
        final int MAX_INT_LEN = (Integer.MAX_VALUE + "").length() - 1;
        // since MAX_VALUE is 2147483647, max len would be 9 characters

        //copied from Person.java

        double n = 1.0;
        while (((num.length() / n) * 2) > MAX_INT_LEN) {
            n++;
        }

        String out = "";
        char[] arr = this.num.toCharArray();
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

        // System.out.println("encode "+this.num+" --> "+out);
        return Integer.valueOf(out);
    }


    @Override
    public boolean equals(Object obj) {
        return this.num.equals(((PhoneNumber) (obj)).num);
    }

    public PhoneNumber(int in) {
        this.num = in+"";
    }

    @Override
    public String toString() {
        return this.num;
    }
}
