
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class OrderDesk {

    // Stores previous order amounts for undo
    private final Deque<Edit> undoStack = new ArrayDeque<>();

    // Stores order IDs waiting for dispatch
    private final Queue<Integer> dispatchQueue = new ArrayDeque<>();

    // Represents one edit to an order
    private static class Edit {
        int orderId;
        double previousAmount;

        Edit(int orderId, double previousAmount) {
            this.orderId = orderId;
            this.previousAmount = previousAmount;
        }
    }

    // Edit an order and save its previous amount
    public void editOrder(Order order, double newAmount) {
        undoStack.push(new Edit(order.orderId, order.amount));

        order.amount = newAmount;

        System.out.println("Order " + order.orderId
                + " updated to Rs." + order.amount);
    }

    // Undo the most recent edit
    public void undoLastEdit(Order[] orders) {
        if (undoStack.isEmpty()) {
            System.out.println("No edits to undo.");
            return;
        }

        Edit edit = undoStack.pop();

        for (Order order : orders) {
            if (order.orderId == edit.orderId) {
                order.amount = edit.previousAmount;

                System.out.println("Undo successful for order "
                        + order.orderId);
                System.out.println("Restored amount: Rs."
                        + order.amount);
                return;
            }
        }

        System.out.println("Order not found.");
    }

    // Add an order to the dispatch queue
    public void addToDispatch(int orderId) {
        dispatchQueue.offer(orderId);
        System.out.println("Order " + orderId
                + " added to dispatch queue.");
    }

    // Dispatch orders in FIFO order
    public void dispatchOrders() {
        System.out.println("\n--- WAREHOUSE DISPATCH ---");

        while (!dispatchQueue.isEmpty()) {
            int orderId = dispatchQueue.poll();
            System.out.println("Dispatching order: " + orderId);
        }
    }
}
