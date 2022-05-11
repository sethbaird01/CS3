import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) {

        System.out.println("Scooby");
        Scooby scooby = new Scooby();
        try {
            scooby.run("data/scooby1.dat"); // working as expected
            System.out.println("----------");
            scooby.run("data/scooby2.dat"); // working as expected
        } catch (FileNotFoundException e) {
            System.out.println("File not found, terminating");
            System.exit(9); // error
        }

        System.out.println("\nPlay");
        Play play = new Play();
        try {
            play.run("data/play1.dat"); // working as expected
            System.out.println("----------");
            play.run("data/play2.dat"); // working as expected

        } catch (FileNotFoundException e) {
            System.out.println("File not found, terminating");
            System.exit(9); // error
        }

        System.out.println("\nMaze");
        Maze maze = new Maze();
        try {
            maze.run("data/maze.dat"); // working as expected

        } catch (FileNotFoundException e) {
            System.out.println("File not found, terminating");
            System.exit(9); // error
        }

        System.out.println("\nPrimePath");
        PrimePath path = new PrimePath();
        try {
            path.run("data/path1.dat"); // working as expected
            System.out.println("----------");
            path.run("data/path2.dat"); // working as expected
            System.out.println("----------");
            path.run("data/path3.dat"); // working as expected
        } catch (FileNotFoundException e) {
            System.out.println("File not found, terminating");
            System.exit(9); // error
        }

    }
}
