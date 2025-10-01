class Solution {
    public int fib(int n) {
        if (n <= 1) return n;
        
        int a = 0, b = 1;
        
        for (int i = 2; i <= n; i++) {
            int temp = a + b;
            a = b;
            b = temp;
        }
        
        return b;
    }
    
    // Test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test cases
        System.out.println("n = 2, Output: " + sol.fib(2) + " (Expected: 1)");
        System.out.println("n = 3, Output: " + sol.fib(3) + " (Expected: 2)");
        System.out.println("n = 4, Output: " + sol.fib(4) + " (Expected: 3)");
        System.out.println("n = 5, Output: " + sol.fib(5) + " (Expected: 5)");
        System.out.println("n = 10, Output: " + sol.fib(10) + " (Expected: 55)");
    }
}