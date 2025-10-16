import java.util.*;

class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diff = new int[n + 1];

        // Apply range decrements using difference array
        for (int[] q : queries) {
            diff[q[0]]++;
            diff[q[1] + 1]--;
        }

        int curr = 0;
        for (int i = 0; i < n; i++) {
            curr += diff[i];
            // After all possible decrements, nums[i] must not be > curr
            if (nums[i] > curr) return false;
        }

        return true;
    }
}
