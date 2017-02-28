public class Item {

    private static final String DEFAULT_LABEL = "unlabled item";
    public int price, weight;
    public String label;

    public Item (int price, int weight) {
        this.price = price;
        this.weight = weight;
        this.label = DEFAULT_LABEL;
    }

    public String changeLabel (String newLabel) {
        this.label = newLabel;
        return label;
    }

    public String getLabel () {
       return this.label;
    }

    public String toString () {
        return "$" + this.price + "/" + this.weight + " pounds";
    }
}
