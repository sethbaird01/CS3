public class Edge {
    Vertex a;
    Vertex b;
    double weight;

    public Edge(Vertex a, Vertex b) {
        this.a = a;
        this.b = b;
        this.weight = Math.sqrt(Math.pow((b.x - a.x), 2) + Math.pow((b.y - a.y), 2));
    }

    public double getWeight() {
        return this.weight;
    }

    public boolean contains(Vertex v){
        return (this.a.equals(v) ^ this.b.equals(v));
    }

    public Vertex getVertexNot(Vertex v){
        if(this.a.equals(v)){
            return b;
        }else if(this.b.equals(v)){
            return a;
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return "[ V1: ("+this.a + ") V2: ("+this.b+ ") Weight: "+this.weight+"]";
    }
}
