import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) {
        Scooby scooby = new Scooby();
        try {
            scooby.run("data/scooby1.dat"); //working as expected
            System.out.println("----------");
            scooby.run("data/scooby2.dat"); //working as expected
        } catch (FileNotFoundException e) {
            System.out.println("File not found, terminating");
            System.exit(9); //error
        }

        // Play play = new Play();
        // try {
        //     play.run("data/play1.dat"); //TODO 
        //     play.run("data/play2.dat"); //TODO
        // } catch (FileNotFoundException e) {
        //     System.out.println("File not found, terminating");
        //     System.exit(9); //error
        // }

        
    }
}
