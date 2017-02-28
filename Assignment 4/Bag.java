import java.util.ArrayList;

public class Bag {

    private ArrayList<Item> items;
    public int cost, weight;

    public Bag () {
        this.items = new ArrayList<Item>();
        this.cost = 0;
        this.weight = 0;
    }

    public Bag (Bag toClone) {
        this.items = new ArrayList<>(toClone.getItems());
        this.cost = toClone.cost;
        this.weight = toClone.weight;
    }

    public void add (Item item) {
        items.add(item);
        weight += item.weight;
        cost += item.cost;
    }

    public int getItemsCount () {
        return this.items.size();
    }

    public static Bag getHeaviest (Bag c1, Bag c2) {
        return c1.weight > c2.weight ? c1 : c2;
    }


    public ArrayList<Item> getItems () {
        return this.items;
    }

}
