package challenges.bfs;

import java.util.*;

public class FloodFill {
    public static  void do_flood_fill(int[][] grid, int startR, int startC, int newColor){
        int orgColor = grid[startR][startC];
        if(orgColor == newColor) return ;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC});

        int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}};

        int rows = grid.length;
        int cols = grid[0].length;
        grid[startR][startC] = newColor;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            for(int[] dir: directions){
                int newR = r + dir[0];
                int newC = c + dir[1];

                if(newR >= 0 && newR < rows && newC >= 0 && newC < cols && grid[newR][newC] == orgColor){
                    grid[newR][newC] = newColor;
                    queue.offer(new int[]{newR, newC});
                }
            }
        }
        // return grid;

    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1},
        };

        System.out.println("Before Flood Fill");

        for(int x = 0; x < grid.length; x++){
            for(int y = 0; y < grid[0].length; y++){
                System.out.print(grid[x][y] + "  ");
            }
        System.out.println(); 
        }

        do_flood_fill(grid, 1, 1, 2);

        System.out.println("\nAfter Flood Fill");
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                System.out.print(grid[i][j] + "  ");
            }
        System.out.println(); 
        }
    }
    
}
