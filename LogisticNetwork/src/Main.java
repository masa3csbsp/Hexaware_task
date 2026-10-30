import java.util.List;

public class Main {

    public static void main(String[] args) {

        HubBST bst = new HubBST();

        // Given insertion order
        String[] hubs = {
            "HUB50", "HUB30", "HUB70",
            "HUB20", "HUB40", "HUB60", "HUB80"
        };

        // Insert hubs
        for (String hub : hubs) {
            bst.addHub(hub);
        }

        // Display sorted hub codes
        System.out.println("Hubs in sorted order:");
        bst.inorder(bst.getRoot());
        System.out.println("\n--- TASK 1: BST for Hub Codes ---");
        // Search operation
        String target = "HUB40";

        System.out.println("\n\nSearching for " + target);

        if (bst.search(bst.getRoot(), target)) {
            System.out.println("Hub found!");
        } else {
            System.out.println("Hub not found!");
        }

        // Calculate height
        System.out.println(
            "BST Height: " + bst.height(bst.getRoot())
        );
        
        // TASK 2: PARCEL TRACKING
        System.out.println("\n--- TASK 2: PARCEL TRACKING ---");

        ParcelTracker tracker = new ParcelTracker();

        tracker.scanParcel("TRK1001", "HUB50");
        tracker.scanParcel("TRK1002", "HUB30");
        tracker.scanParcel("TRK1001", "HUB50");
        tracker.scanParcel("TRK1001", "HUB70");
        tracker.scanParcel("TRK1002", "HUB30");

        tracker.displayTracking();

        
        // TASK 3: LOGISTICS NETWORK
        System.out.println("\n--- TASK 3: LOGISTICS NETWORK ---");

        LogisticsGraph logistics = new LogisticsGraph();

        // Add 10 hubs
        for (int i = 1; i <= 10; i++) {
            logistics.addHub("HUB" + i);
        }

        // Add direct routes
        logistics.addRoute("HUB1", "HUB2");
        logistics.addRoute("HUB1", "HUB3");

        logistics.addRoute("HUB2", "HUB4");
        logistics.addRoute("HUB2", "HUB5");

        logistics.addRoute("HUB3", "HUB6");
        logistics.addRoute("HUB3", "HUB7");

        logistics.addRoute("HUB4", "HUB8");
        logistics.addRoute("HUB5", "HUB8");

        logistics.addRoute("HUB6", "HUB9");
        logistics.addRoute("HUB7", "HUB9");

        logistics.addRoute("HUB8", "HUB10");
        logistics.addRoute("HUB9", "HUB10");

        // Display graph
        logistics.displayGraph();

        // BFS: Shortest route
        System.out.println("\n--- BFS SHORTEST ROUTE ---");

        List<String> route =
            logistics.bfs("HUB1", "HUB10");

        if (route.isEmpty()) {
            System.out.println("No route available.");
        } else {
            System.out.println("Route: " + route);
            System.out.println(
                "Minimum hops: " + (route.size() - 1)
            );
        }

        // DFS: Reachable hubs from central depot
        logistics.dfs("HUB1");


        
        // TASK 4: SLIDING WINDOW
        System.out.println("\n--- TASK 4: DELIVERY WINDOW ANALYSIS ---");

        int[] hourlyParcels = {
            120, 150, 180, 200, 250,
            300, 170, 140, 100, 90
        };

        int windowSize = 4;

        SlidingWindow.findHighestAverage(
            hourlyParcels, windowSize
        );

        System.out.println("\n--- TASK 5: PALINDROME ROUTE CHECK ---");

String route1 = "HUB1 -> HUB2 -> HUB3 -> HUB2 -> HUB1";
String route2 = "HUB1 -> HUB2 -> HUB3";

System.out.println("Route 1: " + route1);

if (PalindromeChecker.isPalindrome(route1)) {
    System.out.println("Result: Palindrome route");
} else {
    System.out.println("Result: Not a palindrome route");
}

System.out.println("\nRoute 2: " + route2);

if (PalindromeChecker.isPalindrome(route2)) {
    System.out.println("Result: Palindrome route");
} else {
    System.out.println("Result: Not a palindrome route");
}
    }
}
