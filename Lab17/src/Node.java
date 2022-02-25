public class Node implements Comparable<Node>{
    int data;
    Node left, right;
    int weight;

    public Node(int data, int weight) {//construct new node
        this.data = data;
        this.weight = weight;
        left = right = null;
    }

    @Override
    public int compareTo(Node other) {
       return this.weight - other.weight;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "{" + this.data + " | "+ this.weight + "}";
    }
}
