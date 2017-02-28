
public class Item {

    private static final String DEFAULT_LABEL = "unlabled item";
    private int cost;
    private int weight;
    public String label;

    public Item (int cost, int weight) {
        this.cost = cost;
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
    public int getCost () {
        return this.cost;
    }

    public int getWeight () {
        return this.weight;
    }

    public String toString () {
        return "$" + this.cost + "/" + this.weight + " pounds";
    }
}
