import java.util.Arrays;

public class Ksmallest {

    private static void swap (int[] arr, int i, int j) {
        if (i >= arr.length || j >= arr.length || i < 0 || j < 0) return;

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static int random (int min, int max) {
        return min + (int) (Math.random() * (max - min));
    }

    private static int randomPartition (int[] arr, int l, int r) {
        int i = l, j = r;
        int pivotIdx = random(l, r);
        int pivot = arr[pivotIdx];
        //swap(arr, l + (r-l)/2, pivotIdx);
        System.out.println(Arrays.toString(arr));

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                swap(arr, i, j);
                System.out.println(Arrays.toString(arr));
                System.out.println(i);
                System.out.println(j);
                i++;
                j--;
            }
        }

        int newIdx = 0;
        int smallestGrater = 0;
        for (int k = 0; k < arr.length; k++) {
            if (arr[k] > pivot && smallestGrater == 0) smallestGrater = k;
            if (arr[k] == pivot) newIdx = k;
        }
        swap(arr, smallestGrater, newIdx);
        return smallestGrater;
    }









    public static void main (String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        swap(arr, 1, 3);
        //System.out.println(Arrays.toString(arr));

        int a = random(1, 15);

        //System.out.println(a);

        int[] arr2 = new int[] {3,44,2,60,7,14,80,90,1,21};
        //System.out.println(Arrays.toString(arr2));
        System.out.println(randomPartition(arr2,0,arr2.length-1));
        System.out.println(Arrays.toString(arr2));
    }




}
