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

    private static double min (double[] arr) {
        double min = arr[0];
        for (double d : arr) {
            if (d < min) min = d;
        }
        return Math.floor(min);
    }

    private static double max (double[] arr) {
        double max = arr[0];
        for (double d : arr) {
            if (d > max) max = d;
        }
        return Math.ceil(max);
    }

    private static double range (double[] arr) {
        return max(arr) - min(arr);
    }

    public static ArrayList<Double> sort (double[] arr, int n) {
        ArrayList<ArrayList<Double>> buckets = new ArrayList<ArrayList<Double>>();

        double min = min(arr);
        double range = range(arr);


        for (int i = 0; i < n; i++) buckets.add(new ArrayList<Double>());
        for (int i = 0 ; i < n; i++) {
            double normalized = (arr[i] - min) / range;
            buckets.get((int) (normalized * n)).add(arr[i]);
        }

        ArrayList<Double> ans = new ArrayList<>();
        int index = 0;
        for (ArrayList<Double> bucket : buckets) {
            if (bucket.size() >= 2) insertionSort(bucket);
            for (Double d : bucket) {
                ans.add(index, d);
                index++;
            }
        }
        return ans;
    }

    private static void insertionSort(ArrayList<Double> list) {
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

    public static void main (String[] args) {

        FileReader fileReader = new FileReader();
        double[] array = fileReader.readNumbers();
        boolean shouldRun = array.length > 0;

        if (shouldRun) {
            ArrayList<Double> sortedDoubles = sort(array, array.length);
            for (int i = 0; i < sortedDoubles.size(); i++)
                System.out.println(sortedDoubles.get(i));
        } else {
            System.out.println("One or more of the double values in stdIn is not a double value");
        }

	}

}
