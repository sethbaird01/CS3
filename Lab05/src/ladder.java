import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ladder {

    private static Queue<String> ladder; // stores ladder for returning later
    private static String input; // stores beginning of ladder
    private static String goal; // stores end of ladder
    private static dictionary dict; // dictionary object
    private static HashSet<String> seen; // hashmap of strings (to avoid duplicates)
    private static HashSet<Stack<String>> seenStacks; // hashmap of stacks (to avoid duplicates)
    private static boolean found; // true if word has been found in ladder

    public static void main(String[] args) throws FileNotFoundException {

        Scanner inputScan = new Scanner(new File("/Users/seth/Documents/GitHub/CS3/Lab05/data/input.txt"));
        Queue<String> inputs = new LinkedList<String>(); // stores all the puzzles
        dict = new dictionary(new File("/Users/seth/Documents/GitHub/CS3/Lab05/data/dictionary.txt"));

        while (inputScan.hasNext()) {
            inputs.add(inputScan.next());
        }
        inputScan.close();

        long time = System.currentTimeMillis();

        for (int i = 0; i < inputs.size(); i++) {

            ladder = new LinkedList<String>();
            found = false;
            seen = new HashSet<String>();
            seenStacks = new HashSet<Stack<String>>();

            Stack<String> empty = new Stack<String>();
            seenStacks.add(empty);

            input = inputs.remove().toUpperCase();
            goal = inputs.remove().toUpperCase();
            ladder.add(input);

            if (input.equals(goal)) {
                printFound(true);
                continue;
            }

            if (input.length() != goal.length()) {
                printFound(false);
                continue;
            }

            Queue<Stack<String>> possibilities = new LinkedList<Stack<String>>();

            possibilities = allChangesForWord(input);
            while (!possibilities.isEmpty() && !found) {
                Stack<String> q = possibilities.remove();
                while (seenStacks.contains(q) && !found && !possibilities.isEmpty()) {
                    // runs while q is known, loops until it isn't.
                    q = possibilities.remove();
                }
                seenStacks.add(q);




                // ladder.addAll(q);

                while (!q.isEmpty() && !found) {
                    String add = q.pop();

                    while (seen.contains(add) && !q.isEmpty()) {
                        add = q.pop();
                    }
                    possibilities.addAll(allChangesForWord(add));
                    seen.add(add);
                }

            }
            if (possibilities.isEmpty() && !found) {
                printFound(false);
            } else {
                printFound(found);
            }
        }
        long timeAfter = System.currentTimeMillis();
        double diff = (double) (timeAfter - time) / 1000;
        System.out.println("Seconds to run: " + diff);
    }

    static void printFound(boolean in) {
        if (in) {
            ladder.add(goal);
            if (goal.equals(input)) {
                ladder.remove();
            }



            // System.out.println("Found a ladder! >>> " + ladder + " Length: "+ladder.size());

            System.out.println("Found a ladder! >>> " + input + " and " + goal
                    + ": Uncomment lines 70 and 100 to enable printing ladder.");
        } else {
            System.out.println("No ladder between " + input + " and " + goal);
        }
    }

    // returns all changes
    static Queue<Stack<String>> allChangesForWord(String in) {
        Queue<Stack<String>> out = new LinkedList<Stack<String>>();
        for (int i = 0; i < in.length(); i++) {
            Stack<String> q = validOneLetterChange(in, i);
            out.add(q);
        }
        return out;
    }

    static Stack<String> validOneLetterChange(String in, int index) {
        char[] chars = in.toCharArray();
        Stack<String> out = new Stack<String>();
        // chars[index] is current letter to check
        for (int i = 65; i < 91; i++) {
            chars[index] = (char) i;
            String currWord = charString(chars);
            if (dict.doesExist(currWord) && !seen.contains(currWord)) {
                out.add(currWord);
                // System.out.print("."); // loading indicator
                seen.add(currWord);
                if (currWord.equals(goal)) {
                    found = true;
                }
            }
        }
        return out;
    }

    static String charString(char[] in) {
        String out = "";
        for (int i = 0; i < in.length; i++) {
            out += in[i];
        }
        return out;
    }

}