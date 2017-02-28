import java.util.ArrayList;

public class SumoSolver {

    public static Bag[][] bagGrid;

    public static Bag getBestBag(int totalMoney, ArrayList<Item> items) {

            Bag bestBag = new Bag(); // start with the empty Bag
            int moneyAvailable = totalMoney;
            int numProducts = items.size();

            if (bagGrid[numProducts][moneyAvailable] != null) {
                 return bagGrid[numProducts][moneyAvailable]; //already computed this value
            } else {
                if (moneyAvailable == 0 || numProducts == 0)
                    return bestBag;

                if (moneyAvailable >= items.get(numProducts - 1).price) {
                    bestBag = new Bag(getBestBag(moneyAvailable - items.get(numProducts - 1).price, items));

                    if (bestBag.itemIsinBag(items.get(numProducts - 1))) {
                        // Only allowed to grab one item of each of the items available.
                        bestBag = new Bag();
                    } else {
                        bestBag.add(items.get(numProducts - 1));
                    }
                }
                // check if this solution is better than the one above it
                Item lastItem = items.remove(numProducts - 1);
                Bag bagFromAbove = new Bag(getBestBag(moneyAvailable, items));
                items.add(lastItem);
                bestBag = Bag.getBest(bagFromAbove, bestBag);

                if (moneyAvailable - bestBag.price >= items.get(numProducts - 1).price && !bestBag.itemIsinBag(items.get(numProducts - 1))) {
                    bestBag.add(items.get(numProducts - 1));
                }

            }
            bagGrid[numProducts][moneyAvailable] = bestBag;
            return bestBag;
    }

    public static void main (String[] args) {

        boolean negativeValues = false;
        boolean NaN = false;
        boolean notOdd = false;
        boolean shouldRun = true;

        int totalMoney = 0;
        ArrayList<Item> items = new ArrayList<Item>(args.length / 2);

        if (args.length % 2 != 0) {

            try {
                totalMoney = Integer.parseInt(args[args.length - 1]);
                for (int i = 0; i < (args.length-1)/2; i++) {

                    int thisWeight = Integer.parseInt(args[2 * i]);
                    int thisprice = Integer.parseInt(args[2 * i + 1]);

                    items.add(new Item(thisWeight, thisprice));
                    if (thisWeight < 0 || thisprice < 0) {
                        negativeValues = true;
                    }
                }
            } catch (NumberFormatException ignore) {
                NaN = true;
            }
        } else {
            notOdd = true;
        }

         if (NaN) {
             System.out.println("One or more of the supplied values is not valid");
             shouldRun = false;
         }
         if (notOdd){
             System.out.println("Please provide an odd number of arguments");
             shouldRun = false;
         }
         if (negativeValues){
             System.out.println("Supplied values must be positive integers");
             shouldRun = false;
         }
         if (shouldRun) {

             bagGrid = new Bag[items.size() + 1][totalMoney + 1];

             for (int i = 0; i < items.size(); i++) {
                 items.get(i).changeLabel( Integer.toString(i));
             }
             Bag myBag = getBestBag(totalMoney, items);
             System.out.println(" ");
             for (Item i : myBag.getItems()) {
                 System.out.println(i);
             }
             System.out.println(myBag.getItemsCount() + " items/$" + myBag.price + "/"+ myBag.weight + " pounds\n");
         }
    }

    private static class Bag {

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
            if (this.itemIsinBag(item)) return;
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

    private static class Item {

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


}
