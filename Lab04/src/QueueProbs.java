import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class QueueProbs {

    Queue<Integer> evenFirst(Queue<Integer> nums) {
        Queue<Integer> even = new LinkedList<Integer>();
        Queue<Integer> odd = new LinkedList<Integer>();
        while(!nums.isEmpty()){
            if(nums.peek() % 2 == 0){//even
                even.add(nums.remove());
            }
            if(nums.peek() % 2 != 0){//odd
                odd.add(nums.remove());
            }
        }
        Queue<Integer> out = new LinkedList<Integer>();
        while(!even.isEmpty()){
            out.add(even.remove());
        }
        while(!odd.isEmpty()){
            out.add(odd.remove());
        }
        return out;
    }

    boolean numPalindrome(Queue<Integer> nums) { // 0, 1, 0 or 0, 1, 2
        Queue<Integer> in = nums; // 0, 1, 0 or 0, 1, 2
        Stack<Integer> reverse = new Stack<Integer>(); // 0, 1, 0 or 0, 1, 2

        while(!in.isEmpty()){
            reverse.push(in.remove()); // 0, 1, 0 or 2, 1, 0
        }

        while(!reverse.isEmpty() && !in.isEmpty()){
            if(reverse.pop() != in.remove()){
                return false;//0, 1, 0 == 0, 1, 0 or 0, 1, 2 != 2, 1, 0
            }
        }
        return true;
        //this problem alone took me an hour i'm embarrased
    }

    //riddle = 3-day weekend or long weekend such as labor day weekend

    Stack<Integer> primesUntil(int n){
        Queue<Integer> q = new LinkedList<Integer>();//stores 2 through n
        Stack<Integer> out = new Stack<Integer>(); //stores primes

        for (int i = 2; i <= n; i++) {
            q.add(i);
        }

        while(!q.isEmpty()){
            int p = q.remove();
            out.add(p);
            q.removeIf(i -> i % p == 0);
        }

        return out;
    }

}
