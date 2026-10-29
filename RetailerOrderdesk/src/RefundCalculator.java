
import java.util.Arrays;

public class RefundCalculator {

    // Greedy Algorithm
    public static int greedyChange(int amount, int[] coins) {
        Arrays.sort(coins);
        int count = 0;

        // Use the largest denomination first
        for (int i = coins.length - 1; i >= 0; i--) {
            count += amount / coins[i];
            amount %= coins[i];
        }

        if (amount != 0) {
            return -1; // Exact change is impossible
        }

        return count;
    }

    // Dynamic Programming
    public static int dpChange(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        // Initialize all amounts as unreachable
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int current = 1; current <= amount; current++) {
            for (int coin : coins) {
                if (coin <= current) {
                    dp[current] = Math.min(
                        dp[current],
                        dp[current - coin] + 1
                    );
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] indianCoins = {1, 2, 5, 10, 20, 50, 100, 200, 500};
        int amount = 786;

        System.out.println("--- INDIAN CURRENCY ---");
        System.out.println("Refund amount: Rs." + amount);
        System.out.println("Greedy result: "
                + greedyChange(amount, indianCoins.clone()));
        System.out.println("DP result: "
                + dpChange(amount, indianCoins));

        // Demonstrate where greedy is not optimal
        int[] testCoins = {1, 3, 4};
        int testAmount = 6;

        System.out.println("\n--- GREEDY VS DP EXAMPLE ---");
        System.out.println("Denominations: 1, 3, 4");
        System.out.println("Amount: " + testAmount);
        System.out.println("Greedy result: "
                + greedyChange(testAmount, testCoins.clone()));
        System.out.println("DP result: "
                + dpChange(testAmount, testCoins));
    }
}
