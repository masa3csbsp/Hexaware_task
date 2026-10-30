
import java.util.*;

public class LogisticsGraph {

    // Adjacency List
    private Map<String, List<String>> graph =
            new LinkedHashMap<>();

    // Add a hub
    public void addHub(String hub) {
        graph.putIfAbsent(hub, new ArrayList<>());
    }

    // Add an undirected route
    public void addRoute(String hub1, String hub2) {
        addHub(hub1);
        addHub(hub2);

        graph.get(hub1).add(hub2);
        graph.get(hub2).add(hub1);
    }

    // Display adjacency list
    public void displayGraph() {
        System.out.println("\n--- ADJACENCY LIST ---");

        for (String hub : graph.keySet()) {
            System.out.println(
                hub + " -> " + graph.get(hub)
            );
        }
    }

    // BFS: Find shortest route
    public List<String> bfs(String source, String destination) {

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        Map<String, String> parent = new HashMap<>();

        if (!graph.containsKey(source) ||
            !graph.containsKey(destination)) {
            return new ArrayList<>();
        }

        queue.offer(source);
        visited.add(source);
        parent.put(source, null);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            if (current.equals(destination)) {
                break;
            }

            for (String neighbor : graph.get(current)) {

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        // Reconstruct shortest path
        if (!visited.contains(destination)) {
            return new ArrayList<>();
        }

        List<String> path = new ArrayList<>();

        String current = destination;

        while (current != null) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);

        return path;
    }

    // DFS: Find all reachable hubs
    public void dfs(String start) {

        if (!graph.containsKey(start)) {
            System.out.println("Hub not found!");
            return;
        }

        Set<String> visited = new LinkedHashSet<>();

        System.out.println("\n--- DFS REACHABLE HUBS ---");

        dfsTraversal(start, visited);

        System.out.println("\nReachable hubs: " + visited);
    }

    // Recursive DFS
    private void dfsTraversal(
            String current, Set<String> visited) {

        visited.add(current);

        System.out.print(current + " ");

        for (String neighbor : graph.get(current)) {

            if (!visited.contains(neighbor)) {
                dfsTraversal(neighbor, visited);
            }
        }
    }
}