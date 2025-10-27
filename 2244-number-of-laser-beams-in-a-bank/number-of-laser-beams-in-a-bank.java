class Solution {
    public int numberOfBeams(String[] bank) {
        int totalBeams = 0;           // Total number of laser beams
        int previousDeviceCount = 0;   // Number of devices in the previous non-empty row
      
        // Iterate through each row in the bank
        for (String row : bank) {
            int currentDeviceCount = 0;  // Count of security devices in current row
          
            // Count the number of '1's (security devices) in the current row
            for (int i = 0; i < row.length(); i++) {
                currentDeviceCount += row.charAt(i) - '0';
            }
          
            // If current row has security devices
            if (currentDeviceCount > 0) {
                // Calculate beams between previous row and current row
                // Each device in previous row connects to each device in current row
                totalBeams += previousDeviceCount * currentDeviceCount;
              
                // Update previous device count for next iteration
                previousDeviceCount = currentDeviceCount;
            }
        }
      
        return totalBeams;
    }
}
