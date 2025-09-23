class Solution {
    public char findTheDifference(String s, String t) {
        int charSum = 0;
        
        // Add ASCII values of all characters in t
        for (char c : t.toCharArray()) {
            charSum += c;
        }
        
        // Subtract ASCII values of all characters in s
        for (char c : s.toCharArray()) {
            charSum -= c;
        }
        
        // The remaining sum is the ASCII value of the added character
        return (char) charSum;
    }
}