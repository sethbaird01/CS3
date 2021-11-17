import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Dictionary {
    Scanner scan;
    HashSet<String> map;

    public Dictionary(File dictPath) throws FileNotFoundException{
        this.map = new HashSet<String>();
        this.scan = new Scanner(dictPath);
        
        while(scan.hasNext()){
            map.add(scan.next());
        }//map is full
        scan.close();

    }  

    public HashSet<String> getMap(){
        return this.map;
    }

    public boolean doesExist(String word){
        return map.contains(word);
    }
}
