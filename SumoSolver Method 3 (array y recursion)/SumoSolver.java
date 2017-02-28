import java.util.Arrays;
public class SumoSolver {

        public static Cart heaviestCart(int totalMoney, Item[] items) {


            for (int i = 0; i < items.length; i++) {
                items[i].changeLabel( Integer.toString(i));
            }

            Cart[][] cartGrid = new Cart[items.length + 1][totalMoney + 1];
            for (int i = 0; i <= items.length; i++) {
                for (int j = 0; j <= totalMoney; j++) {
                    if (i == 0)
                        cartGrid[i][j] = new Cart();
                    else if (j == 0)
                        cartGrid[i][j] = new Cart();
                    else {
                        if (items[i - 1].getCost() <= j) {
                            int remaining = j - items[i - 1].getCost();
                            cartGrid[i][j] = new Cart(cartGrid[i][remaining]);
                            cartGrid[i][j].addItem(items[i-1]);
                            cartGrid[i][j] = Cart.getHeaviest(cartGrid[i][j], cartGrid[i - 1][j]);
                        } else {
                            cartGrid[i][j] = cartGrid[i - 1][j];
                        }
                    }
                }
            }
            //System.out.println(Arrays.deepToString(cartGrid));
            return cartGrid[items.length][totalMoney];
        }






    public static void main (String[] args) {
        if (args.length % 2 != 0){
            Item[] items = new Item[(args.length - 1)/2];
            int totalMoney = 0;
            try {
                totalMoney = Integer.parseInt(args[args.length - 1]);
                for (int i = 0; i < (args.length-1)/2; i++) {
                    items[i] = new Item(Integer.parseInt(args[2*i]), Integer.parseInt(args[2*i+1]));
                    //Check if it is negative (DO)
                }
            }catch (NumberFormatException ignore) {
                System.out.println("Supplied values must be integers.");
            }
            Cart myCart = heaviestCart(totalMoney, items);
            for (Item i : myCart.getItems())
                System.out.println(i);
            System.out.println(myCart.getItemsCount() + " items/$" + myCart.getTotalCost() + "/"+ myCart.getTotalWeight() + " pounds\n");

        } else System.out.println("One or more of the supplied dates is not valid");
    }
}
