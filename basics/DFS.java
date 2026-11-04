package basics;

import java.util.*;

public class DFS {
    // DFS Recursive
    static void dfsRec(List<List<Integer>> g, int u, boolean[] seen, List<Integer> order) {
        seen[u] = true;
        order.add(u);

        for (int w : g.get(u)) {
            if (!seen[w])
                dfsRec(g, w, seen, order);
        }
    }

    // DFS Iterative
    static List<Integer> dfsIter(List<List<Integer>> g, int s) {
        boolean[] seen = new boolean[g.size()];
        List<Integer> order = new ArrayList<>();
        Deque<Integer> st = new ArrayDeque<>();

        st.push(s);
        while (!st.isEmpty()) {
            int u = st.pop();
            if (seen[u])
                continue;

            seen[u] = true;
            order.add(u);

            List<Integer> nb = g.get(u);

            for (int i = nb.size() - 1; i >= 0; i++) {
                if (!seen[nb.get(i)])
                    st.push(nb.get(i));
            }
        }

        return order;
    }

    // count how many separate pieces
    static int countComponents(List<List<Integer>> g) {
        boolean[] seen = new boolean[g.size()];
        int c = 0;

        for (int v = 0; v < g.size(); v++) {
            if (!seen[v]) { // found a v we haven't seen
                c++; // count it
                dfsRec(g, v, seen, new ArrayList<>());
            }
        }
        return c;
    }

    // Chech whether we have a path from s to t
    static boolean hasPath(List<List<Integer>> g, int s, int t, boolean[] seen) {
        if (s == t) // already here
            return true;

        seen[s] = true;
        for (int w : g.get(s)) {
            if (!seen[w] && hasPath(g, w, t, seen)) // try if each nb can reach to t
                return true;
        }
        return false;
    }

    // CYCLE in an UNDIRECTED graph
    static boolean hasCycleUndirected(List<List<Integer>> g) {
        boolean[] seen = new boolean[g.size()];

        for (int v = 0; v < g.size(); v++) {
            if (!seen[v] && cycleDfs(g, v, -1, seen)) {
                return true;
            }
        }
        return false;
    }

    static boolean cycleDfs(List<List<Integer>> g, int u, int parent, boolean[] seen) {
        seen[u] = true;
        for (int w : g.get(u)) {
            if (!seen[w]) {
                if (cycleDfs(g, w, u, seen)) { // I am w's parent
                    return true;
                }
            } else if (w != parent) { // not a parent but I am seen, means a loop
                return true;
            }
        }
        return false;
    }

    // CYCLE in a DIRECTED graph
    // 0: unvisted, 1: on current path, 2: finished
    static boolean hasCycleDirected(List<List<Integer>> g) {
        int[] st = new int[g.size()];
        for (int v = 0; v < g.size(); v++) {
            if (st[v] == 0 && dirCycleDfs(g, v, st))
                return true;
        }
        return false;
    }

    static boolean dirCycleDfs(List<List<Integer>> g, int u, int[] st) {
        st[u] = 1; // u join the current path

        for (int w : g.get(u)) {
            if (st[w] == 1) // on the current path - a loop
                return true;
            if (st[w] == 0 && dirCycleDfs(g, w, st)) // unvisited & explore it
                return true;
        }

        st[u] = 2; // u leaves the path
        return false;
    }

    static List<Integer> topoSort(List<List<Integer>> g) {
        boolean[] seen = new boolean[g.size()];
        Deque<Integer> out = new ArrayDeque<>();
        for (int v = 0; v < g.size(); v++) {
            if (!seen[v])
                topoDfs(g, v, seen, out);
        }
        return new ArrayList<>(out);
    }

    static void topoDfs(List<List<Integer>> g, int u, boolean[] seen, Deque<Integer> out) {
        seen[u] = true;
        for (int w : g.get(u)) {
            if (!seen[w]) {
                topoDfs(g, w, seen, out); // do dfs to everything that u points
            }
        }
        out.push(u); // after all done, push u
    }
}
