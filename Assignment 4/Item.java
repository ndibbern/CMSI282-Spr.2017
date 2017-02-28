public class Item {

    private static final String DEFAULT_LABEL = "unlabled item";
    public int cost, weight;
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

    public String toString () {
        String toPrint = "$" + this.cost + "/" + this.weight + " pounds";
        return toPrint;
    }
}
