import java.util.ArrayList;

public class Bag {

    private ArrayList<Item> items;
    public int price, weight;

    public Bag () {
        this.items = new ArrayList<Item>();
        this.price = 0;
        this.weight = 0;
    }

    public Bag (Bag toClone) {
        this.items = new ArrayList<>(toClone.getItems());
        this.price = toClone.price;
        this.weight = toClone.weight;
    }

    public void add (Item item) {
        items.add(item);
        weight += item.weight;
        price += item.price;
    }

    public int getItemsCount () {
        return this.items.size();
    }

    public static Bag getBest (Bag c1, Bag c2) {
        return c1.weight > c2.weight ? c1 : c2;
    }


    public ArrayList<Item> getItems () {
        return this.items;
    }

    public boolean itemIsinBag (Item item) {
       for (Item i : this.items) {
           if (i.getLabel() == item.getLabel()) {
               return true;
           }
       }
       return false;
   }
}
