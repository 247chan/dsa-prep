package challenges.dfs;

public class NumIslands {
    // Grid DFS: number of islands
    // counts groups of connected land cells in a grid
    // '1' == island
    static int numIslands(char[][] grid) {
        int count = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    count++;
                    sink(grid, r, c);
                }
            }
        }

        return count;
    }

    static void sink(char[][] g, int r, int c) {
        if (r < 0 ||
                c < 0 ||
                r >= g.length ||
                c >= g[0].length ||
                g[r][c] != 1)
            return;

        g[r][c] = 0;
        sink(g, r + 1, c);
        sink(g, r - 1, c);
        sink(g, r, c + 1);
        sink(g, r, c - 1);
    }
}
