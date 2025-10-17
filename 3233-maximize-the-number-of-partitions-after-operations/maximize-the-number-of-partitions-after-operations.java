import java.util.*;

class Solution {
    public int maxPartitionsAfterOperations(String s, int k) {
        int n = s.length();
        // Map: key = bitmask of distinct chars in current partition
        // value = max partitions count so far
        Map<Integer, Integer> dp0 = new HashMap<>(); // no change used
        Map<Integer, Integer> dp1 = new HashMap<>(); // one change used

        dp0.put(0, 0); // start with empty string

        for (char ch : s.toCharArray()) {
            int bit = 1 << (ch - 'a');
            Map<Integer, Integer> ndp0 = new HashMap<>();
            Map<Integer, Integer> ndp1 = new HashMap<>();

            // Case 1: no change used yet
            for (var e : dp0.entrySet()) {
                int mask = e.getKey(), part = e.getValue();

                // Add current char
                int newMask = mask | bit;
                if (Integer.bitCount(newMask) > k) {
                    // need to start new partition
                    ndp0.merge(bit, part + 1, Math::max);
                } else {
                    ndp0.merge(newMask, part, Math::max);
                }

                // Try changing current char to any of 26 letters
                for (int c = 0; c < 26; c++) {
                    int nbit = 1 << c;
                    int newMask2 = mask | nbit;
                    if (Integer.bitCount(newMask2) > k) {
                        ndp1.merge(nbit, part + 1, Math::max);
                    } else {
                        ndp1.merge(newMask2, part, Math::max);
                    }
                }
            }

            // Case 2: already changed once
            for (var e : dp1.entrySet()) {
                int mask = e.getKey(), part = e.getValue();

                int newMask = mask | bit;
                if (Integer.bitCount(newMask) > k)
                    ndp1.merge(bit, part + 1, Math::max);
                else
                    ndp1.merge(newMask, part, Math::max);
            }

            dp0 = ndp0;
            dp1 = ndp1;
        }

        int ans = 0;
        for (int v : dp0.values()) ans = Math.max(ans, v);
        for (int v : dp1.values()) ans = Math.max(ans, v);
        return ans + 1; // add last partition
    }
}
