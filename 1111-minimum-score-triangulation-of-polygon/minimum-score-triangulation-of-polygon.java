class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        
        // dp[i][j] = minimum score to triangulate polygon from vertex i to j
        int[][] dp = new int[n][n];
        
        // Length of the chain we're considering
        // We need at least 3 vertices to form a triangle
        for (int length = 3; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                // Try each vertex k between i and j to form triangle (i, k, j)
                for (int k = i + 1; k < j; k++) {
                    // Cost of triangle (i, k, j)
                    int triangleCost = values[i] * values[k] * values[j];
                    // Cost of triangulating left part (i to k)
                    int leftCost = dp[i][k];
                    // Cost of triangulating right part (k to j)
                    int rightCost = dp[k][j];
                    
                    int totalCost = triangleCost + leftCost + rightCost;
                    dp[i][j] = Math.min(dp[i][j], totalCost);
                }
            }
        }
        
        // Return the cost of triangulating the entire polygon (0 to n-1)
        return dp[0][n - 1];
    }
    
    // Test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int[] values1 = {1, 2, 3};
        System.out.println("Input: [1, 2, 3]");
        System.out.println("Output: " + sol.minScoreTriangulation(values1));
        System.out.println("Expected: 6\n");
        
        // Example 2
        int[] values2 = {3, 7, 4, 5};
        System.out.println("Input: [3, 7, 4, 5]");
        System.out.println("Output: " + sol.minScoreTriangulation(values2));
        System.out.println("Expected: 144\n");
        
        // Example 3
        int[] values3 = {1, 3, 1, 4, 1, 5};
        System.out.println("Input: [1, 3, 1, 4, 1, 5]");
        System.out.println("Output: " + sol.minScoreTriangulation(values3));
        System.out.println("Expected: 13\n");
    }
}