import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import pack.GraphList_681303;
import pack.GraphMap_681303;
import pack.GraphNode_681303;

public class Lab12a_Graph1_681303 {
        static final int INF = Integer.MAX_VALUE;

        static void q1_1() {
                List<int[]> edges = Arrays.asList(
                                new int[] { 0, 1 }, new int[] { 0, 4 },
                                new int[] { 1, 2 }, new int[] { 1, 3 });

                System.out.println("\tGraph Node");
                GraphNode_681303 graph = new GraphNode_681303(5, edges);
                assert graph.hasEdge(1, 3) : "GraphNode.hasEdge(1, 3) should be true";
                assert graph.hasPath(4, 2) : "GraphNode.hasPath(4, 2) should be true";
                assert graph.neighborsOf(2).toString().equals("[1]")
                                : "GraphNode.neighborsOf(2) should be [1]";
                graph.printGraph();
                // 0 -> 1 4
                // 1 -> 0 2 3
                // 2 -> 1
                // 3 -> 1
                // 4 -> 0
                System.out.println(graph.hasEdge(1, 3)); // true
                System.out.println(graph.hasPath(4, 2)); // true
                System.out.println(graph.neighborsOf(2)); // [1]
                graph.addEdge(4, 5);
                assert graph.hasEdge(4, 5) : "GraphNode.addEdge(4, 5) should add the edge";
                System.out.println("DFS:");
                graph.dfs(0); // 0 1 2 3 4 5
        }

        static void q1_2() {
                List<int[]> edges = Arrays.asList(
                                new int[] { 0, 1 }, new int[] { 0, 4 },
                                new int[] { 1, 2 }, new int[] { 1, 3 });

                System.out.println("\tGraph List");
                GraphList_681303 graph = new GraphList_681303(5, edges);
                assert graph.hasEdge(1, 3) : "GraphList.hasEdge(1, 3) should be true";
                assert graph.hasPath(4, 2) : "GraphList.hasPath(4, 2) should be true";
                assert graph.neighborsOf(2).equals(Arrays.asList(1))
                                : "GraphList.neighborsOf(2) should be [1]";
                graph.printGraph();
                System.out.println(graph.hasEdge(1, 3)); // true
                System.out.println(graph.hasPath(4, 2)); // true
                System.out.println(graph.neighborsOf(2)); // [1]
                System.out.println("DFS:");
                graph.dfs(0); // 0 1 2 3 4
        }

        static void q1_3() {
                List<int[]> edges = Arrays.asList(
                                new int[] { 0, 1 }, new int[] { 0, 4 },
                                new int[] { 1, 2 }, new int[] { 1, 3 });

                System.out.println("\tGraph Map");
                GraphMap_681303 graph = new GraphMap_681303(5, edges);
                graph.addVertex(5);
                graph.addEdge(4, 5);
                assert !graph.hasEdge(1, 4) : "GraphMap.hasEdge(1, 4) should be false";
                assert graph.hasPath(4, 2) : "GraphMap.hasPath(4, 2) should be true";
                assert graph.hasPath(5, 2) : "GraphMap.hasPath(5, 2) should be true";
                assert graph.neighborsOf(2).equals(Arrays.asList(1))
                                : "GraphMap.neighborsOf(2) should be [1]";
                graph.printGraph();
                System.out.println(graph.hasEdge(1, 4)); // false
                System.out.println(graph.hasPath(4, 2)); // true
                System.out.println(graph.hasPath(5, 2)); // true
                System.out.println(graph.neighborsOf(2)); // [1]
                System.out.println("DFS all components:");
                graph.dfsAll(); // 0 1 2 3 4 5
        }

        static void q2() {
                List<int[]> noCycle = Arrays.asList(
                                new int[] { 0, 1 }, new int[] { 1, 2 } // vertex 3 is isolated
                );
                List<int[]> hasCycle = Arrays.asList(
                                new int[] { 0, 1 }, new int[] { 0, 3 },
                                new int[] { 1, 2 }, new int[] { 2, 3 });

                GraphMap_681303 acyclicGraph = new GraphMap_681303(4, noCycle);
                GraphMap_681303 cyclicGraph = new GraphMap_681303(4, hasCycle);
                assert !acyclicGraph.hasCycle() : "The first graph should not contain a cycle";
                assert cyclicGraph.hasCycle() : "The second graph should contain a cycle";
                System.out.println(acyclicGraph.hasCycle()); // false
                System.out.println(cyclicGraph.hasCycle()); // true
        }

        static void q3() {
                int[][] matrix = {
                                { 0, 3, INF, INF, INF },
                                { INF, 0, 1, INF, INF },
                                { INF, INF, 0, 4, INF },
                                { INF, INF, INF, 0, 5 },
                                { INF, INF, INF, INF, 0 }
                };

                System.out.println("DFS on adjacency matrix:");
                dfsMatrix(matrix, 0); // 0 1 2 3 4
        }

        static void dfsMatrix(int[][] matrix, int start) {
                boolean[] visited = new boolean[matrix.length];
                Deque<Integer> stack = new ArrayDeque<>();
                StringBuilder traversal = new StringBuilder();

                visited[start] = true;
                stack.push(start);

                while (!stack.isEmpty()) {
                        int current = stack.pop();
                        traversal.append(current).append(" ");
                        System.out.print(current + " ");
                        /*
                         * your code 10: scan each neighbor; if there is an unvisited edge,
                         * mark it visited and push it onto stack
                         */
                        for (int neighbor = 0; neighbor < matrix.length; neighbor++) {
                                if (neighbor != current && matrix[current][neighbor] != INF && !visited[neighbor]) {
                                        visited[neighbor] = true;
                                        stack.push(neighbor);
                                }
                        }

                }

                assert traversal.toString().equals("0 1 2 3 4 ")
                                : "dfsMatrix should print 0 1 2 3 4 for this matrix";
                System.out.println();
        }

        public static void main(String[] args) {
                q1_1();
                q1_2();
                q1_3();
                q2();
                q3();
        }
}
