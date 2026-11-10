import java.util.Arrays;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class QuickSortMultiThreading extends RecursiveAction {
    private int[] arr;
    private int low, high;

    public QuickSortMultiThreading(int[] arr, int low, int high) {
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            QuickSortMultiThreading leftTask = new QuickSortMultiThreading(arr, low, pivotIndex - 1);
            QuickSortMultiThreading rightTask = new QuickSortMultiThreading(arr, pivotIndex + 1, high);
            
            invokeAll(leftTask, rightTask);
        }
    }

    private int partition(int[] a, int l, int h) {
        int pivot = a[h];
        int i = (l - 1);
        for (int j = l; j < h; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1];
        a[i + 1] = a[h];
        a[h] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] data = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
        System.out.println("Before sorting: " + Arrays.toString(data));
        
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new QuickSortMultiThreading(data, 0, data.length - 1));
        
        System.out.println("After multi-threaded sorting: " + Arrays.toString(data));
    }
}
