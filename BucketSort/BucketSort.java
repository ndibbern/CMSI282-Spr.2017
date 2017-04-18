import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

public class BucketSort {

     private static class FileReader {

         private java.io.BufferedReader stdIn;

         public FileReader () {
             stdIn = new java.io.BufferedReader (new java.io.InputStreamReader(System.in));
         }
         /**
          * @return a double array holding the doubles found in stdIn, or an empty array if there is a problem
          */
         public double[] readNumbers () {
             ArrayList<Double> numbers = new ArrayList<>();
             String line = null;
             try {
                 while ((line  = stdIn.readLine()) != null) {
                     double toAdd = readNumber(line);
                     if (toAdd < Double.MAX_VALUE) numbers.add(toAdd);
                     else return new double[] {};
                 }
             } catch (IOException e) {
                 System.out.println("cannot read file ");
             }
             return convertDoubles(numbers);
         }

         /**
          * @return the integer value of the string, or MAX_VALLUE in case the string is not a number
          */
         private double readNumber (String line) {
             try {
                 return Double.parseDouble(line);
             } catch (NumberFormatException nfe) {
                 return Double.MAX_VALUE;
             }
         }

         private double[] convertDoubles(ArrayList<Double> doubles) {
             doubles.trimToSize();
             double[] ret = new double[doubles.size()];
             Iterator<Double> iterator = doubles.iterator();
             for (int i = 0; i < ret.length; i++)
                 ret[i] = iterator.next().doubleValue();
             return ret;
         }

     }

     public static ArrayList<Double> sort (double arr[], int n) {
          ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>(10);

          for (int i = 0; i < n; i ++) {
              buckets.add(new ArrayList<Double>());
          }

          // Put array elements in different buckets
          for (int i = 0 ; i < n; i++) {
             int index = (int) (arr[i] * n);
             buckets.get(index).add(arr[i]);
          }

          // Sort individual buckets and add to final list
          ArrayList<Double> ans = new ArrayList<>(n);
          int i = 0;

          for (ArrayList<Double> bucket : buckets) {
              if (bucket.size() >= 2) {
                  insertionSort(bucket);
              }
              for (Double d : bucket) {
                  ans.add(i++, d);
              }
          }
          return ans;
    }

    public static void insertionSort(ArrayList<Double> list) {
        double temp;
        int previousIndex;

        for (int index = 1; index < list.size(); index++) {
            temp = list.get(index);
            previousIndex = index - 1;
            while (previousIndex >= 0 && (list.get(previousIndex) > temp)) {
                list.set((previousIndex + 1), list.get(previousIndex));
                previousIndex -= 1;
            }
            if (previousIndex >= 0 && list.get(previousIndex) > temp) {
                list.set((previousIndex + 1), list.get(previousIndex));
                list.set((previousIndex + 1), temp);
            } else {
                list.set((previousIndex + 1), temp);
            }
        }
    }

    private static String listDoubles (ArrayList<Double> arr) {
       StringBuilder ans = new StringBuilder();
       for (Double d : arr) {
           ans.append(d);
           ans.append("\n");
       }
       return ans.toString();
   }

    public static void main (String[] args) {

        FileReader fr = new FileReader();
        double[] numbers = fr.readNumbers();
        boolean goodList = numbers.length > 0;

        if (goodList) {
            ArrayList<Double> sortedDoubles = sort(numbers, numbers.length);
           System.out.println(listDoubles(sortedDoubles));
        } else {
            System.out.println("BAD DATA");
        }

	}

}
