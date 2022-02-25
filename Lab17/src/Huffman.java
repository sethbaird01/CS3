import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {

    public String[] lookupTable;

    // compress constructor
    public Huffman(String textFile) throws IOException {// compress
        // gather frequencies here then call const with resulting array
        this(getFrequencies(new File(textFile)), textFile); // line 23
    }

    // expand constructor
    public Huffman(String codeFile, String shortFile) throws FileNotFoundException {
        // decode lookuptable
        this.lookupTable = new String[257];
        Scanner scan = new Scanner(new File(codeFile));

        // minimum value length in lookup table (later use)
        int minBitLength = Integer.MAX_VALUE;

        while (scan.hasNextLine()) {
            String currLine = scan.nextLine();
            String[] elements = currLine.split(" ");

            // System.out.println("lookupTable[" + Integer.parseInt(elements[0] + "") + "] =
            // " + elements[1]);
            lookupTable[Integer.parseInt(elements[0] + "")] = elements[1];

            // sets minBitLength for later
            if (elements[1].length() < minBitLength) {
                // System.out.println("new min length: " + elements[1].length());
                minBitLength = elements[1].length();
            }
        }
        scan.close();
        // lookup table full, proceed w decoding

        String[] tfSplit = codeFile.split("\\.");
        final String PATH_OUT = tfSplit[tfSplit.length - 2] + ".new";

        BitInputStream stream = new BitInputStream(shortFile);

        PrintWriter outFile = new PrintWriter(new FileOutputStream(PATH_OUT)); // copied from past lab
        // System.out.println("Out file: " + PATH_OUT);

        String bitCache = "";
        while (true) {// close your eyes
            bitCache += stream.readBit();

            // check bit segnment length before running extra loop
            if (bitCache.length() >= minBitLength) {

                if (bitCache.equals(lookupTable[256])) {
                    // System.out.println("PSEUDO_EOF reached - stop!");
                    bitCache = "";
                    // System.out.println("Closing processes, expanded successfully");
                    outFile.close();
                    stream.close();
                    break;
                }

                // loop over lookupTable to find current bit segment
                // O(1) char--> byte but O(n) byte--> char
                for (int i = 0; i < lookupTable.length; i++) {
                    if (lookupTable[i] != null) {
                        // System.out.println("checking against "+ lookupTable[i] + "("+(char) i +")");
                        if (bitCache.equals(lookupTable[i])) {
                            // System.out.println("Character found: " + i + " (" + (char) i + "), writing to
                            // output file");
                            outFile.print((char) i);
                            bitCache = ""; // clear cache, prepare for next bit segment (character)
                            break; // exit for loop, result found
                        }
                    }
                }
            }
        }
    }

    // *** public methods over ***//

    private Huffman(int[] count, String textFile) throws IOException {
        this.lookupTable = new String[257];
        String[] tfSplit = textFile.split("\\.");
        final String PATH_SHORT = tfSplit[tfSplit.length - 2] + ".short";
        final String PATH_CODE = tfSplit[tfSplit.length - 2] + ".code";

        // frequency array precalculated
        // System.out.println("HT array constructor called");
        PriorityQueue<Node> nodes = new PriorityQueue<Node>();

        for (int i = 0; i < count.length; i++) {
            // index is char value (data) and count[i] is amount of said character
            if (count[i] != 0) {// only adding non-zero frequency characters
                // System.out.println("adding node: " + i + ", " + count[i]);
                nodes.add(new Node(i, count[i]));
            }
        }
        // PQ is now a forest, nodes are in it; record original size for later use
        final int FOREST_SIZE = nodes.size();

        // System.out.println("PQ before: " + nodes.toString());

        while (nodes.size() != 1) {
            // loops over PQ until there is one node

            // takes two least common nodes
            Node combine1 = nodes.poll();
            Node combine2 = nodes.poll();

            // new node of added frequency
            Node interior = new Node(1, combine1.weight + combine2.weight);

            // adding child nodes to new interior node
            interior.left = combine1;
            interior.right = combine2;

            // putting interior node back into PQ
            nodes.add(interior);
        }

        // System.out.println("PQ after: " + nodes.toString());

        // System.out.println("PQ tree printed: ");
        // TreePrinter.printTree(nodes.peek());

        // System.out.println("Tree paths printed: ");
        this.printPaths(nodes.peek(), FOREST_SIZE / 2);

        int Ltable = 0;
        for (int i = 0; i < lookupTable.length; i++) {
            if (lookupTable[i] != null) {
                Ltable++;
            }
        }
        // System.out.println("Forest number: " + FOREST_SIZE + " Lookup table size: " +
        // Ltable);

        outputCode(PATH_CODE);
        outputShort(new File(textFile), PATH_SHORT);

    }

    // static bc it is being invoked in a constructor call
    private static int[] getFrequencies(File textFile) throws FileNotFoundException {

        int[] count = new int[257];
        for (int i : count) {
            count[i] = 0;
        } // array full, now add char frequencies

        Scanner scan = new Scanner(textFile);
        while (scan.hasNextLine()) {
            String currLine = scan.nextLine() + "\n";
            for (int i = 0; i < currLine.length(); i++) {
                char currChar = currLine.charAt(i);
                count[currChar]++;
                // System.out.println("Character: " + currChar + " new frequency: " +
                // count[currChar]);
            }
        }
        scan.close();
        count[256]++; // pseudo-eof
        // System.out.println("Character: PSEUDO_EOF new frequency: 1");
        // frequency array full, all chars recorded
        return count;
    }

    void printPaths(Node node, int size) {
        String path = "";
        printPathsHelper(node, path, "");
    }

    void printPathsHelper(Node node, String path, String dir) {
        if (node == null) {
            return;
        }

        // append direction to output array
        path += dir;

        // leaf node: print path taken to get here
        if (node.left == null && node.right == null) {
            // path finished, add new information to lookup table and print
            lookupTable[node.data] = path;
            // System.out.println(node.data + "'s path: " + path);
            return;

        } else {
            printPathsHelper(node.left, path, "0"); // 0 means left
            printPathsHelper(node.right, path, "1"); // 1 means right
        }
    }

    void outputCode(String filePath) throws IOException {
        // File file = new File(filePath);
        // file.createNewFile();
        PrintWriter outFile = new PrintWriter(new FileOutputStream(filePath)); // copied from past lab
        // System.out.println("Code file: " + filePath);
        for (int i = 0; i < lookupTable.length; i++) {
            if (lookupTable[i] != null) {
                // System.out.println("Writing to file: " + i + " " + lookupTable[i]);
                outFile.println(i + " " + lookupTable[i]);
            }
        }
        // System.out.println("done writing codefile");
        outFile.close();
    }

    void outputShort(File inFile, String outFile) throws FileNotFoundException {
        BitOutputStream stream = new BitOutputStream(outFile);
        Scanner scan = new Scanner(inFile);
        while (scan.hasNextLine()) {
            String currLine = scan.nextLine() + "\n";

            for (int i = 0; i < currLine.length(); i++) {
                char currChar = currLine.charAt(i);

                if (!(currChar == 10 && !scan.hasNextLine())) {
                    // isolate charcter
                    String write = lookupTable[currChar];
                    // System.out.println("Character: " + currChar + " code: " +
                    // lookupTable[currChar]);
                    // System.out.println("Writing to file: " + write);

                    // turn character into bits using lookup table
                    for (int j = 0; j < write.length(); j++) {
                        int currBit = Integer.parseInt(write.charAt(j) + "");
                        // char --> string --> int --> written
                        stream.writeBit(currBit);
                    }
                } else {
                    // System.out.println("trailing newline avoided");
                }
            }
        }
        // don't forget PSEUDO_EOF
        char currChar = 256;
        String write = lookupTable[currChar];
        // System.out.println("Character: PSEUDO_EOF code: " + lookupTable[currChar]);
        // System.out.println("Writing to file: " + write);

        // turn character into bits using lookup table
        for (int j = 0; j < write.length(); j++) {
            int currBit = Integer.parseInt(write.charAt(j) + "");
            // char --> string --> int --> written
            stream.writeBit(currBit);
        }

        // System.out.println("Done writing shortfile");
        stream.close();
        scan.close();
    }
}
