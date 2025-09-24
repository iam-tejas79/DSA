import java.util.*;

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        
        // Hours can be [0, 11], Minutes [0, 59]
        for (int hour = 0; hour < 12; hour++) {
            for (int minute = 0; minute < 60; minute++) {
                // Count number of LEDs on
                if (Integer.bitCount(hour) + Integer.bitCount(minute) == turnedOn) {
                    // Format minute to always have 2 digits
                    result.add(hour + ":" + (minute < 10 ? "0" + minute : minute));
                }
            }
        }
        
        return result;
    }
}
