import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Runner {

    public static void main(String[] args) throws IOException {

        String[] compress = {
                "data/Hamlet.txt",
                "data/happy hip hop.txt",
                "data/short.txt",
                "data/War and Peace.txt"
        };

        for (String textFile : compress) {
            String[] tfSplit = textFile.split("\\.");
            final String PATH_SHORT = tfSplit[tfSplit.length - 2] + ".short";
            final String PATH_CODE = tfSplit[tfSplit.length - 2] + ".code";
            final String PATH_NEW = tfSplit[tfSplit.length - 2] + ".new";

            // compress
            Huffman toCompress = new Huffman(textFile);
            // expand
            Huffman toExpand = new Huffman(PATH_CODE, PATH_SHORT);

            // diff files
            boolean diff = filesCompareByLine(Paths.get(textFile), Paths.get(PATH_NEW)) == -1L;

            System.out.println("Files identical?: " + diff);
        }
    }

    // !!! following code taken from internet !!!
    // used to diff two files programatically in java
    public static long filesCompareByLine(Path path1, Path path2) throws IOException {
        try (BufferedReader bf1 = Files.newBufferedReader(path1);
                BufferedReader bf2 = Files.newBufferedReader(path2)) {

            long lineNumber = 1;
            String line1 = "", line2 = "";
            while ((line1 = bf1.readLine()) != null) {
                line2 = bf2.readLine();
                if (line2 == null || !line1.equals(line2)) {
                    return lineNumber;
                }
                lineNumber++;
            }
            if (bf2.readLine() == null) {
                return -1;
            } else {
                return lineNumber;
            }
        }
    }
}
