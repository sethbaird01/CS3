import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class Play {

    ArrayList<Domino> root;

    private class Domino {
        private int id;
        private ArrayList<Domino> connections;

        public Domino(int id) {
            this.id = id;
            this.connections = new ArrayList<>();
        }

        public void connect(Domino d) {
            if (d != null) {
                this.connections.add(d);
            } else {
                System.out.println("Error: attempting to add null element");
            }
        }

        public int count() {
            int out = 1;
            for (Domino conn : this.connections) { // base case: .size() == 0
                out += conn.count();
            }
            return out;
        }

        public int getId() {
            return this.id;
        }

        @Override
        public String toString() {
            String out = "D" + this.id + "(";
            for (Domino domino : this.connections) {
                out += domino;
            }
            return out + ")";
        }
    }

    public Play() {
        this.root = new ArrayList<>();
    }

    public Domino findDomino(int id) {
        HashSet<Integer> visited = new HashSet<>();
        for (Domino temp : this.root) {
            Stack<Domino> stack = new Stack<>();
            stack.add(temp);

            while (!stack.isEmpty()) {
                Domino curr = stack.pop();
                visited.add(curr.getId());
                if (curr.getId() == id) {
                    return curr;
                }

                for (Domino neighbor : curr.connections) {
                    if (visited.contains(neighbor.getId())) {
                        continue;
                    }
                    stack.add(neighbor);
                }
            }

        }
        System.out.println("Error: domino not found");
        return null;
    }

    private void connect(int x, int y) {
        // add to x
        Domino dX = this.findDomino(x);
        dX.connect(this.findDomino(y));

        // i have never used a predicate before ,, hope this works
        this.root.removeIf(d -> d.getId() == y);
    }

    @Override
    public String toString() {
        String out = "Root[";
        for (Domino domino : root) {
            out += domino + ", ";
        }
        return out + " ]";
    }

    public void run(String path) throws FileNotFoundException {

        Scanner scan = new Scanner(new File(path));
        final int CASES = scan.nextInt();
        for (int i = 0; i < CASES; i++) {
            this.root = new ArrayList<>();

            final int N = scan.nextInt(); // number of domino tiles (no 1 to n)
            final int M = scan.nextInt(); // number of lines containing x, y
            final int L = scan.nextInt(); // number of lines containing z

            for (int j = 0; j < N; j++) {
                // add dominoes
                this.root.add(new Domino(j + 1));
            }

            for (int j = 0; j < M; j++) {
                int X = scan.nextInt();
                int Y = scan.nextInt();
                // add connections
                this.connect(X, Y);
            }

            int fallen = 0;
            for (int j = 0; j < L; j++) {
                int Z = scan.nextInt();
                // knock over
                fallen += findDomino(Z).count();
            }

            System.out.println(fallen + " Dominoes knocked over");

        }

        scan.close();
    }
}
