import java.util.*;

class Solution {
    public int maxSum(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        long sumPosDistinct = 0;
        int maxVal = Integer.MIN_VALUE;

        for (int v : nums) {
            maxVal = Math.max(maxVal, v);
            if (v > 0 && !seen.contains(v)) {
                sumPosDistinct += v;
            }
            seen.add(v);
        }

        if (sumPosDistinct > 0) return (int) sumPosDistinct;
        return maxVal;
    }
}
