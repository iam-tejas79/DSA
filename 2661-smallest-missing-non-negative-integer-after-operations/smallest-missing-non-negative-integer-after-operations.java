import java.util.*;

class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        Map<Integer, Integer> count = new HashMap<>();

        // Group numbers by their remainder modulo 'value'
        for (int num : nums) {
            int mod = ((num % value) + value) % value; // handle negatives
            count.put(mod, count.getOrDefault(mod, 0) + 1);
        }

        // Find the smallest non-negative integer that cannot be formed
        int mex = 0;
        while (true) {
            int mod = mex % value;
            if (!count.containsKey(mod) || count.get(mod) == 0) {
                return mex;
            }
            count.put(mod, count.get(mod) - 1);
            mex++;
        }
    }
}
