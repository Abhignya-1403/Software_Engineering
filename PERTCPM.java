import java.util.*;

class Activity {
    int startNode;
    int endNode;
    int to, tm, tp;
    double te;

    public Activity(int startNode, int endNode, int to, int tm, int tp) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.to = to;
        this.tm = tm;
        this.tp = tp;
        this.te = (to + 4 * tm + tp) / 6.0;
    }
}

public class PERTCPM {
    private static Map<Integer, List<Activity>> graph = new HashMap<>();
    private static Map<Integer, Double> earliestTimes = new HashMap<>();
    private static Map<Integer, Double> latestTimes = new HashMap<>();

    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>();

        // Input: Define activities
        activities.add(new Activity(1, 2, 1, 2, 3));
        activities.add(new Activity(1, 3, 3, 4, 5));
        activities.add(new Activity(1, 4, 1, 2, 4));
        activities.add(new Activity(1, 5, 2, 4, 5));
        activities.add(new Activity(2, 4, 6, 7, 8));
        activities.add(new Activity(2, 5, 10, 12, 15));
        activities.add(new Activity(3, 5, 1, 2, 4));
        activities.add(new Activity(4, 5, 2, 3, 5));

        // Build graph
        for (Activity activity : activities) {
            graph.computeIfAbsent(activity.startNode, k -> new ArrayList<>()).add(activity);
        }

        // Initialize earliest times for start node
        earliestTimes.put(1, 0.0);

        // Topological sort and calculate earliest times
        List<Integer> sortedNodes = topologicalSort();
        calculateEarliestTimes(sortedNodes);

        // Calculate latest times
        calculateLatestTimes(sortedNodes);

        // Find and print the critical path
        findAndPrintCriticalPath(activities);
    }

    private static List<Integer> topologicalSort() {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                topologicalSortUtil(node, visited, stack);
            }
        }

        List<Integer> sortedNodes = new ArrayList<>();
        while (!stack.isEmpty()) {
            sortedNodes.add(stack.pop());
        }
        return sortedNodes;
    }

    private static void topologicalSortUtil(int node, Set<Integer> visited, Stack<Integer> stack) {
        visited.add(node);
        if (graph.containsKey(node)) {
            for (Activity activity : graph.get(node)) {
                if (!visited.contains(activity.endNode)) {
                    topologicalSortUtil(activity.endNode, visited, stack);
                }
            }
        }
        stack.push(node);
    }

    private static void calculateEarliestTimes(List<Integer> sortedNodes) {
        for (int node : sortedNodes) {
            double maxTime = earliestTimes.getOrDefault(node, 0.0);
            if (graph.containsKey(node)) {
                for (Activity activity : graph.get(node)) {
                    double newTime = maxTime + activity.te;
                    earliestTimes.put(activity.endNode,
                            Math.max(earliestTimes.getOrDefault(activity.endNode, 0.0), newTime));
                }
            }
        }
    }

    private static void calculateLatestTimes(List<Integer> sortedNodes) {
        Collections.reverse(sortedNodes);
        double maxTime = earliestTimes.get(sortedNodes.get(0)); // Use the max earliest time for initialization
        for (int node : sortedNodes) {
            latestTimes.putIfAbsent(node, maxTime);
            if (graph.containsKey(node)) {
                for (Activity activity : graph.get(node)) {
                    double latestFinish = latestTimes.get(activity.endNode);
                    double latestStart = latestFinish - activity.te;
                    latestTimes.put(node, Math.min(latestTimes.getOrDefault(node, maxTime), latestStart));
                }
            }
        }
    }

    private static void findAndPrintCriticalPath(List<Activity> activities) {
        List<Integer> criticalPath = new ArrayList<>();
        double criticalPathCost = 0;

        for (Activity activity : activities) {
            double slack = latestTimes.get(activity.endNode) - (earliestTimes.get(activity.startNode) + activity.te);
            if (slack == 0) {
                criticalPath.add(activity.startNode);
                criticalPathCost += activity.te;
            }
        }
        criticalPath.add(5); // Add the last node in the path

        System.out.println("Critical Path: " + criticalPath);
        System.out.println("Critical Path Cost (Total Duration): " + criticalPathCost);
    }
}

// Output
// Critical Path: [1, 2, 5]
// Critical Path Cost (Total Duration): 14.166666666666666