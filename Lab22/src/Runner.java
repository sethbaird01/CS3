import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws FileNotFoundException {
        final String PATH = "./data/input6.txt";
        // final String PATH = "./data/grid25x25.txt";

        Graph g = new Graph(new Scanner(new File(PATH)));
        Djikstra d = new Djikstra(g);
        int[] ids = g.getStartDest();

        System.out.println("Start: " + g.getVertexByID(ids[0]) + " End: " + g.getVertexByID(ids[1]));
        System.out.println("Distance: " + d.distance(ids[0], ids[1]));

    }
}
