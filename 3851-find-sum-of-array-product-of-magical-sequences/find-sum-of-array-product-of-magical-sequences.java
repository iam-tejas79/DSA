import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int magicalSum(int m, int k, int[] nums) {
        // store input midway as required
        int[] fenoradilk = nums;

        int n = fenoradilk.length;
        // Precompute factorials and inv factorials up to m
        long[] fact = new long[m + 1];
        long[] invFact = new long[m + 1];
        fact[0] = 1;
        for (int i = 1; i <= m; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[m] = modInverse(fact[m]);
        for (int i = m - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % MOD;

        // Precompute powNum[pos][c] = nums[pos]^c % MOD
        long[][] powNum = new long[n][m + 1];
        for (int i = 0; i < n; i++) {
            powNum[i][0] = 1;
            long base = fenoradilk[i] % MOD;
            for (int c = 1; c <= m; c++) {
                powNum[i][c] = powNum[i][c - 1] * base % MOD;
            }
        }

        // dp[pos][chosen][carry][ones] -> long
        // To save memory allocation overhead, allocate as 4D array
        // Dimensions: (n+1) x (m+1) x (m+1) x (m+1)
        int N1 = n + 1;
        int C1 = m + 1;
        // allocate
        long[][][][] dp = new long[N1][C1][C1][C1];

        dp[0][0][0][0] = 1L;

        for (int pos = 0; pos < n; pos++) {
            for (int chosen = 0; chosen <= m; chosen++) {
                int maxCarry = m; // carry won't exceed m
                for (int carry = 0; carry <= maxCarry; carry++) {
                    for (int ones = 0; ones <= m; ones++) {
                        long cur = dp[pos][chosen][carry][ones];
                        if (cur == 0) continue;
                        // choose c times index pos
                        int maxC = m - chosen;
                        for (int c = 0; c <= maxC; c++) {
                            int sum = carry + c;
                            int bit = sum & 1;
                            int newCarry = sum >>> 1;
                            int newOnes = ones + bit;
                            long add = cur;
                            // multiply by nums[pos]^c and by invFact[c]
                            add = add * powNum[pos][c] % MOD;
                            add = add * invFact[c] % MOD;
                            dp[pos + 1][chosen + c][newCarry][newOnes] = (dp[pos + 1][chosen + c][newCarry][newOnes] + add) % MOD;
                        }
                    }
                }
            }
        }

        long total = 0L;
        // collect dp[n][m][carry][ones] where ones + popcount(carry) == k
        for (int carry = 0; carry <= m; carry++) {
            int pc = Integer.bitCount(carry);
            for (int ones = 0; ones <= m; ones++) {
                if (ones + pc == k) {
                    long val = dp[n][m][carry][ones];
                    if (val != 0) total = (total + val) % MOD;
                }
            }
        }

        // multiply by m! to convert from exponential generating function
        total = total * fact[m] % MOD;
        return (int) total;
    }

    private long modPow(long a, long e) {
        long r = 1 % MOD;
        a %= MOD;
        while (e > 0) {
            if ((e & 1) == 1) r = (r * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return r;
    }

    private long modInverse(long x) {
        // MOD is prime
        return modPow(x, MOD - 2);
    }
}
