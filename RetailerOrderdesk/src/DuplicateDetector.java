
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;

public class DuplicateDetector {

    public static void detectDuplicates(Order[] orders) {
        Set<String> seen = new HashSet<>();
        boolean found = false;

        System.out.println("--- DUPLICATE ORDER DETECTION ---");

        for (Order order : orders) {

            // Convert timestamp to the start of its minute
            LocalDateTime time =
                LocalDateTime.parse(order.createdAt);

            String minute = time
                .withSecond(0)
                .withNano(0)
                .toString();

            // Build a key from customer, amount, and minute
            String key = order.customerId + "|" +
                         order.amount + "|" + minute;

            if (!seen.add(key)) {
                System.out.println(
                    "Duplicate order found: " + order.orderId
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No duplicate orders found.");
        }
    }
}
