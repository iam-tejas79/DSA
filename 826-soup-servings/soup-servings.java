class Solution {
    private double[][] memo;
    
    public double soupServings(int n) {
        if (n == 0) return 0.5; // both empty at start -> 0.5
        
        // scale to units of 25
        int N = (n + 24) / 25;
        // for large N, probability -> 1
        if (N >= 200) return 1.0;
        
        memo = new double[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) memo[i][j] = -1.0;
        }
        
        return dp(N, N);
    }
    
    private double dp(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;
        if (memo[a][b] >= 0) return memo[a][b];
        
        double res = 0.25 * (
            dp(a - 4, b) +
            dp(a - 3, b - 1) +
            dp(a - 2, b - 2) +
            dp(a - 1, b - 3)
        );
        memo[a][b] = res;
        return res;
    }
}
