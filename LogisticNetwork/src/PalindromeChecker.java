import java.util.ArrayList;
import java.util.List;

public class PalindromeChecker {

    public static boolean isPalindrome(String route) {

        // Split the route using arrows, commas, semicolons, or pipes
        String[] hubs = route.split("\\s*(?:->|[,;|])\\s*|\\s+");

        List<String> hubList = new ArrayList<>();

        // Remove empty values and store hub codes
        for (String hub : hubs) {
            if (!hub.trim().isEmpty()) {
                hubList.add(hub.trim());
            }
        }

        // Two pointers
        int left = 0;
        int right = hubList.size() - 1;

        while (left < right) {

            // Compare hub codes without considering case
            if (!hubList.get(left).equalsIgnoreCase(hubList.get(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}