import java.util.*;

class Solution {
    int n;
    int[] seg; // Segment tree storing max capacity in a segment

    // Required method name for judge
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        n = baskets.length;
        seg = new int[4 * n];
        build(baskets, 1, 0, n - 1);

        int unplaced = 0;
        for (int fruit : fruits) {
            int idx = query(1, 0, n - 1, fruit);
            if (idx == -1) {
                unplaced++;
            } else {
                // mark basket as used
                update(1, 0, n - 1, idx, Integer.MIN_VALUE);
            }
        }
        return unplaced;
    }

    // Build segment tree
    void build(int[] arr, int v, int l, int r) {
        if (l == r) {
            seg[v] = arr[l];
        } else {
            int m = (l + r) / 2;
            build(arr, v * 2, l, m);
            build(arr, v * 2 + 1, m + 1, r);
            seg[v] = Math.max(seg[v * 2], seg[v * 2 + 1]);
        }
    }

    // Query leftmost index with capacity >= need
    int query(int v, int l, int r, int need) {
        if (seg[v] < need) return -1;
        if (l == r) return l;
        int m = (l + r) / 2;
        if (seg[v * 2] >= need) {
            return query(v * 2, l, m, need);
        } else {
            return query(v * 2 + 1, m + 1, r, need);
        }
    }

    // Update basket at idx as used
    void update(int v, int l, int r, int idx, int val) {
        if (l == r) {
            seg[v] = val;
        } else {
            int m = (l + r) / 2;
            if (idx <= m) update(v * 2, l, m, idx, val);
            else update(v * 2 + 1, m + 1, r, idx, val);
            seg[v] = Math.max(seg[v * 2], seg[v * 2 + 1]);
        }
    }
}

// Example test harness
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] fruits1 = {4, 2, 5};
        int[] baskets1 = {3, 5, 4};
        System.out.println(sol.numOfUnplacedFruits(fruits1, baskets1)); // Expected 1

        int[] fruits2 = {3, 6, 1};
        int[] baskets2 = {6, 4, 7};
        System.out.println(sol.numOfUnplacedFruits(fruits2, baskets2)); // Expected 0
    }
}