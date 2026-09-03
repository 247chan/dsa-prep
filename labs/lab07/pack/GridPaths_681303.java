package pack;

public class GridPaths_681303 {

    public int numberOfPaths(int[][] grid){
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        if(grid[0][0] == 1 || grid[rows-1][cols-1] == 1){
            return 0;
        }

        int[][] dp = new int[rows][cols];

        // base case
        dp[0][0] = 1;

        // filling first row
        for (int c = 1; c < cols; c++){
            if(grid[0][c] == 1){
                dp[0][c] = 0;
            } else {
                dp[0][c] = dp[0][c-1];
            }
        }

        // filling first col
        for (int r = 1; r < rows; r++){
            if(grid[r][0] == 1){
                dp[r][0] = 0;
            } else {
                dp[r][0] = dp[r-1][0];
            }
        }

        // others
        for(int r = 1; r < rows; r++){
            for(int c = 1; c < cols; c++){
                if(grid[r][c] == 1){
                    dp[r][c] = 0;
                } else {
                    dp[r][c] = dp[r-1][c] + dp[r][c-1];
                }
            }
        }
        return dp[rows-1][cols-1];
    }
}