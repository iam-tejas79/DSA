import java.util.*;

class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;

        // bucket queries by their left endpoint
        List<Integer>[] starts = new ArrayList[n];
        for (int i = 0; i < n; i++) starts[i] = new ArrayList<>();
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            if (l < 0) l = 0;
            if (l < n) starts[l].add(r);
        }

        // max-heap of right endpoints (pick largest r first)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // selDiff is difference array for how many selected queries currently affect positions
        long[] selDiff = new long[n + 1];
        long curr = 0;      // current coverage at position i from selected queries
        int kept = 0;       // number of queries we selected (kept)

        for (int i = 0; i < n; i++) {
            // add all queries that start at i
            for (int r : starts[i]) pq.offer(r);

            // apply difference array to update current coverage for position i
            curr += selDiff[i];

            // we need nums[i] decrements available at index i
            while (curr < nums[i]) {
                // remove intervals that no longer can cover i (r < i)
                while (!pq.isEmpty() && pq.peek() < i) pq.poll();

                if (pq.isEmpty()) {
                    // no available interval can cover position i -> impossible
                    return -1;
                }

                int r = pq.poll(); // choose interval with largest r
                kept++;
                // selecting this interval increases coverage by +1 for positions [i..r]
                selDiff[i] += 1;
                if (r + 1 <= n - 1) selDiff[r + 1] -= 1;
                else if (r + 1 == n) selDiff[r + 1] -= 1; // safe because selDiff length = n+1

                // immediately reflect the +1 at current position
                curr += 1;
            }
        }

        // maximum removable = total queries - minimum kept
        return m - kept;
    }
}
