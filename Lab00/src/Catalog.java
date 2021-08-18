import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private String name;
    private List<Item> list;

    public Catalog(String name){
        this.list = new ArrayList<Item>();
        this.name = name;
    }

    public void add(Item item){
        this.list.add(item);
    }

    public int size(){
        return this.list.size();
    }

    public Item get(int index){
        return (Item) this.list.get(index);
    }

    public String getName(){
        return this.name;
    }

}
