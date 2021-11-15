public class RecursionProbs {

    void printBinary(int num, String str) {
        if (str.length() == num) {
            System.out.print(str + " ");
            return;
        } else {
            printBinary(num, str + "0");
            printBinary(num, str + "1");
        }
    }

    public void climbStairsHelper(int n) {

        String str = "[";
        int p = 0;
        climbStairs(str, p, n);

    }

    public void climbStairs(String str, int p, int n) {

        if (n == 0 && p == 2) {
            System.out.print(str.substring(0, str.length() - 2) + "]");
        }
        else if (n == 1) {
            System.out.print(str + "1]");
        }else{
            climbStairs(str+"1, ",1,n-1);
            System.out.println();
            climbStairs(str+"2, ",2,n-2);
        }
    }

    public static void main(String[] args) {
        RecursionProbs rp = new RecursionProbs();
        // rp.printBinary(2, "");
        // rp.climbStairsHelper(4);

        /*************************************/
        // RecursionProbs skipped - unfinished
        /*************************************/

    }
}
