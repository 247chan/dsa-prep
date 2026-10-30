package pack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Complete example: use this class as a guide for GraphList_681303 and GraphMap_681303.
public class GraphNode_681303 {
    public static class Node {
        int value;
        List<Node> neighbors;

        Node(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        void addNeighbor(Node node) {
            if (!neighbors.contains(node)) {
                neighbors.add(node);
            }
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    private List<Node> nodes;

    public GraphNode_681303(int n, List<int[]> edges) {
        nodes = new ArrayList<>();

        for (int value = 0; value < n; value++) {
            addNode(value);
        }

        if (edges != null) {
            for (int[] edge : edges) {
                if (edge != null && edge.length == 2) {
                    addEdge(edge[0], edge[1]);
                }
            }
        }
    }

    public Node addNode(int value) {
        Node node = getNode(value);
        if (node == null) {
            node = new Node(value);
            nodes.add(node);
        }
        return node;
    }

    public void addEdge(int a, int b) {
        if (a == b) {
            return;
        }

        Node first = addNode(a);
        Node second = addNode(b);
        first.addNeighbor(second);
        second.addNeighbor(first);
    }

    public List<Node> neighborsOf(int value) {
        Node node = getNode(value);
        if (node == null) {
            return Collections.emptyList();
        }
        return node.neighbors;
    }

    public boolean hasEdge(int a, int b) {
        Node first = getNode(a);
        Node second = getNode(b);
        return first != null && second != null && first.neighbors.contains(second);
    }

    public boolean hasPath(int start, int target) {
        Node startNode = getNode(start);
        Node targetNode = getNode(target);

        if (startNode == null || targetNode == null) {
            return false;
        }

        return hasPathDfs(startNode, targetNode, new HashSet<>());
    }

    private boolean hasPathDfs(Node current, Node target, Set<Node> visited) {
        if (current == target) {
            return true;
        }

        visited.add(current);
        for (Node neighbor : current.neighbors) {
            if (!visited.contains(neighbor)
                    && hasPathDfs(neighbor, target, visited)) {
                return true;
            }
        }
        return false;
    }

    public void dfs(int start) {
        Node startNode = getNode(start);
        if (startNode == null) {
            return;
        }

        dfs(startNode, new HashSet<>());
        System.out.println();
    }

    private void dfs(Node current, Set<Node> visited) {
        visited.add(current);
        System.out.print(current + " ");

        for (Node neighbor : current.neighbors) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }

    public void printGraph() {
        for (Node node : nodes) {
            System.out.print(node + " -> ");
            for (Node neighbor : node.neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    private Node getNode(int value) {
        for (Node node : nodes) {
            if (node.value == value) {
                return node;
            }
        }
        return null;
    }
}
