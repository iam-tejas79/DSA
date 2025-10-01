class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int totalDrunk = numBottles;  // Drink all initial bottles
        int emptyBottles = numBottles; // Now we have this many empty bottles
        
        // Keep exchanging while we have enough empty bottles
        while (emptyBottles >= numExchange) {
            // Exchange empty bottles for new full bottles
            int newBottles = emptyBottles / numExchange;
            
            // Drink the new bottles
            totalDrunk += newBottles;
            
            // Update empty bottles: remainder from exchange + newly drunk bottles
            emptyBottles = emptyBottles % numExchange + newBottles;
        }
        
        return totalDrunk;
    }
    
    // Test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1
        int numBottles1 = 9, numExchange1 = 3;
        System.out.println("Input: numBottles = " + numBottles1 + 
                          ", numExchange = " + numExchange1);
        System.out.println("Output: " + sol.numWaterBottles(numBottles1, numExchange1));
        System.out.println("Expected: 13");
        System.out.println("Explanation: 9 (initial) + 3 (from 9/3) + 1 (from 3/3) = 13\n");
        
        // Example 2
        int numBottles2 = 15, numExchange2 = 4;
        System.out.println("Input: numBottles = " + numBottles2 + 
                          ", numExchange = " + numExchange2);
        System.out.println("Output: " + sol.numWaterBottles(numBottles2, numExchange2));
        System.out.println("Expected: 19");
        System.out.println("Explanation: 15 (initial) + 3 (from 12/4) + 1 (from 4/4) = 19\n");
        
        // Additional test case
        int numBottles3 = 5, numExchange3 = 5;
        System.out.println("Input: numBottles = " + numBottles3 + 
                          ", numExchange = " + numExchange3);
        System.out.println("Output: " + sol.numWaterBottles(numBottles3, numExchange3));
        System.out.println("Expected: 6");
        System.out.println("Explanation: 5 (initial) + 1 (from 5/5) = 6\n");
    }
}