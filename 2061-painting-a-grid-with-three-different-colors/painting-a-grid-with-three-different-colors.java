import java.util.*;

public class Solution {
    private static final int MOD = 1_000_000_007;
    
    public int colorTheGrid(int m, int n) {
        // 1) Generate all valid column states (no equal adjacent vertically)
        List<int[]> states = new ArrayList<>();
        int max = (int) Math.pow(3, m);
        for (int mask = 0; mask < max; mask++) {
            int x = mask;
            int[] col = new int[m];
            boolean ok = true;
            for (int r = 0; r < m; r++) {
                col[r] = x % 3;
                x /= 3;
                if (r > 0 && col[r] == col[r - 1]) {
                    ok = false;
                    break;
                }
            }
            if (ok) states.add(col);
        }
        
        int S = states.size();
        
        // 2) Precompute compatibility between columns
        boolean[][] compat = new boolean[S][S];
        for (int i = 0; i < S; i++) {
            int[] a = states.get(i);
            for (int j = 0; j < S; j++) {
                int[] b = states.get(j);
                boolean ok = true;
                for (int r = 0; r < m; r++) {
                    if (a[r] == b[r]) {
                        ok = false;
                        break;
                    }
                }
                compat[i][j] = ok;
            }
        }

        // 3) DP over columns
        long[] dp = new long[S];
        Arrays.fill(dp, 1); // first column: any valid state
        
        for (int col = 1; col < n; col++) {
            long[] next = new long[S];
            for (int j = 0; j < S; j++) {
                long sum = 0;
                for (int i = 0; i < S; i++) {
                    if (compat[i][j]) {
                        sum += dp[i];
                        if (sum >= MOD) sum -= MOD;
                    }
                }
                next[j] = sum;
            }
            dp = next;
        }
        
        long ans = 0;
        for (long v : dp) {
            ans += v;
            if (ans >= MOD) ans -= MOD;
        }
        return (int) ans;
    }

    // Quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.colorTheGrid(1, 1)); // 3
        System.out.println(sol.colorTheGrid(1, 2)); // 6
        System.out.println(sol.colorTheGrid(5, 5)); // 580986
    }
}
