import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Scooby {

    public void run(String path) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(path));
        final int N = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < N; i++) {
            ArrayList<String> connections = new ArrayList<>(N);
            String[] edges = scan.nextLine().split(" ");
            String test = scan.nextLine();

            for (String currEdge : edges) {
                if (!consolidationCheck(currEdge.charAt(0), currEdge.charAt(1), connections)) {
                    // no consolidation, edges arent accounted for
                    if (connIndex(currEdge.charAt(0), connections) > -1) {
                        // found part of edge, combine with the other half
                        String mod = connections.remove(connIndex(currEdge.charAt(0), connections)) + currEdge.charAt(0)
                                + currEdge.charAt(1);
                        connections.add(mod);
                    } else if (connIndex(currEdge.charAt(1), connections) > -1) {
                        // found part of edge, combine with the other half
                        String mod = connections.remove(connIndex(currEdge.charAt(1), connections)) + currEdge.charAt(0)
                                + currEdge.charAt(1);
                        connections.add(mod);
                    } else {
                        // not found anywhere in arraylist, create new element
                        connections.add(currEdge);
                    }
                }
            }

            if (validPath(test.charAt(0), test.charAt(1), connections)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        }
        scan.close();
    }

    static boolean validPath(Character room, Character room2, ArrayList<String> connections) {
        for (String conn : connections) {
            if (conn.contains(room + "") && conn.contains(room2 + "")) {
                return true;
            }
        }
        return false;
    }

    // returns index of String containing room
    static int connIndex(Character a, ArrayList<String> connections) {
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).contains(a + "")) {
                return i;
            }
        }
        return -1;
    }

    // checks if new connection (AB) is present in two connection strings,
    // combines if necessary ro returns false if not.
    static boolean consolidationCheck(Character a, Character b, ArrayList<String> connections) {
        int foundA = connIndex(a, connections);
        int foundB = connIndex(b, connections);
        if (foundA > -1 && foundB > -1 && foundA != foundB) {
            // both indexes are found, consolidate
            String combine = connections.remove(connIndex(a, connections))
                    + connections.remove(connIndex(b, connections)) + a + b;
            connections.add(combine);
            return true;
        }
        return false;
    }
}