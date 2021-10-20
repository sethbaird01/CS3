import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Maze {

    private Square[][] arr;
    private Square start;
    private Square end;
    private String file;

    public Maze(){

    }

    public boolean loadMaze(String FileName){
        this.file = FileName;
        System.out.println("LOADING FILE");
        try{
        Scanner scan = new Scanner(Paths.get(FileName).toFile());

        int rows = scan.nextInt(); 
        int cols = scan.nextInt();

        this.arr = new Square[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int temp = scan.nextInt();
                arr[row][col] = new Square(row, col, temp);
                if(temp == 2){//start found
                    start = arr[row][col];
                }
                if(temp == 3){//end found
                    end = arr[row][col];
                }
            }
        }
    
        scan.close();
        return true;
        } catch (Exception e) {System.out.println("File not found");};
        return false;
    }

    List<Square> getNeighbors(Square s){
        List<Square> list = new ArrayList<Square>();
    try {
        list.add(this.arr[s.getRow()-1][s.getCol()]);
    } catch (Exception e) {}
    try {
        list.add(this.arr[s.getRow()][s.getCol()-1]);
    } catch (Exception e) {}
        //current
    try {
        list.add(this.arr[s.getRow()][s.getCol()+1]);
    } catch (Exception e) {}
    try {
        list.add(this.arr[s.getRow()+1][s.getCol()]);
    } catch (Exception e) {}
    //these are guaranteed to not be null

    return list;
    }

    Square getStart(){
        return start;
    }

    Square getExit() {
        return end;
    }

    void reset(){
        this.loadMaze(this.file);
    }

    public String toString(){
        String out = "";
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                out += this.arr[row][col]+ " ";
            }
            out += "\n";
        }
        return out;
    }

}
