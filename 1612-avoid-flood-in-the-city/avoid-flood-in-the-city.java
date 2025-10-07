import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Map<Integer, Integer> fullLakes = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake == 0) {
                // dry day — we’ll decide later which lake to dry
                dryDays.add(i);
                ans[i] = 1; // default, can be changed later
            } else {
                // it rains on lake
                ans[i] = -1;
                if (fullLakes.containsKey(lake)) {
                    // lake already full — need a dry day after it was filled
                    int lastRainDay = fullLakes.get(lake);
                    Integer dryDay = dryDays.higher(lastRainDay); // find next dry day
                    if (dryDay == null) {
                        // no dry day available → flood inevitable
                        return new int[0];
                    }
                    ans[dryDay] = lake; // dry this lake on that dry day
                    dryDays.remove(dryDay);
                }
                // mark lake as full
                fullLakes.put(lake, i);
            }
        }

        return ans;
    }
}
