import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        // Generate all valid column color patterns
        List<Integer> patterns = new ArrayList<>();
        generatePatterns(m, 0, -1, 0, patterns);

        int size = patterns.size();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < size; i++) adj.add(new ArrayList<>());

        // Build adjacency list: two columns are compatible if no row has same color
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isCompatible(patterns.get(i), patterns.get(j), m))
                    adj.get(i).add(j);
            }
        }

        // DP array
        long[] dp = new long[size];
        Arrays.fill(dp, 1); // any valid pattern can be the first column

        for (int col = 1; col < n; col++) {
            long[] newDp = new long[size];
            for (int i = 0; i < size; i++) {
                for (int prev : adj.get(i)) {
                    newDp[i] = (newDp[i] + dp[prev]) % MOD;
                }
            }
            dp = newDp;
        }

        long ans = 0;
        for (long val : dp) ans = (ans + val) % MOD;
        return (int) ans;
    }

    // Generate all valid single-column patterns (no adjacent same colors)
    private void generatePatterns(int m, int row, int prevColor, int mask, List<Integer> patterns) {
        if (row == m) {
            patterns.add(mask);
            return;
        }

        for (int color = 0; color < 3; color++) {
            if (color == prevColor) continue;
            generatePatterns(m, row + 1, color, mask * 3 + color, patterns);
        }
    }

    // Check if two columns are compatible (no same color in same row)
    private boolean isCompatible(int a, int b, int m) {
        for (int i = 0; i < m; i++) {
            if (a % 3 == b % 3) return false;
            a /= 3;
            b /= 3;
        }
        return true;
    }
}
