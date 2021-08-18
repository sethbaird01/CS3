import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    
    private List<ItemOrder> list;

    public ShoppingCart(){
        this.list = new ArrayList<ItemOrder>();
    }

    public void add(ItemOrder newOrder){
        boolean orderFound = false;
        for (int i = 0; i < list.size(); i++) {
            if(this.list.get(i).equals(newOrder)){//order is present in list, replace it
                orderFound = true;
                this.list.set(i, newOrder);
            }
        }

        if(!orderFound){//order does not exist in list, append
        this.list.add(newOrder);
        }
    }

    public double getTotal(){

        double temp = 0;

        for (int i = 0; i < list.size(); i++) {
            temp += this.list.get(i).getPrice();
        }

        return temp;
    }


}
