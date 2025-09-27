class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] < nums[high]) {
                high = mid; // min in left part
            } else if (nums[mid] > nums[high]) {
                low = mid + 1; // min in right part
            } else {
                high--; // nums[mid] == nums[high], shrink search space
            }
        }
        return nums[low];
    }
}
