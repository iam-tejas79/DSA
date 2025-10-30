import java.util.*;

public class Solution {
    static final long MOD = 1_000_000_007;

    public int[] productQueries(int n, int[][] queries) {
        // Step 1: Extract powers of 2 that sum to n
        List<Long> powers = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            if ((n & (1 << i)) != 0) {
                powers.add(1L << i);
            }
        }

        int len = powers.size();
        long[] prefix = new long[len + 1];
        prefix[0] = 1;

        // Step 2: Compute prefix product modulo MOD
        for (int i = 0; i < len; i++) {
            prefix[i + 1] = (prefix[i] * powers.get(i)) % MOD;
        }

        int[] result = new int[queries.length];

        // Step 3: Process each query efficiently
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            long product = (prefix[r + 1] * modInverse(prefix[l], MOD)) % MOD;
            result[i] = (int) product;
        }

        return result;
    }

    // Modular inverse using Fermat’s Little Theorem (MOD is prime)
    private long modInverse(long a, long mod) {
        return pow(a, mod - 2, mod);
    }

    private long pow(long base, long exp, long mod) {
        long res = 1;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    // --- Optional main method for local testing ---
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] queries1 = {{0,1}, {2,2}, {0,3}};
        System.out.println(Arrays.toString(sol.productQueries(15, queries1))); // [2, 4, 64]

        int[][] queries2 = {{0,0}};
        System.out.println(Arrays.toString(sol.productQueries(2, queries2)));  // [2]
    }
}
