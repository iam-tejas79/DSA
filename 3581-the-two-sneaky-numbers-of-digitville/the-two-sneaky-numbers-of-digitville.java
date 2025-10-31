import java.util.*;

public class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        int[] result = new int[2];
        int index = 0;

        for (int num : nums) {
            if (!seen.add(num)) {
                result[index++] = num;
            }
        }

        return result;
    }
}
