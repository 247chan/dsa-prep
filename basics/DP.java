package basics;

import java.util.Arrays;

// Dynamic Programming & Memoization

// Each function answers the same 5 questions
// 1. State: what small numbers describe a subproblem?
// 2. Recurrence: how does a state use smaller states?
// 3. Base case: which states are known immediately?
// 4. Order: which states must be computed first?
// 5. Answer: which state holds the final result?

public class DP {
    static int fibMemo(int n, int[] memo) {
        // memo must be filled initially with -1 (not computed yet)
        if (n <= 1)
            return n;

        if (memo[n] != -1)
            return memo[n];

        return memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    }

    static int fibDP(int n) {
        if (n <= 1)
            return n;

        int a = 0;
        int b = 1;

        for (int i = 2; i <= n; i++) {
            int c = b + a;
            a = b;
            b = c;
        }

        return b;
    }

    // steps can be 1, 3, 4
    // ground floor is 0
    static int countWays(int n) {
        int[] ways = new int[n + 1];
        ways[0] = 1;

        for (int i = 1; i <= n; i++) {
            ways[i] += ways[i - 1];
            if (i >= 3)
                ways[i] += ways[i - 3];
            if (i >= 4)
                ways[i] += ways[i - 4];
        }

        return ways[n];
    }

    // you cannot rob 2 adj houses.
    // dp[i] is most money from house 0..i
    // house[i]? You have 2 options
    // 1. Skip it (keep max money up to house i-1)
    // 2. Rob it (take the money m[i] and max money up to i - 2)
    static int rob(int[] m) {
        // m is money array
        int n = m.length;
        if (n == 0)
            return 0;
        if (n == 1)
            return m[0];

        int[] dp = new int[n];
        dp[0] = m[0]; // only house zero available
        dp[1] = Math.max(m[0], m[1]); // two houses, take the richer one

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], m[i] + dp[i - 2]);
        }

        return dp[n - 1];
    }

    // House robber again with O(1)
    static int robO1(int[] m) {
        int prev1 = 0;
        int prev2 = 0;
        for (int x : m) {
            int cur = Math.max(prev1, x + prev2);
            prev1 = cur;
            prev2 = prev1;
        }

        return prev1;
    }

    // S: given set, B: targetSum
    static boolean subsetSum2D(int[] S, int B) {
        int n = S.length;
        boolean[][] T = new boolean[n][B + 1];
        for (int i = 0; i < n; i++)
            T[i][0] = true;
        if (S[0] <= B)
            T[0][S[0]] = true;
        for (int i = 1; i < n; i++)
            for (int j = 1; j <= B; j++) {
                T[i][j] = T[i - 1][j]; // skip
                if (j >= S[i])
                    T[i][j] = T[i][j] || T[i - 1][j - S[i]]; // use
            }
        return T[n - 1][B];
    }

    // T[j] means sum j is reachable with the items processed
    static boolean subsetSum1D(int[] S, int B) {
        boolean[] T = new boolean[B + 1];
        T[0] = true; // sum 0 is reachable with no items
        for (int x : S)
            for (int j = B; j >= x; j--)
                T[j] = T[j] || T[j - x]; // j goes DOWN: use each item once
        return T[B];
    }

    // dp[r][c] is the number of ways to reach (r,c)
    // moves only right, down
    // Thus, dp[r][c] = right(comes from left) + down(comes from up)
    static int uniquePaths(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = grid[0][0] == '\0' ? 1 : 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // start cell already defined
                if (r == 0 && c == 0)
                    continue;

                // if blocked cell, no way to reach it, make it 0 and skip
                if (grid[r][c] != '\0') {
                    dp[r][c] = 0;
                    continue;
                }

                int up = r > 0 ? dp[r - 1][c] : 0;
                int left = c > 0 ? dp[r][c - 1] : 0;
                dp[r][c] = up + left;
            }
        }

        return dp[rows - 1][cols - 1];
    }

    // dp[r][c] minimum total cost to reach (r,c)
    // Recurrence: cost[r][c] + min(from above, from left)
    static int minPathSum(int[][] cost) {
        int m = cost.length;
        int n = cost[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = cost[0][0];

        for (int r = 1; r < m; r++) {
            dp[r][0] = cost[r][0] + dp[r - 1][0];
        }

        for (int c = 1; c < n; c++) {
            dp[0][c] = cost[0][c] + dp[0][c - 1];
        }

        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[r][c] = cost[r][c] + Math.min(dp[r - 1][c], dp[r][c - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    // the best revenue from cutting a rod
    // dp[len] = the best revenue from that len
    static int rodCutting(int[] price) { // price[j-1] = price of length j
        int n = price.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int len = 1; len <= n; len++) {
            int best = 0;
            // try every len for cutting
            for (int cut = 1; cut <= len; cut++) {
                best = Math.max(best, price[cut - 1] + dp[len - cut]);
            }
            dp[len] = best;
        }

        return dp[n];
    }

    // ------------------- challenges ----------------------
    static int coinChangeMin(int[] coins, int amount) { // min coins, -1 if impossible
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++)
            for (int c : coins)
                if (c <= i && dp[i - c] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    static int coinWays(int[] coins, int amount) { // number of combinations (order does not matter)
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int c : coins)
            for (int i = c; i <= amount; i++)
                dp[i] += dp[i - c]; // coin loop OUTSIDE
        return dp[amount];
    }

    static int knapsack01(int[] w, int[] v, int cap) {
        int[] dp = new int[cap + 1];
        for (int i = 0; i < w.length; i++)
            for (int c = cap; c >= w[i]; c--)
                dp[c] = Math.max(dp[c], v[i] + dp[c - w[i]]);
        return dp[cap];
    }

    static int lcs(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i <= a.length(); i++)
            for (int j = 1; j <= b.length(); j++)
                dp[i][j] = a.charAt(i - 1) == b.charAt(j - 1) ? dp[i - 1][j - 1] + 1
                        : Math.max(dp[i - 1][j], dp[i][j - 1]);
        return dp[a.length()][b.length()];
    }

}
