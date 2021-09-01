import java.util.Stack;

public class StackProbs {
    
    public static Stack<Integer> makeStack(int[] nums) { //prewritten code
        Stack<Integer> stack = new Stack<>();
        for (int num : nums)
        stack.push(num);
        return
        stack;
    }

    public static Stack<Integer> doubleUp(Stack<Integer> nums) {
         Stack<Integer> temp = new Stack<Integer>();
         while (!nums.isEmpty()) {
             temp.add(nums.peek());
             temp.add(nums.pop());
            }
         Stack<Integer> reverse = new Stack<Integer>();
         while (!temp.isEmpty()) {
            reverse.add(temp.pop());
           }
            return reverse;
    }

    public static Stack<Integer> posAndNeg(Stack<Integer> nums) {
        Stack<Integer> pos = new Stack<Integer>();
        Stack<Integer> neg = new Stack<Integer>();

        while (!nums.isEmpty()) {
            if(nums.peek() < 0){
                neg.add(nums.pop());
                continue;
            }
            if(nums.peek() >= 0){
                pos.add(nums.pop());
                continue;
            }
        }

        Stack<Integer> temp = new Stack<Integer>();

        while(!neg.isEmpty()){
                temp.add(neg.pop());
        }
        while(!pos.isEmpty()){
                temp.add(pos.pop());
        }

        return temp;
    }

    public static Stack<Integer> shiftByN(Stack<Integer> nums, int n) {
        Stack<Integer> s1 = new Stack<Integer>();

        while (!nums.isEmpty() && n < nums.size()) {
            s1.add(nums.pop());
        }
        
        Stack<Integer> s4 = new Stack<Integer>();
        while(!s1.isEmpty()){
                s4.add(s1.pop());
        }

        while(!nums.isEmpty()){
                s4.add(nums.pop());
        }

        return s4;
    }

    public static String reverseVowels(String str) {
        Stack<Character> vowels = new Stack<Character>();
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'i' || chars[i] == 'o'|| chars[i] == 'u'){
                vowels.add(chars[i]);
            }
        }

        String out = "";
        for (int i = 0; i < chars.length; i++) {
            
            if(chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'i' || chars[i] == 'o'|| chars[i] == 'u'){
                out += vowels.pop();
            }else{
                out += chars[i];
            }
        }
        return out;
    }
    
    public static boolean bracketBalance(String s) {
        Stack<Character> parenthesis = new Stack<Character>();
        Stack<Character> squareBrackets = new Stack<Character>();
        Stack<Character> curlyBrackets = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
        switch (s.charAt(i)) {
            case '(':
                parenthesis.push(s.charAt(i));
            break;
            case ')':
                parenthesis.pop();
            break;
            case '[':
                 squareBrackets.push(s.charAt(i));
            break;
            case ']':
                squareBrackets.pop();
            break;
            case '{':
                curlyBrackets.push(s.charAt(i));
            break;
            case '}':
                curlyBrackets.pop();
            break;

            default:
            System.out.println("unknown character");
                break;
            }
        }

        if(parenthesis.size() > 0 || squareBrackets.size() > 0 || curlyBrackets.size() > 0){
            return false;
        }
        return true;

    }
}
