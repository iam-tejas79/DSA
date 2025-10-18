import java.util.Arrays;

class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long totalSum = 0;
        int flipCount = 0;
        int minChange = Integer.MAX_VALUE;

        for (int num : nums) {
            int xorNum = num ^ k;
            if (xorNum > num) {
                totalSum += xorNum;
                flipCount++;
                minChange = Math.min(minChange, xorNum - num);
            } else {
                totalSum += num;
                minChange = Math.min(minChange, num - xorNum);
            }
        }

        if (flipCount % 2 == 0) {
            return totalSum;
        } else {
            return totalSum - minChange;
        }
    }
}
