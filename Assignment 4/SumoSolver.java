import java.util.ArrayList;
import java.util.Hashtable;

public class SumoSolver {

    public static Hashtable<Entry, Bag> memoization = new Hashtable<>();
    public static Bag getBestBag(int totalMoney, ArrayList<Item> items) {

            Bag bestBag = new Bag(); // start with the empy Bag
            int moneyAvailable = totalMoney;
            int numProducts = items.size();

            //Define unique label to all items
            for (int i = 0; i < numProducts; i++) {
                items.get(i).changeLabel( Integer.toString(i));
            }

            //Check if you have 0 money or 0 items, then the bag is empty
            if (moneyAvailable == 0 || numProducts == 0) {
                return bestBag;
             }

            Entry thisBag = new Entry(numProducts, moneyAvailable);

            if (memoization.get(thisBag) != null) {
                 return memoization.get(thisBag); //already computed this value
            } else {
                if (moneyAvailable >= items.get(numProducts - 1).price) {
                    bestBag = new Bag(getBestBag(moneyAvailable - items.get(numProducts - 1).price, items));

                    if (bestBag.itemIsinBag(items.get(numProducts - 1))) {
                    // Only allowed to grab one item of each of the items available.
                    bestBag = new Bag(getBestBag(moneyAvailable - 1, items));
                    } else {
                        bestBag.add(items.get(numProducts - 1));
                    }
                }
                // check if this solution is better than the one above it
                Item lastItem = items.get(numProducts - 1);
                items.remove(numProducts - 1);
                Bag bagFromAbove = new Bag(getBestBag(moneyAvailable, items));
                items.add(lastItem);
                bestBag = Bag.getBest(bagFromAbove, bestBag);
            }
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
             Bag myBag = getBestBag(totalMoney, items);
             System.out.println(" ");
             for (Item i : myBag.getItems()) {
                 System.out.println(i);
             }
             System.out.println(myBag.getItemsCount() + " items/$" + myBag.price + "/"+ myBag.weight + " pounds\n");
         }
    }
}
