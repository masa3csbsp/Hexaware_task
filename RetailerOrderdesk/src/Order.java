
public class Order {
    int orderId;
    String customerId;
    double amount;
    String createdAt;

    public Order(int orderId, String customerId,
                 double amount, String createdAt) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Amount: Rs." + amount);
        System.out.println("Created At: " + createdAt);
    }
}
