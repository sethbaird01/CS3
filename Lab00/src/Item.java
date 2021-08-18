public class Item {

    private String name;
    private double price;
    private int bulkQty;
    private double bulkPrice;

    public Item(String name, double price){
        this.name = name;
        this.price = price;
        this.bulkQty = Integer.MAX_VALUE; //bulk price will never happen
        this.bulkPrice = price; //unused but set to price to avoid nulls
    }

    public Item(String name, double price, int bulkQty, double bulkPrice){
        this(name, price);
        this.bulkQty = bulkQty;
        this.bulkPrice = bulkPrice;
    }

    public String getName() {
        return this.name;
    }

    public double priceFor(int quantity){
        if(quantity < 1){
            System.out.println("exception: "+quantity);
            throw new IllegalArgumentException();
        }

        if(quantity >= this.bulkQty){//enough for bulk
            return quantity*bulkPrice;
        }
        return quantity*price;
    }

    public String toString(){
        if(this.bulkQty != Integer.MAX_VALUE){
            return this.name + ", $" + this.price + " (" + this.bulkQty + " for " + this.bulkPrice + ")";
        }
        return this.name + ", $" + this.price;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this.name.equals(((Item) obj).getName())){
            return true;
        }
        return false;
    }
}
