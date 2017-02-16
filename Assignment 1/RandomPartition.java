import java.util.Arrays;

public class RandomPartition {

    private class PivotData {
        int numOfPivots;
        int posFirst;
        int pivotValue;

        public PivotData (int pivotValue, int numOfPivots, int posFirst) {
            this.numOfPivots = numOfPivots;
            this.posFirst = posFirst;
            this.pivotValue = pivotValue;
        }
    }

    public static int kSmallest (int[] arr, int k) {
        return kSmallest(arr, k, 0, arr.length - 1);
    }

    private static int kSmallest (int[] arr, int k, int start, int end) {

        PivotData pd = randomPartition(arr, start, end-1);

        if (k >= pd.posFirst && k <= pd.posFirst + pd.numOfPivots) return pd.pivotValue;
        else if (k > pd.posFirst + pd.numOfPivots)
            return kSmallest(arr, k- (pd.posFirst+pd.numOfPivots), pd.posFirst+pd.numOfPivots + 1, end);
        else
            return kSmallest(arr, k, start, pd.posFirst - 1);


    }

    private static PivotData randomPartition (int[] arr, int start, int end) {
        int pivotIndex = (int) ( Math.random() * (end - start) ) + start;
        exchange(arr, pivotIndex, end);
        return partition(arr, start, end);
    }

	private static int partition (int[] arr, int start, int end) {
        int pivot = arr[end];
        if (isSmallest(arr, pivot, start, end)) {
            exchange(arr, start, end);
            return start;
        }

        int i = start, j = end-1;

        while (i <= j) {
            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
                exchange(arr, i, j);
                i++; j--;
            }
        }

        exchange(arr, i, end);
        return joinPivots(arr, i, start, end);
	}

    /**
    * @param pivot is the position of the pivot
    */
    private static PivotData joinPivots (int[] arr, int pivot, int start, int end) {

        for (int i = pivot - 1; i >= start; i--)
            if (arr[i] == arr[pivot]) bubbleUp(arr, i);

        for (int j = pivot + 1; j <= end; j++)
            if (arr[j] == arr[pivot]) bubbleDown(arr, j);


        int count = 0, pos = pivot;
        for (int i = start; i < end; i++) {
            if(arr[i] == arr[pivot]){
                count++;
                pos = i;
            }
        }

        PivotData pd = new PivotData(arr[pivot], count, pos-count + 1);
        return pd;
    }

	private static void exchange (int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

    /**
     * will bubble element at position idx until it finds another with the same value
     */
    private static void bubbleUp (int[] arr, int idx) {
        while (arr[idx+1] != arr[idx] && idx != arr.length - 1) {
            exchange(arr, idx, idx+1);
            idx++;
        }
    }

    private static void bubbleDown (int [] arr, int idx) {
        while (arr[idx-1] != arr[idx] && idx != 0) {
            exchange(arr, idx, idx-1);
            idx--;
        }
    }

    /**
     * @return if pivot is the smallest element in range start, end
     */
    private static boolean isSmallest(int[] arr, int pivot, int start, int end) {
        for (int i = start; i < end; i ++) {
            if (arr[i] < pivot) return false;
        }
        return true;
    }

	public static void main (String[] args) {
		int[] arr = new int[] {43,2,5,63,11,3,54,23,45,33};
		System.out.println(Arrays.toString(arr));
        System.out.println(kSmallest(arr,3));

	}

}
