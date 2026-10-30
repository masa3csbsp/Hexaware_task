
public class SlidingWindow {

    public static void findHighestAverage(
            int[] parcels, int windowSize) {

        if (windowSize <= 0 || windowSize > parcels.length) {
            System.out.println("Invalid window size.");
            return;
        }

        // Calculate the first window sum
        int currentSum = 0;

        for (int i = 0; i < windowSize; i++) {
            currentSum += parcels[i];
        }

        int maxSum = currentSum;
        int maxStart = 0;

        // Slide the window
        for (int i = windowSize; i < parcels.length; i++) {

            // Remove the outgoing element
            currentSum -= parcels[i - windowSize];

            // Add the incoming element
            currentSum += parcels[i];

            // Update maximum sum
            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxStart = i - windowSize + 1;
            }
        }

        double maxAverage = (double) maxSum / windowSize;

        System.out.println("\n--- SLIDING WINDOW RESULT ---");

        System.out.println(
            "Window starts at hour index: " + maxStart
        );

        System.out.println(
            "Window ends at hour index: " +
            (maxStart + windowSize - 1)
        );

        System.out.println("Maximum parcel count: " + maxSum);

        System.out.println(
            "Highest 4-hour average: " + maxAverage
        );
    }
}
