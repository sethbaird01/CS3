import java.util.HashMap;

public class Practice {

    private static long fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static HashMap<Integer, Long> fibMemo = new HashMap<>();

    private static long fibonacciMemo(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (!fibMemo.containsKey(n)) {
            fibMemo.put(n, fibonacciMemo(n - 1) + fibonacciMemo(n - 2));
        }

        return fibMemo.get(n);
    }

    private static int pascal(int row, int col) {

        if (col == 0 || row == 0 || col == row) {
            // base cases: left edge node, top node, right edge node
            return 1;
        } else {
            // add above node and above/left node
            return pascal(row - 1, col) + pascal(row - 1, col - 1);
        }

    }

    private static HashMap<Pair, Integer> pascalMemo = new HashMap<>();

    private static int pascalMemo(Pair p) {
        int row = p.a;
        int col = p.b;

        if (col == 0 || row == 0 || col == row) {
            // base cases: left edge node, top node, right edge node
            return 1;
        }

        if (!pascalMemo.containsKey(p)) {
            // add left node and above node
            pascalMemo.put(p, pascalMemo(new Pair(row - 1, col)) + pascalMemo(new Pair(row - 1, col - 1)));
        }

        return pascalMemo.get(p);

    }

    private static String pascalRow(int row) {
        String out = "";
        for (int j = 0; j < row + 1; j++) {
            out += pascal(row, j) + " ";
        }
        return out;
    }

    private static String pascalMemoRow(int row) {
        String out = "";
        for (int j = 0; j < row + 1; j++) {
            out += pascalMemo(new Pair(row, j)) + " ";
        }
        return out;
    }
    
    private static int path(int[][] grid) {

    }


    public static void main(String[] args) {
        // System.out.println("Fibonacci");
        // System.out.println(fibonacci(40));
        // System.out.println("-------");
        // System.out.println(fibonacciMemo(40)); // fast !

        // System.out.println("\nPascal's (Point)");
        // System.out.println(pascal(20, 25));
        // System.out.println("-------");
        // System.out.println(pascalMemo(new Pair(20, 25))); // fast !

        // System.out.println("\nPascal's (Row)");
        // System.out.println(pascalRow(30));
        // System.out.println("-------");
        // System.out.println(pascalMemoRow(30)); // fast !

        System.out.println("\nGrid");
        System.out.println(path(new int[10][10]));
        // System.out.println("-------");
        // System.out.println(pathMemo(new int[10][10])); // 

    }
}
