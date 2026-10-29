
import java.util.Arrays;

public class SortAlgorithms {

    // Selection Sort: highest amount first
    public static void selectionSort(Order[] orders) {
        for (int i = 0; i < orders.length - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < orders.length; j++) {
                if (orders[j].amount > orders[maxIndex].amount) {
                    maxIndex = j;
                }
            }

            Order temp = orders[i];
            orders[i] = orders[maxIndex];
            orders[maxIndex] = temp;
        }
    }

    // Merge Sort: highest amount first
    public static void mergeSort(Order[] orders) {
        if (orders.length <= 1) {
            return;
        }

        int mid = orders.length / 2;

        Order[] left = Arrays.copyOfRange(orders, 0, mid);
        Order[] right = Arrays.copyOfRange(
            orders, mid, orders.length
        );

        mergeSort(left);
        mergeSort(right);

        merge(orders, left, right);
    }

    private static void merge(
            Order[] result, Order[] left, Order[] right) {

        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].amount >= right[j].amount) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }
}
