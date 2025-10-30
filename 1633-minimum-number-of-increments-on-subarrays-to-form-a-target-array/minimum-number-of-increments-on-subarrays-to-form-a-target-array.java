import java.util.*;

public class Solution {
    public static int minNumberOperations(int[] target) {
        int operations = target[0];
        for (int i = 1; i < target.length; i++) {
            if (target[i] > target[i - 1]) {
                operations += target[i] - target[i - 1];
            }
        }
        return operations;
    }

    // main method for testing
    public static void main(String[] args) {
        int[] target1 = {1, 2, 3, 2, 1};
        int[] target2 = {3, 1, 1, 2};
        int[] target3 = {3, 1, 5, 4, 2};

        System.out.println(minNumberOperations(target1)); // Output: 3
        System.out.println(minNumberOperations(target2)); // Output: 4
        System.out.println(minNumberOperations(target3)); // Output: 7
    }
}
