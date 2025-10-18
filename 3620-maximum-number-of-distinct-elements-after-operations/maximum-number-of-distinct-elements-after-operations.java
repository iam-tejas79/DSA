import java.util.Arrays;

class Solution {
    // Renamed the method to match the expected name in the driver code
    public int maxDistinctElements(int[] nums, int k) {
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        long lastDistinctVal = Long.MIN_VALUE;
        int distinctCount = 0;
        
        for (int x : nums) {
            // Check if we can make a distinct element from x
            // The smallest possible value we can create from x is x - k.
            // If this is greater than the last distinct value, we can use it.
            if ((long) x - k > lastDistinctVal) {
                lastDistinctVal = (long) x - k;
                distinctCount++;
            } 
            // Otherwise, we can still form a distinct number if x + k is large enough.
            // We greedily choose the smallest possible value that is larger than the last one,
            // which is lastDistinctVal + 1.
            else if ((long) x + k > lastDistinctVal) {
                lastDistinctVal++;
                distinctCount++;
            }
            // If x + k is not greater than or equal to the last distinct value,
            // we can't create a new distinct number, so we skip this element.
        }
        
        return distinctCount;
    }
}
