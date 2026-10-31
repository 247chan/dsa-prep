package basics;

import java.util.*;

/*
 * GRAPH BASICS PRACTICE  (unweighted -> weighted)
 * 
 *   unweighted graph  = List<List<Integer>>   g.get(u) is the list of neighbours of u
 *   weighted graph    = List<List<int[]>>     g.get(u) holds {neighbour, weight}
 *   edge input        = int[]{u, v}  or  int[]{u, v, w}
 *   undirected edge   = add BOTH directions.   Keep neighbours in the order edges are given.
 */

public class Graph {

    // graph in list
    static List<List<Integer>> buildUnweightedGraph(int n, int[][] edges, boolean directed) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            if (!directed)
                g.get(e[1]).add(e[0]);
        }
        return g;
    }

    static List<List<int[]>> buildWeightedGraph(int n, int[][] edges, boolean directed) {
        List<List<int[]>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            g.get(e[0]).add(new int[] { e[1], e[2] });
            if (!directed) {
                g.get(e[1]).add(new int[] { e[0], e[2] });
            }
        }
        return g;
    }

    static int degree(List<List<Integer>> g, int u) {
        return g.get(u).size();
    }

    static int inDegree(List<List<Integer>> g, int v) {
        int c = 0;
        for (List<Integer> nb : g) {
            for (int w : nb) {
                if (w == v) {
                    c++;
                }
            }
        }
        return c;
    }

    static boolean hasEdge(List<List<Integer>> g, int u, int v) {
        return g.get(u).contains(v);
    }

    // graph in n x n grid
    static int[][] buildMatrix(int n, int[][] edges, boolean directed) {
        int[][] m = new int[n][n];
        for (int[] e : edges) {
            m[e[0]][e[1]] = 1;
            if (!directed)
                m[e[1]][e[0]] = 1;
        }
        return m;
    }

    // ----------------- Traversal ---------------
    // recursive dfs
    static void dfs(List<List<Integer>> g, int u, boolean[] visited, List<Integer> order) {
        visited[u] = true;
        order.add(u);
        for (int w : g.get(u)) {
            if (!visited[w]) {
                dfs(g, w, visited, order);
            }
        }
    }

    // Prim's Algorithm
    // returns the total weight of the MST
    static int prim(List<List<int[]>> g) {
        int n = g.size(); // no. of vertices
        int total = 0; // weight of the tree
        int taken = 0; // how many v are in the tree

        // inTree[u] - true if u has joined the tree
        // default - all false
        boolean[] inTree = new boolean[n];

        // each item is a {w, n} -> {weight, node}
        // comparator a[0] - a[b] orders pairs by w
        // comparator gives the smallest weight edge first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] { 0, 0 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); // polling gives the cheapest entry

            // unzip them into w - weight, u - node
            int w = cur[0];
            int u = cur[1];

            // Lazy PQ Part!!!!!!!!!!
            // if the node is already in the tree, skip it
            if (inTree[u])
                continue;

            // if not, accept it.
            inTree[u] = true;
            total += w;
            taken++;

            // each e is {neighbour, weight}
            for (int[] e : g.get(u)) {
                // if the neighbour is not in the tree, add it to pq!
                if (!inTree[e[0]]) {
                    pq.offer(new int[] { e[1], e[0] });
                }
            }
        }

        // taken == n means every vertex joined the tree -> return the weight
        return taken == n ? total : -1;
    }

    // Prim's Algorithm but also takes parents
    static int[] primParent(List<List<int[]>> g) {
        int n = g.size();
        boolean[] inTree = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1); // -1 means no parent, root

        // pq stored as { weight, node, from }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] { 0, 0, -1 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[1];
            int from = cur[2];

            if (inTree[node]) {
                continue;
            }

            inTree[node] = true;
            parent[node] = from; // add to parent []

            for (int[] e : g.get(node)) {
                if (!inTree[e[0]]) {
                    pq.offer(new int[] { e[1], e[0], node }); // "from" is "node"
                }
            }
        }
        return parent;
    }

    // Dijkstra's Algorithm
    // dist[v] -> best distace from s to v
    static int[] dijkstra(List<List<int[]>> g, int s) {
        int n = g.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE); // starts at infinity
        dist[s] = 0; // source is 0

        // pq holds { distance, v } pairs, small distance first
        // the number "distance" is total distance from s to v -> similar to dist[v]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] { 0, s });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            if (d > dist[u]) // this means we already found shorter, stored in dist[] so skip!
                continue;

            // each e is { neighbor, weight }
            for (int[] e : g.get(u)) {
                int v = e[0];
                int w = e[1];

                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    pq.offer(new int[] { dist[v], v });
                }
            }
        }
        return dist;
    }

    // Dijkstra with Parent
    static int[] dijkstraParent(List<List<int[]>> g, int s) {
        int n = g.size();
        int[] dist = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        Arrays.fill(parent, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] { 0, s });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            if (d > dist[u])
                continue;

            for (int[] e : g.get(u)) {
                int v = e[0];
                int w = e[1];

                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    parent[v] = u; // parent added to know the path
                    pq.offer(new int[] { dist[v], v });
                }
            }
        }
        return dist;
    }

}
