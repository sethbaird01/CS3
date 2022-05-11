import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Maze {

    private boolean[][] cells;
    private boolean[][] visited;
    private int R;
    private int C;
    private int[] DEST;
    private int[] SOURCE;

    public void run(String path) throws FileNotFoundException {
        // (r, c) coordinates are stored as an int array here
        // with coord[0] as r and coord[1] as c

        Scanner scan = new Scanner(new File(path));
        this.R = scan.nextInt();
        this.C = scan.nextInt();

        System.out.println("R: " + this.R + " C: " + this.C);

        this.cells = new boolean[R][C];
        this.visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            this.cells[i] = new boolean[C];
            this.visited[i] = new boolean[C];

            // String[] chars = scan.nextLine().split(" ");
            for (int j = 0; j < C; j++) {
                int currInt = scan.nextInt(); // 1 or 0
                if (currInt == 0) {
                    cells[i][j] = false;
                } else if (currInt == 1) {
                    cells[i][j] = true;
                } else {
                    System.out.println("Parse error");
                }
                this.visited[i][j] = false;
            }
        }

        this.SOURCE = new int[] { scan.nextInt(), scan.nextInt() };
        this.DEST = new int[] { scan.nextInt(), scan.nextInt() };
        scan.close();

        System.out.println("Distance: " + path());
    }

    private int[][] getNeighbors(int r, int c) {
        // System.out.println("change in r: "+(Math.abs(this.DEST[0] - r))+" | change in c: "+(Math.abs(this.DEST[1] - c)));

        // returns in N, E, S, W order (with null fillers)

        int[][] out = new int[4][2]; //4 coordinate pairs
        
        if (r - 1 >= 0) {
            out[0][0] = r - 1;
            out[0][1] = c;
        }

        if (c + 1 < this.C) {
            out[1][0] = r;
            out[1][1] = c + 1;
        }

        if (r + 1 < this.R) {
            out[2][0] = r + 1;
            out[2][1] = c;
        }

        if (c - 1 >= 0) {
            out[3][0] = r;
            out[3][1] = c - 1;
        }
        return out;
    }


    private int path() {
        Stack<int[]> stack = new Stack<>();
        int out = -1;
        stack.add(SOURCE);

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            out++;

            visited[curr[0]][curr[1]] = true;
            // System.out.println("current index: (" + curr[0] + ", " + curr[1]);
            // System.out.println("goal index: (" + DEST[0] + ", " + DEST[1]);

            if (curr[0] == DEST[0] && curr[1] == DEST[1]) {
                return out; //removes first jump
            }

            for (int[] neighbor : getNeighbors(curr[0], curr[1])) {
                if (neighbor != null) {
                    if (visited[neighbor[0]][neighbor[1]] || !this.cells[neighbor[0]][neighbor[1]]) {
                        continue;
                    }
                    stack.add(neighbor);
                }
            }
        }
        return -1;
    }

}
