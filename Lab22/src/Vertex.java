public class Vertex implements Comparable<Vertex> {
    private double dist;
    private boolean visited;
    private int id;
    public int x;
    public int y;
    // Vertex predecessor;

    public Vertex(int id, int x, int y) {
        visited = false;
        dist = Double.POSITIVE_INFINITY;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    // public void setPred(Vertex pred){
    // this.predecessor = pred;
    // }

    public void setDistance(double dist) {
        this.dist = dist;
    }

    public double getDistance() {
        return this.dist;
    }

    public void setVisited() {
        this.visited = true;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public int getID() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id + "|(" + this.x + ", " + this.y + ")";
    }

    @Override
    public int compareTo(Vertex o) {
        return (int) (this.dist - o.getDistance());
    }

    @Override
    public boolean equals(Object obj) {
        // assumes unique ids (how it is suppoed to be)
        return obj != null && (((Vertex) obj).getID() == this.id);
    }

    public double euclidean(Vertex v) {
        return Math.sqrt(
                Math.pow((v.x - this.x), 2)
                        + Math.pow((v.y - this.y), 2));
    }
}
