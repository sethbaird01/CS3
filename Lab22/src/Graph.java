import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    // undirected weighted graph implementation

    private ArrayList<Vertex> vertices;
    private ArrayList<Edge> edges;
    private int start;
    private int dest;

    public Graph(Scanner scan) {
        final int VERTICES = scan.nextInt();
        final int EDGES = scan.nextInt();

        this.vertices = new ArrayList<>(VERTICES);
        this.edges = new ArrayList<>(EDGES);
        scan.nextLine();
        for (int i = 0; i < VERTICES; i++) {
            this.vertices.add(new Vertex(scan.nextInt(), scan.nextInt(), scan.nextInt()));
        }
        // System.out.println("vertices: "+this.vertices.toString());

        scan.nextLine();
        for (int i = 0; i < EDGES; i++) {
            this.edges.add(new Edge(this.getVertexByID(scan.nextInt()), this.getVertexByID(scan.nextInt())));
        }
        // System.out.println("edges: "+this.edges.toString());
        scan.nextLine();
        this.start = scan.nextInt();
        this.dest = scan.nextInt();

        scan.close();
    }

    public void addVertex(int id, int x, int y) {
        Vertex v = new Vertex(id, x, y);
        this.vertices.add(v);
    }

    public void addEdge(Vertex a, Vertex b) {
        Edge e = new Edge(a, b);
        this.edges.add(e);
    }

    public void addEdge(int idA, int idB) {
        Edge e = new Edge(getVertexByID(idA), getVertexByID(idB));
        this.edges.add(e);
    }

    // could be optimized to make use of hashing or indexes
    public Vertex getVertexByID(int id) {
        for (Vertex v : this.vertices) {
            if (v.getID() == id) {
                return v;
            }
        }
        return null;
    }

    public int[] size() {
        return new int[] { this.vertices.size(), this.edges.size() };
    }

    public ArrayList<Edge> allEdgesContaining(Vertex v) {
        ArrayList<Edge> out = new ArrayList<>();
        for (Edge edge : this.edges) {
            if (edge.contains(v)) {
                out.add(edge);
            }
        }
        return out;
    }

    public int[] getStartDest() {
        return new int[] { this.start, this.dest };
    }
}
