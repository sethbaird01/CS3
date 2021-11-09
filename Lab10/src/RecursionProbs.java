
public class RecursionProbs {

    double sumReciprocals(int n) {
        double t = 1.0 / n;
        if (n == 0) {
            return 0; // n == 0
        }
        // System.out.println(" 1/" + n + " = " + t);

        return t + sumReciprocals(n - 1);

    }

    int productOfEvens(int n) {
        // Complete the method int productOfEvens(int n) that will return the product of the first n even numbers
        //poe(2) = 2 * 4
        //poe(3) = 2 * 4 * 6
        //poe(4) = 2 * 4 * 6 * 8

        int product = 2;
        int iters  = 1;

        if (n == 0){
            return 1;
        }



        if (n % 2 != 0) {// odd (second or later iterations)
            return n * productOfEvens(n-2);
        }

                n *= 2;

        product *= productOfEvens(n-3);
        iters++;

        if(iters == n){
            return product;
        }

        return 1;
    }

    // riddle: any postulate relating to physics or mathematics
    // ✔ can't be seen - logical concept
    // ✔️ never was - not tangible
    // ✔️ always is to be - defenition of postulate

    // void doubleUp(Stack<Integer> nums) {

    // }

    // void countToBy(int n, int m) {

    // }

    int matchingDigits(int a, int b) {
        //i know the string manipulation is bad but at least i could solve it

        int index = 0;
        
        if(String.valueOf(b).length() > String.valueOf(a).length()){
            //can't find search value if reference is shorter
            System.out.println("out of possiblities, not found");
            return String.valueOf(b).length();
        }

        int currSearch = Integer.parseInt((String.valueOf(a).substring(0, String.valueOf(b).length())));

        if(currSearch == b){
            System.out.println("match found");
            return index;
        }else{
            matchingDigits(Integer.parseInt(String.valueOf(a).substring(1, String.valueOf(a).length())), b);
            index++;
            System.out.println("index++"); //TODO
        }

        return index;
    }

    void printThis(int n) {
        String strOut = "";

        if(n == 0){
            strOut = "";
        }

        if(n % 2 == 0){//even
            strOut = "**";
            n -=2;
        }else{
            strOut = "*";
            n -= 1;
        }

        //remaining n / 2 = layers of brackets
        n/=2;
        while(n-- > 0){//TODO recurse
            strOut = "<" + strOut + ">";
        }

        System.out.println(strOut);
        
        }


    public static void main(String[] args) {
        RecursionProbs rp = new RecursionProbs();
        // System.out.println(rp.sumReciprocals(10));
        // System.out.println(rp.productOfEvens(4));
        // System.out.println(rp.matchingDigits(1000, 0));
        // System.out.println(rp.matchingDigits(298892, 7892));
        // System.out.println(rp.productOfEvens(2));

        /*************************************/
        // RecursionProbs skipped - unfinished
        /*************************************/


        // rp.printThis(8);

    }
}