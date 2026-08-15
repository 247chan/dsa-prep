package challenges.bfs;

import java.util.*;

public class NumberOfIslands {
    public static void bfsIsland(int[][] grid, boolean[][] visited, int startR, int startC){
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            // row, col coordinates of the grid element '1'
            // System.out.print(curr[0] + " " + curr[1]);
            // System.out.println();
            int r = curr[0];
            int c = curr[1];

            for(int[] dir: directions){
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc] && grid[nr][nc] == 1){
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }

    private static int countIslands(int[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 1 && !visited[r][c]){
                    bfsIsland(grid, visited, r, c);
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0,1,0,0,0},
            {1,0,0,0,0},
            {0,0,1,0,0},
            {0,0,0,1,1},
        };
        System.out.println("\nNumber of islands: " + countIslands(grid));
    }
}
