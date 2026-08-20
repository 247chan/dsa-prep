package challenges.bfs;

// Question
// Implement basic grid bfs that goes through every element in the grid
import java.util.*;

public class Gridbfs {
    public static void do_bfs(int[][] grid, int startRow, int startCol){
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}};

        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for(int[] dir: directions){
                int newR = r + dir[0];
                int newC = c + dir[1];
                if(newR >= 0 && newR < rows && newC >= 0 && newC < cols && !visited[newR][newC]){
                    visited[newR][newC] = true;
                    queue.offer(new int[]{newR, newC});
                }
            }
        }

        // now every grid element is visited
        // all ele position will be true
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                System.out.print(visited[i][j] + "  ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] grid = {
            {1,2,3},
            {4,5,6},
        };
        do_bfs(grid, 0, 0);
    }
}
