import java.util.PriorityQueue;

public class Djikstra {
    Graph g;

    public Djikstra(Graph g) {
        this.g = g;
    }

    public double distance(int sourceId, int destId) {
        djikstra(sourceId, destId);
        return g.getVertexByID(destId).getDistance();
    }

    private void djikstra(int sourceId, int destId) {
        // destId was mentioned in intructions but goes unused in implementation,
        // just going to leave it here for continuity

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(g.getVertexByID(sourceId));

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            current.setVisited();

            // opposite is grabbed from edge

            for (Edge e : g.allEdgesContaining(current)) {
                Vertex opposite = e.getVertexNot(current);
                double dist = 0;
                if (current.getDistance() == Double.POSITIVE_INFINITY) {
                    dist = current.euclidean(opposite);
                } else {
                    dist = current.getDistance() + current.euclidean(opposite);
                }

                if (dist < opposite.getDistance() && !opposite.getVisited()) {
                    opposite.setDistance(dist);
                    pq.offer(opposite);
                }
            }
        }
        // pop vertex
        // update distances of neighbors to the recently popped vertex
        // if (current vertex distance + edge weight) < next vertex distance, add vertex
        // with updated distance to pq (if not visited)
        // repeat until pq.isempty
    }

}
