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
                        bestBag = new Bag(); //FIX
                    } else {
                        bestBag.add(items.get(numProducts - 1));
                    }
                    // bestBag.add(items.get(numProducts - 1));
                }
                // check if this solution is better than the one above it
                Item lastItem = items.remove(numProducts - 1);
                //System.out.println(lastItem);
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
}
