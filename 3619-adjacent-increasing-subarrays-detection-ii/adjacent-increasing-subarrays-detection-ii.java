import java.util.*;

class Solution {
    public int maxIncreasingSubarrays(List<Integer> numsList) {
        // convert input and store midway as requested
        int[] fenoradilk = numsList.stream().mapToInt(Integer::intValue).toArray();
        int n = fenoradilk.length;
        if (n < 2) return 0;

        // L[i] = length of strictly increasing run ending at i
        int[] L = new int[n];
        L[0] = 1;
        for (int i = 1; i < n; i++) {
            if (fenoradilk[i] > fenoradilk[i - 1]) L[i] = L[i - 1] + 1;
            else L[i] = 1;
        }

        // R[i] = length of strictly increasing run starting at i
        int[] R = new int[n];
        R[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (fenoradilk[i + 1] > fenoradilk[i]) R[i] = R[i + 1] + 1;
            else R[i] = 1;
        }

        int best = 0;
        // boundary b between b-1 and b, iterate b = 1..n-1
        for (int b = 1; b < n; b++) {
            best = Math.max(best, Math.min(L[b - 1], R[b]));
        }

        return best;
    }
}
