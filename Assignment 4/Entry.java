public class Entry {
    public int x, y;

    public Entry (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
         final int prime = 31;
         int result = 1;
         result = prime * result + x;
         result = prime * result + y;
         return result;
     }
 }
