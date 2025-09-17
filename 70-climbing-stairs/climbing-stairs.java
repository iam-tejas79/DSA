class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int first = 1, second = 2, ways = 0;
        for (int i = 3; i <= n; i++) {
            ways = first + second; // dp[i] = dp[i-1] + dp[i-2]
            first = second;
            second = ways;
        }
        return second;
    }

    // Testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.climbStairs(2)); // 2
        System.out.println(sol.climbStairs(3)); // 3
        System.out.println(sol.climbStairs(5)); // 8
    }
}
