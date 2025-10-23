class Solution {
    public boolean hasSameDigits(String s) {
        // Convert string to a list of digits
        int n = s.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = s.charAt(i) - '0';
        }

        // Keep reducing the array until only two digits remain
        while (digits.length > 2) {
            int[] next = new int[digits.length - 1];
            for (int i = 0; i < digits.length - 1; i++) {
                next[i] = (digits[i] + digits[i + 1]) % 10;
            }
            digits = next; // update to new sequence
        }

        // Return true if both digits are the same
        return digits[0] == digits[1];
    }
}
