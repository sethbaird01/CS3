import java.util.HashMap;

public class Thievery {

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

    private static HashMap<Pair, Integer> pascalMap = new HashMap<>();

    private static int pascalMemo(Pair p) {
        int row = p.a;
        int col = p.b;

        if (col == 0 || row == 0 || col == row) {
            // base cases: left edge node, top node, right edge node
            return 1;
        }

        if (!pascalMap.containsKey(p)) {
            // add left node and above node
            pascalMap.put(p, pascalMemo(new Pair(row - 1, col)) + pascalMemo(new Pair(row - 1, col - 1)));
        }

        return pascalMap.get(p);

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
        int m = grid.length;
        int n = grid[0].length;

        // base case:
        if (m == 1 || n == 1) {
            return 1;
        }

        return path(new int[m - 1][n]) + path(new int[m][n - 1]);
    }

    private static HashMap<Integer[][], Integer> pathMap = new HashMap<>();

    private static int pathMemo(Integer[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // base case: 1 x 1 is just one path
        if (m == 1 || n == 1) {
            return 1;
        }

        if (!pathMap.containsKey(grid)) {
            // add left node and above node
            pathMap.put(grid, pathMemo(new Integer[m - 1][n]) + pathMemo(new Integer[m][n - 1]));
        }

        return pathMap.get(grid);
    }

    // private static int minCoins(int total, int[] coins) {
    // i cannot figure this out for the life of me
    // }

    private static int steal(StealPair sp) {
        int[] houses = sp.a;
        int idx = sp.b;

        if (idx >= houses.length) {
            return 0;
        }
        // bypass current index or steal from current
        return Math.max(steal(sp.add(1)), (houses[idx] + steal(sp.add(2))));
    }

    private static HashMap<StealPair, Integer> stealMap = new HashMap<>();

    private static int stealMemo(StealPair sp) {
        int[] houses = sp.a;
        int idx = sp.b;

        if (idx >= houses.length) {
            return 0;
        }

        StealPair addOne = sp.add(1);
        if (!stealMap.containsKey(addOne)) {
            stealMap.put(addOne, steal(addOne));
        }

        StealPair addTwo = sp.add(2);
        if (!stealMap.containsKey(addTwo)) {
            stealMap.put(addTwo, steal(addTwo));
        }

        return Math.max(stealMap.get(addOne), houses[idx] + stealMap.get(addTwo));
    }

    private static int thievery(ThieveryTuple t) {
        int[] values = t.v;
        int[] weights = t.w;
        int amt = t.itemCount;
        int wMax = t.weightMax;

        if (values.length == 0 || wMax == 0 || amt == 0) {
            // base case: no items to steal or reached carrying limit
            return 0;
        }

        if (weights[amt - 1] <= wMax) {
            // meaning item can be carried
            int a = values[amt - 1] + thievery(t.decreaseiMaxwMax(1, weights[amt - 1]));
            int b = thievery(t.decreaseiMax(1));
            return Math.max(a, b);
        } else {
            return thievery(t.decreaseiMax(1));
        }
    }

    private static HashMap<ThieveryTuple, Integer> thieveryMap = new HashMap<>();

    private static int thieveryMemo(ThieveryTuple t) {
        int[] values = t.v;
        int[] weights = t.w;
        int amt = t.itemCount;
        int wMax = t.weightMax;

        if (values.length == 0 || wMax == 0 || amt == 0) {
            // base case: no items to steal or reached carrying limit
            return 0;
        }

        ThieveryTuple decI = t.decreaseiMax(1);
        if (!thieveryMap.containsKey(decI)) {
            thieveryMap.put(decI, thievery(decI));
        }

        if (weights[amt - 1] <= wMax) {
            // meaning item can be carried
            ThieveryTuple decAll = t.decreaseiMaxwMax(1, weights[amt - 1]);

            if (!thieveryMap.containsKey(decAll)) {
                thieveryMap.put(decAll, thievery(decAll));
            }

            int a = values[amt - 1] + thieveryMap.get(decAll);
            int b = thieveryMap.get(decI);
            return Math.max(a, b);
        } else {
            return thieveryMap.get(decI);
        }
    }

    // private static int thieveryMemo(ThieveryTuple t) {
    // return null;
    // }
    public static void main(String[] args) {
        System.out.println("Fibonacci");
        System.out.println(fibonacci(40));
        System.out.println("-------");
        System.out.println(fibonacciMemo(40)); // fast !

        System.out.println("\nPascal's (Point)");
        System.out.println(pascal(20, 25));
        System.out.println("-------");
        System.out.println(pascalMemo(new Pair(20, 25))); // fast !

        System.out.println("\nPascal's (Row)");
        System.out.println(pascalRow(30));
        System.out.println("-------");
        System.out.println(pascalMemoRow(30)); // fast !

        System.out.println("\nGrid");
        System.out.println(path(new int[20][10]));
        System.out.println("-------");
        System.out.println(pathMemo(new Integer[20][10])); // not much faster !

        // System.out.println("\nCoins");
        // System.out.println(minCoins(11, new Integer[] { 9, 6, 5, 1 }));
        // System.out.println("-------");
        // System.out.println(minCoinsMemo(11, new int[] {9, 6, 5, 1})); // !

        System.out.println("\nSteal");
        System.out.println(steal(new StealPair(new int[] { 200, 234, 182, 111, 87,
                194, 221, 217, 71, 162, 140, 51, 81,
                80, 232, 193, 223, 103, 139, 103 }, 0)));
        System.out.println("-------");
        System.out.println(stealMemo(new StealPair(new int[] { 200, 234, 182, 111,
                87, 194, 221, 217, 71, 162, 140, 51,
                81, 80, 232, 193, 223, 103, 139, 103 }, 0))); // faster ? maybe, they are both quite fast

        System.out.println("\nThievery");
        System.out.println(thievery(
                new ThieveryTuple(new int[] { 10, 5, 7, 12, 8, 6 }, new int[] { 6, 1, 2, 5, 4, 3 }, 6, 10)));
        System.out.println("-------");
        System.out.println(thieveryMemo(
                new ThieveryTuple(new int[] { 10, 5, 7, 12, 8, 6 }, new int[] { 6, 1, 2, 5, 4, 3 }, 6, 10)));
        // faster ? maybe, they are both quite fast

    }

}
