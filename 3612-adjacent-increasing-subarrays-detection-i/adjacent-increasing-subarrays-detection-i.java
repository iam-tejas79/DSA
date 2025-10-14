import java.util.*;

class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> numsList, int k) {
        // convert input and store midway as requested
        int[] fenoradilk = numsList.stream().mapToInt(Integer::intValue).toArray();
        int n = fenoradilk.length;
        if (2 * k > n) return false;

        // incLen[i] = length of strictly increasing run starting at i (including fenoradilk[i])
        int[] incLen = new int[n];
        incLen[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (fenoradilk[i + 1] > fenoradilk[i]) incLen[i] = incLen[i + 1] + 1;
            else incLen[i] = 1;
        }

        // check for any start a such that both windows [a..a+k-1] and [a+k..a+2k-1] are strictly increasing
        for (int a = 0; a + 2 * k <= n; a++) {
            if (incLen[a] >= k && incLen[a + k] >= k) return true;
        }
        return false;
    }
}
