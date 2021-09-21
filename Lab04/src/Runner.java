import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Runner {
    public static void main(String[] args) {

        //evenfirst
    
        QueueProbs qp = new QueueProbs();

        Queue<Integer> evenFirst = new LinkedList<Integer>(Arrays.asList(3, 5, 4, 17, 6, 83, 1, 84, 16, 37));
        
        System.out.println("in: "+evenFirst.toString());
        System.out.println("out: "+qp.evenFirst(evenFirst).toString());
        System.out.println();

        //numpalindrome (true)

        Queue<Integer> numPalindrome = new LinkedList<Integer>(Arrays.asList(3, 8, 17, 9, 17, 8, 3));
        
        System.out.println("result: "+qp.numPalindrome(numPalindrome));
        System.out.println();

        //numpalindrome (false)

        numPalindrome = new LinkedList<Integer>(Arrays.asList(3, 10, 17, 9, 17, 8, 3));
        
        System.out.println("result: "+qp.numPalindrome(numPalindrome));
        System.out.println();

        //sieve
        
        System.out.println("primes to 100: "+qp.primesUntil(100).toString());
        System.out.println();

    }
}
