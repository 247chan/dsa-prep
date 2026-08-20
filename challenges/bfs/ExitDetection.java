package challenges.bfs;

import java.util.*;

public class ExitDetection {
    public static boolean findExit(int[][] grid, int startR, int startC){
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            if(grid[r][c] == 2){
                System.out.println("Exit found at index [" + r + "][" + c + "]");
                return true;
            }

            for(int[] dir: directions){
                int newR = r + dir[0];
                int newC = c + dir[1];

                if(newR >=0 && newR < rows && newC >= 0 && newC < cols && !visited[newR][newC] && (grid[newR][newC] == 0 || grid[newR][newC] == 2)){
                    queue.offer(new int[]{newR,newC});
                    visited[newR][newC] = true;
                }
            }
        }
        return false;
    }
     public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 1, 1 ,1},
            {1, 0, 0, 2, 1},
            {1, 1, 1, 1, 1},
        };
        findExit(grid, 1, 1);
    }
    
}
