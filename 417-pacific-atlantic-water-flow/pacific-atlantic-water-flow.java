import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Directions: up, down, left, right
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        // DFS function
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, dirs);        // Pacific left
            dfs(heights, atlantic, i, n - 1, dirs);   // Atlantic right
        }

        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, dirs);        // Pacific top
            dfs(heights, atlantic, m - 1, j, dirs);   // Atlantic bottom
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, boolean[][] visited, int i, int j, int[][] dirs) {
        int m = heights.length, n = heights[0].length;
        visited[i][j] = true;

        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;
            if (heights[x][y] < heights[i][j]) continue; // must flow uphill in reverse
            dfs(heights, visited, x, y, dirs);
        }
    }
}
