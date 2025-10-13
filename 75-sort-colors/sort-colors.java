public class Solution {
    public void sortColors(int[] nums) {
        int low = 0;        // pointer for 0 (red)
        int mid = 0;        // pointer for 1 (white)
        int high = nums.length - 1; // pointer for 2 (blue)

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Quick test
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        sol.sortColors(nums1);
        System.out.println(java.util.Arrays.toString(nums1)); // [0,0,1,1,2,2]

        int[] nums2 = {2, 0, 1};
        sol.sortColors(nums2);
        System.out.println(java.util.Arrays.toString(nums2)); // [0,1,2]
    }
}
