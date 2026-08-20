package challenges.bfs;

import java.util.*;

public class RottingOranges {
    public static int orangesRotting(int[][] grid){
        int fresh = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c] == 2){
                    queue.offer(new int[]{r, c});
                } else if(grid[r][c] == 1){
                    fresh++;
                }
            }
        }

        if(fresh == 0) return 0;

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        int minutes=0;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] curr = queue.poll();
                
                for(int[] d: directions){
                    int newR = curr[0] + d[0];
                    int newC = curr[1] + d[1];
                    if(newR >= 0 && newR < rows && newC >= 0 && newC < cols && grid[newR][newC] == 1){
                        grid[newR][newC] = 2;
                        fresh--;
                        queue.offer(new int[]{newR, newC});
                    }
                }
            }
            minutes++;
        }
        return fresh == 0 ? minutes : -1;
    }
    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 1, 1 ,1},
            {1, 0, 0, 2, 1},
            {1, 1, 1, 1, 1},
        };
        System.out.println(orangesRotting(grid));
    }
    
}
