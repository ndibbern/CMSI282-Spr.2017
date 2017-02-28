import java.util.ArrayList;
import java.util.Hashtable;

public class SumoSolver {

    public static Hashtable<Entry, Bag> memo = new Hashtable<>();
    public static Bag getHeaviestBag(int totalMoney, ArrayList<Item> items) {

            Bag heaviestBag = new Bag(); // start with the empy Bag
            int moneyAvailable = totalMoney;
            int numProducts = items.size();

            //First check if you have 0 money or 0 items, then the bag is empty
            if (moneyAvailable == 0 || numProducts == 0) {
                return heaviestBag;
             }

            Entry thisBag = new Entry(numProducts, moneyAvailable);
            Item currentItem = items.get(numProducts - 1);

            if (memo.get(thisBag) != null) {
                 return memo.get(thisBag); //already computed this value
            } else {
                if (moneyAvailable >= currentItem.cost) {
                    heaviestBag = new Bag(getHeaviestBag(moneyAvailable - currentItem.cost, items));
                    heaviestBag.add(currentItem);
                }
                // check id this solution is better than the one above it
                Item lastItem = items.get(numProducts - 1);
                items.remove(numProducts - 1);
                Bag bagFromAbove = new Bag(getHeaviestBag(moneyAvailable, items));
                items.add(lastItem);
                heaviestBag = Bag.getHeaviest(bagFromAbove, heaviestBag);
                memo.put(thisBag, heaviestBag);
            }
            return heaviestBag;
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
                    int thisCost = Integer.parseInt(args[2 * i + 1]);

                    items.add(new Item(thisWeight, thisCost));
                    if (thisWeight < 0 || thisCost < 0) {
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
             Bag myBag = getHeaviestBag(totalMoney, items);
             System.out.println(" ");
             for (Item i : myBag.getItems()) {
                 System.out.println(i);
             }
             System.out.println(myBag.getItemsCount() + " items/$" + myBag.cost + "/"+ myBag.weight + " pounds\n");
         }
    }
}
