package pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphMap_681303 {
    private Map<Integer, List<Integer>> graph;

    public GraphMap_681303(int n, List<int[]> edges) {
        /* your code 5: create the map and add vertices 0 through n - 1 */
        graph = new LinkedHashMap<>();
        for (int vertex = 0; vertex < n; vertex++) {
            addVertex(vertex);
        }

        if (edges != null) {
            for (int[] edge : edges) {
                if (edge != null && edge.length == 2) {
                    addEdge(edge[0], edge[1]);
                }
            }
        }
    }

    public void addVertex(int value) {
        graph.putIfAbsent(value, new ArrayList<>());
    }

    public void addEdge(int a, int b) {
        if (a == b) {
            return;
        }

        /* your code 6: add both endpoint vertices and both directions */
        addVertex(a);
        addVertex(b);
        addNeighbor(a, b);
        addNeighbor(b, a);
    }

    public List<Integer> neighborsOf(int value) {
        if (!graph.containsKey(value)) {
            return Collections.emptyList();
        }
        return graph.get(value);
    }

    public boolean hasEdge(int a, int b) {
        /* your code 7: check whether b is in a's adjacency list */
        if (graph.containsKey(a) && graph.get(a).contains(b)) {
            return true;
        }
        return false;
    }

    public boolean hasPath(int start, int target) {
        if (!graph.containsKey(start) || !graph.containsKey(target)) {
            return false;
        }

        return hasPathDfs(start, target, new HashSet<>());
    }

    private boolean hasPathDfs(int current, int target, Set<Integer> visited) {
        if (current == target) {
            return true;
        }

        visited.add(current);

        for (int neighbor : graph.get(current)) {
            if (!visited.contains(neighbor)
                    && hasPathDfs(neighbor, target, visited)) {
                return true;
            }
        }

        return false;
    }

    public void dfs(int start) {
        if (!graph.containsKey(start)) {
            return;
        }

        dfs(start, new HashSet<>());
        System.out.println();
    }

    public void dfsAll() {
        Set<Integer> visited = new HashSet<>();

        for (int vertex : graph.keySet()) {
            if (!visited.contains(vertex)) {
                /* your code 8: traverse this unvisited component */
                dfs(vertex, visited);
                System.out.println();
            }
        }
    }

    private void dfs(int current, Set<Integer> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (int neighbor : graph.get(current)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public boolean hasCycle() {
        Set<Integer> visited = new HashSet<>();

        for (int vertex : graph.keySet()) {
            if (!visited.contains(vertex)
                    && hasCycleDfs(vertex, -1, visited)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleDfs(int current, int parent, Set<Integer> visited) {
        visited.add(current);

        for (int neighbor : graph.get(current)) {
            if (!visited.contains(neighbor)) {
                if (hasCycleDfs(neighbor, current, visited)) {
                    return true;
                }
                /* your code 9 A visited neighbor that is not the parent forms a cycle. */
            } else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    private void addNeighbor(int source, int destination) {
        if (!graph.get(source).contains(destination)) {
            graph.get(source).add(destination);
        }
    }
}
