
import java.io.*;
import java.util.*;

public class Main {

    // Read orders from CSV
    static Order[] loadOrders(String fileName) throws IOException {
        List<Order> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))) {

            br.readLine(); // Skip header
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                list.add(new Order(
                    Integer.parseInt(data[0]),
                    data[1],
                    Double.parseDouble(data[2]),
                    data[3]
                ));
            }
        }

        return list.toArray(new Order[0]);
    }

    // Linear Search
    static int linearSearch(Order[] orders, int target) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].orderId == target) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search: requires orders sorted by orderId
    static int binarySearch(Order[] orders, int target) {
        int left = 0, right = orders.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (orders[mid].orderId == target) return mid;
            if (orders[mid].orderId < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Display sorted orders
    static void displayOrders(Order[] orders) {
        System.out.printf("%-10s %-12s %-12s%n",
                          "Order ID", "Customer", "Amount");

        for (Order order : orders) {
            System.out.printf("%-10d %-12s Rs.%-10.2f%n",
                    order.orderId, order.customerId, order.amount);
        }
    }

    public static void main(String[] args) {
        try {
            Order[] orders = loadOrders("orders.csv");
            System.out.println("Orders loaded: " + orders.length);

            // TASK 1: Searching
            int target = 1015;

            long start = System.nanoTime();
            int linearResult = linearSearch(orders, target);
            long linearTime = System.nanoTime() - start;

            Order[] sortedById = orders.clone();
            Arrays.sort(sortedById,
                Comparator.comparingInt(o -> o.orderId));

            start = System.nanoTime();
            int binaryResult = binarySearch(sortedById, target);
            long binaryTime = System.nanoTime() - start;

            System.out.println("\n--- TASK 1: SEARCHING ---");
            System.out.println("Linear Search: " +
                (linearResult >= 0 ? "Found" : "Not found"));
            System.out.println("Linear Time: " + linearTime + " ns");

            System.out.println("Binary Search: " +
                (binaryResult >= 0 ? "Found" : "Not found"));
            System.out.println("Binary Time: " + binaryTime + " ns");

            // TASK 2: SORTING
            Order[] selectionOrders = orders.clone();
            Order[] mergeOrders = orders.clone();

            start = System.nanoTime();
            SortAlgorithms.selectionSort(selectionOrders);
            long selectionTime = System.nanoTime() - start;

            start = System.nanoTime();
            SortAlgorithms.mergeSort(mergeOrders);
            long mergeTime = System.nanoTime() - start;

            System.out.println("\n--- TASK 2: SELECTION SORT ---");
            displayOrders(selectionOrders);
            System.out.println("Time: " + selectionTime + " ns");

            System.out.println("\n--- TASK 2: MERGE SORT ---");
            displayOrders(mergeOrders);
            System.out.println("Time: " + mergeTime + " ns");

            // TASK 3: Duplicate Detection
            System.out.println();
            DuplicateDetector.detectDuplicates(orders);

            
            // TASK 4: Stack and Queue
            System.out.println("\n--- TASK 4: STACK AND QUEUE ---");

            OrderDesk desk = new OrderDesk();

            // Demonstrate Stack: edit and undo
            Order firstOrder = orders[0];

            System.out.println("\nOriginal amount: Rs."
                    + firstOrder.amount);

            desk.editOrder(firstOrder, 9999.00);
            desk.undoLastEdit(orders);

            // Demonstrate Queue: warehouse dispatch
            System.out.println();

            desk.addToDispatch(orders[0].orderId);
            desk.addToDispatch(orders[1].orderId);
            desk.addToDispatch(orders[2].orderId);

            desk.dispatchOrders();


            
            // TASK 5: Refund Calculation
            System.out.println("\n--- TASK 5: REFUND CALCULATION ---");

            int refundAmount = 786;

            int[] denominations = {
                1, 2, 5, 10, 20, 50, 100, 200, 500
            };

            int greedyResult = RefundCalculator.greedyChange(
                refundAmount, denominations.clone()
            );

            int dpResult = RefundCalculator.dpChange(
                refundAmount, denominations
            );

            System.out.println("Refund amount: Rs." + refundAmount);
            System.out.println("Greedy minimum coins/notes: "
                    + greedyResult);
            System.out.println("DP minimum coins/notes: "
                    + dpResult);


        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Invalid data: " + e.getMessage());
        }
    }
}
