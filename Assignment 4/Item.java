public class Item {

    public int cost, weight;

    public Item (int cost, int weight) {
        this.cost = cost;
        this.weight = weight;
    }

    public String toString () {
        String toPrint = "$" + this.cost + "/" + this.weight + " pounds";
        return toPrint;
    }
}
