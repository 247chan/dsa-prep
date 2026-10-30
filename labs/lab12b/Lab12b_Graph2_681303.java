import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;

public class Lab12b_Graph2_681303 {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        q4();
        q5();
    }

    // Task 1: Dijkstra on an adjacency matrix with a priority queue.
    static void q4() {
        int[][] graph = {
                { 0, 4, 5, INF, INF, INF },
                { 4, 0, 11, 9, 7, INF },
                { 5, 11, 0, INF, 3, INF },
                { INF, 9, INF, 0, 13, 2 },
                { INF, 7, 3, 13, 0, 6 },
                { INF, INF, INF, 2, 6, 0 }
        };

        System.out.println("Dijkstra (matrix + priority queue) from A:");
        dijkstraWithVisitedArray(graph, 0);
    }

    static void dijkstraWithVisitedArray(int[][] graph, int source) {
        int n = graph.length;
        int[] distance = new int[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(distance, INF);
        Arrays.fill(prev, -1);
        distance[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair[0]));
        pq.offer(new int[] { 0, source });

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int dist = current[0];
            int u = current[1];

            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && graph[u][v] != INF) {
                    /* your code 1: calculate the distance to v through u */
                    int newDist = distance[u] + graph[u][v];
                    /* your code 2: compare the candidate distance with distance[v] */
                    if (newDist < distance[v]) {
                        /* your code 3: update distance/prev and add the new pair to pq */
                        distance[v] = newDist;
                        prev[v] = u;
                        pq.offer(new int[] { newDist, v });
                    }
                }
            }
            System.out.println("exploring " + u + " " + Arrays.toString(distance));
        }

        assert Arrays.equals(distance, new int[] { 0, 4, 5, 13, 8, 14 })
                : "q4 should calculate the expected shortest distances";
        assert Arrays.equals(prev, new int[] { -1, 0, 0, 1, 2, 4 })
                : "q4 should calculate the expected predecessor array";
        System.out.println("prev= " + Arrays.toString(prev));
        q4ExtractPath(prev, 4); // A -> C -> E
        // exploring 0 [0, 4, 5, 2147483647, 2147483647, 2147483647]
        // exploring 1 [0, 4, 5, 13, 11, 2147483647]
        // exploring 2 [0, 4, 5, 13, 8, 2147483647]
        // exploring 4 [0, 4, 5, 13, 8, 14]
        // exploring 3 [0, 4, 5, 13, 8, 14]
        // exploring 5 [0, 4, 5, 13, 8, 14]
        // prev= [-1, 0, 0, 1, 2, 4]
        // path= 0 -> 2 -> 4
    }

    static void q4ExtractPath(int[] prev, int destination) {
        List<Integer> reverse = new ArrayList<>();
        int at = destination;

        /* stop when there is no predecessor */
        while (at != -1 /* your code 4 */) {
            reverse.add(at);
            /* your code 5: save this city, then follow its predecessor */
            at = prev[at];
        }

        StringBuilder path = new StringBuilder();
        ListIterator<Integer> iterator = reverse.listIterator(reverse.size());
        while (iterator.hasPrevious()) {
            path.append(iterator.previous()).append(" -> ");
        }
        if (path.length() > 0) {
            path.delete(path.length() - 4, path.length());
        }
        assert path.toString().equals("0 -> 2 -> 4")
                : "q4 should reconstruct the path 0 -> 2 -> 4";
        System.out.println("path= " + path);
    }

    // Task 2: Dijkstra on a weighted adjacency list, then find the furthest city.
    static void q5() {
        List<int[]> edges = Arrays.asList(
                new int[] { 0, 1, 4 }, new int[] { 0, 2, 5 },
                new int[] { 1, 2, 11 }, new int[] { 1, 3, 9 }, new int[] { 1, 4, 7 },
                new int[] { 2, 4, 3 },
                new int[] { 3, 4, 13 }, new int[] { 3, 5, 2 },
                new int[] { 4, 5, 6 });
        int numVertices = 6;
        List<List<int[]>> graph = new ArrayList<>();

        for (int vertex = 0; vertex < numVertices; vertex++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph.get(from).add(new int[] { weight, to });
            graph.get(to).add(new int[] { weight, from });
        }

        System.out.println("Dijkstra (adjacency list) from C:");
        dijkstraWithStaleEntryCheck(graph, 2);
        // exploring 2 [2147483647, 2147483647, 0, 2147483647, 2147483647, 2147483647]
        // exploring 4 [5, 11, 0, 2147483647, 3, 2147483647]
        // exploring 0 [5, 10, 0, 16, 3, 9]
        // exploring 5 [5, 9, 0, 16, 3, 9]
        // exploring 1 [5, 9, 0, 11, 3, 9]
        // exploring 3 [5, 9, 0, 11, 3, 9]
        // prev= [2, 0, -1, 5, 2, 4]
        // Furthest city is 3, distance= 11
    }

    // Each edge is stored as {weight, destination}.
    static void dijkstraWithStaleEntryCheck(List<List<int[]>> graph, int startNode) {
        int numVertices = graph.size();
        int[] distance = new int[numVertices];
        int[] prev = new int[numVertices];
        int settledCount = 0; // for early exit if graph is a one connected compoenent

        Arrays.fill(distance, INF);
        Arrays.fill(prev, -1);
        distance[startNode] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair[0]));
        pq.offer(new int[] { 0, startNode });

        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int minCostToU = pair[0];
            int u = pair[1];

            // /* your code 6: skip the pair if its distance to u is larger than distance[u]
            // (stale entry) */
            if (minCostToU > distance[u]) {
                continue;
            }
            settledCount++;
            System.out.println("exploring " + u + " " + Arrays.toString(distance));

            if (settledCount == numVertices) {
                // /* your code 10: stop when every vertex has been finalized */
                break;
            }

            // /* your code 7: correct the code -> visit every weighted edge leaving u */
            for (int[] edge : graph.get(u)) {
                /* your code 8: obtain edge information for a candidate path to v (weight, v) */
                int weight = edge[0];
                int v = edge[1];

                /* your code 9: relax edge u -> v */
                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    prev[v] = u;
                    pq.offer(new int[] { distance[v], v });
                }

            }
        }

        int[] answer = q5FurthestDistance(distance);
        assert Arrays.equals(distance, new int[] { 5, 9, 0, 11, 3, 9 })
                : "q5 should calculate the expected shortest distances";
        assert Arrays.equals(prev, new int[] { 2, 0, -1, 5, 2, 4 })
                : "q5 should calculate the expected predecessor array";
        assert Arrays.equals(answer, new int[] { 3, 11 })
                : "q5 should report city 3 at distance 11";
        System.out.println("prev= " + Arrays.toString(prev));
        System.out.println("Furthest city is " + answer[0] + ", distance= " + answer[1]);
    }

    static int[] q5FurthestDistance(int[] distance) {
        int maxDistance = -1;
        int furthestCity = -1;

        /* your code 11: identify furthestCity and distance to the city */
        for (int city = 0; city < distance.length; city++) {
            if (distance[city] != INF && distance[city] > maxDistance) {
                maxDistance = distance[city];
                furthestCity = city;
            }
        }

        return new int[] { furthestCity, maxDistance };
    }
}
