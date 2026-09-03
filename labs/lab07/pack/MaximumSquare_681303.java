package pack;

import java.util.Arrays;

public class MaximumSquare_681303 {

    // recursion
     public int maximalSquare_Recurse(int[][] arr){
        if (arr == null || arr.length == 0 || arr[0].length == 0){
            return 0;
        }

        int maxSide = 0;

        for(int r = 0; r < arr.length; r++){
            for(int c = 0; c < arr[0].length; c++){
                maxSide = Math.max(maxSide, maximalSquare_Recurse(arr, r, c));
            }
        }
        return maxSide * maxSide;
     }

     private int maximalSquare_Recurse(int[][] arr, int r, int c){
        if(r < 0 || c < 0 || arr[r][c] == 0){
            return 0;
        }

        int left = maximalSquare_Recurse(arr, r, c - 1);
        int up = maximalSquare_Recurse(arr, r - 1, c);
        int diagonal = maximalSquare_Recurse(arr, r - 1, c - 1);

        return 1 + Math.min(left, Math.min(up, diagonal));
     }
     
     // memoization
     public int maximalSquare_Memoiz(int[][] arr){
        if (arr == null || arr.length == 0 || arr[0].length == 0){
            return 0;
        }
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] memo = new int[rows][cols];
        for(int[] row: memo) {
            Arrays.fill(row, -1);
        }

        int maxSide = 0;
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                maxSide = Math.max(maxSide, maximalSquare_Memoiz(arr, r, c, memo));
            }
        }
        return maxSide * maxSide;
     }

     private int maximalSquare_Memoiz(int[][] arr, int r, int c, int[][] memo){
        if(r < 0 || c < 0 || arr[r][c] == 0){
            return 0;
        }
        if(memo[r][c] != -1){
            return memo[r][c];
        }
        int left = maximalSquare_Memoiz(arr, r, c - 1, memo);
        int up = maximalSquare_Memoiz(arr, r - 1, c, memo);
        int diagonal = maximalSquare_Memoiz(arr, r - 1, c - 1, memo);

        memo[r][c] = 1 + Math.min(left, Math.min(up, diagonal));
        return memo[r][c];
     }

     // DP
     public int maximalSquare_DP(int[][] arr){
        if(arr == null || arr.length == 0 || arr[0].length == 0){
            return 0;
        }

        int maxSide = 0;
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] dp = new int[rows][cols];

        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(arr[r][c] == 1){
                    if(r == 0 || c == 0){
                        dp[r][c] = 1;
                    } else {
                        int left = dp[r][c - 1];
                        int up = dp[r - 1][c];
                        int diagonal = dp[r - 1][c - 1];

                        dp[r][c] = 1 + Math.min(left, Math.min(up, diagonal));
                    }
                    maxSide = Math.max(maxSide, dp[r][c]);
                }
            }
        }

        return maxSide * maxSide;

     }
}
