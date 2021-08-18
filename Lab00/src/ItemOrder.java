public class ItemOrder {
    private Item item;
    private int qty;

    public ItemOrder(Item item, int qty){
        this.item = item;
        this.qty = qty;
    }

    public double getPrice(){
        return this.item.priceFor(this.qty);
    }

    public Item getItem() {
        return this.item;
    }

    public boolean equals(Object obj){
        if(this.item.equals(((ItemOrder) obj).getItem())){
            return true;
        }
        return false;
    }
}
