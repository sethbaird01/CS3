
public class Runner {

        //example call: makeStack(new int[] {1, 2, 3, 4})
    public static void main(String[] args) {

        System.out.println("doubleup test");
        System.out.println(StackProbs.doubleUp(StackProbs.makeStack( new int[] {1, 2, 3, 4})).toString());
        System.out.println("");

        System.out.println("posandneg test");
        System.out.println(StackProbs.posAndNeg(StackProbs.makeStack( new int[] {-1, 2, 3, -4})).toString());
        System.out.println("");

        System.out.println("shiftbyn test");
        System.out.println(StackProbs.shiftByN(StackProbs.makeStack( new int[] {7, 23, -7, 0, 22, -8, 4, 5}), 3).toString());
        System.out.println("");

        System.out.println("reversevowels test");
        System.out.println(StackProbs.reverseVowels("hello").toString());
        System.out.println("");
    }
}
