import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PrimePath {
    private int a;
    private int b;
    // code only supports a < b and numbers of equal length

    private class Tuple{
        public int x;
        public int y;

        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null && ((Tuple) obj).x == this.x && ((Tuple) obj).y == this.y;
        }
    }

    public void run(String path) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(path));
        this.a = scan.nextInt();
        this.b = scan.nextInt();
        scan.close();

        int pathStr = this.path();
        System.out.println(pathStr);

        // quick tests of getPrimesOn()
        // System.out.println(getPrimesOn(1033, 3).contains(1031));
        // System.out.println(getPrimesOn(1031, 2).contains(1051));
        // System.out.println(getPrimesOn(1051, 1).contains(1151));

    }

    //shared code with Maze.java
    private int path() {
        Queue<Tuple> queue = new LinkedList<>();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        queue.add(new Tuple(this.a, 0));
        // ArrayList<Integer> pathArr = new ArrayList<>();

        //tuple.x is number
        //tuple.y is out (path length)

        while (!queue.isEmpty()) {
            Tuple curr = queue.poll();
            // pathArr.add(curr.x);

            visited.put(curr.x, true);

            if (curr.x == this.b) {
                // System.out.println("path found! "+pathArr);
                return curr.y; 
            }

            for (int neighbor : getNeighbors(curr.x)) {
                    //non visited and of goal length
                    if (!visited.containsKey(neighbor) && (neighbor+"").length() == (b+"").length()) {
                        queue.add(new Tuple(neighbor, curr.y + 1));
                    }
                }
            }
        return -1;
    }

    // neighbors can be found by changing one digit at a time
    private ArrayList<Integer> getNeighbors(int start) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < (start+"").length(); i++) {
            out.addAll(this.getPrimesOn(start, i));
        }
        return out;
    }

    private ArrayList<Integer> getPrimesOn(int start, int index) {
        // int start is path beginning point,
        // int index is number to change and find primes on

        ArrayList<Integer> out = new ArrayList<>();
        char[] startChars = (start + "").toCharArray();

        int mut = Integer.parseInt(startChars[index] + "");
        for (int i = 0; i < 10; i++) {
            mut++;
            if(mut == 10){
                mut = 0;
            }
            // System.out.println("start: "+start+" mut: "+mut);
            
            char[] test = startChars;
            test[index] = Integer.toString(mut).charAt(0);
            int p = Integer.parseInt(String.valueOf(test));

            // System.out.println("p: "+p);
            if(isPrime(p)){
                out.add(p);
            }
        }
        return out;
    }

    // below method taken from online ! assignment isnt about 
    // math optimization so I think it is fine
    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }

}