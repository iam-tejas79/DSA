import java.util.*;

class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        Map<Integer, List<Integer>> diagMap = new HashMap<>();

        // 1️⃣ Collect diagonals
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                diagMap.computeIfAbsent(i - j, k -> new ArrayList<>()).add(grid[i][j]);
            }
        }

        // 2️⃣ Sort diagonals based on their region
        for (int key : diagMap.keySet()) {
            List<Integer> list = diagMap.get(key);
            if (key >= 0) {
                // bottom-left triangle including main diagonal
                list.sort(Collections.reverseOrder()); // non-increasing
            } else {
                // top-right triangle
                Collections.sort(list); // non-decreasing
            }
        }

        // 3️⃣ Refill diagonals into grid
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int key = i - j;
                int idx = indexMap.getOrDefault(key, 0);
                grid[i][j] = diagMap.get(key).get(idx);
                indexMap.put(key, idx + 1);
            }
        }

        return grid;
    }
}
