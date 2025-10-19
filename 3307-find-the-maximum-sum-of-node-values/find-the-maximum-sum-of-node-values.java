import java.util.*;

class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long total = 0;
        long extra = 0;
        int count = 0;

        for (int num : nums) {
            long xorVal = num ^ k;
            // If XOR gives a better value, count it
            if (xorVal > num) {
                count++;
                total += xorVal;
                extra += (num - xorVal);
            } else {
                total += num;
                extra += (xorVal - num);
            }
        }

        // If count is odd, one XOR must be reverted (remove smallest difference)
        if (count % 2 != 0) {
            long minDiff = Long.MAX_VALUE;
            for (int num : nums) {
                long diff = Math.abs((long)(num ^ k) - num);
                minDiff = Math.min(minDiff, diff);
            }
            total -= minDiff;
        }

        return total;
    }
}
