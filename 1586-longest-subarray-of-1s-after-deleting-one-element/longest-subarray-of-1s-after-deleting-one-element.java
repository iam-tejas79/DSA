class Solution {
    public int longestSubarray(int[] nums) {
        int left = 0, zeros = 0, maxLen = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) zeros++;
            
            // if more than one 0 in window, move left forward
            while (zeros > 1) {
                if (nums[left] == 0) zeros--;
                left++;
            }
            
            // length of window minus one deletion
            maxLen = Math.max(maxLen, right - left);
        }
        
        return maxLen;
    }
}
