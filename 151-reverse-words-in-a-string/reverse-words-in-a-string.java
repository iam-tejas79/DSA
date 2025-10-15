class Solution {
    public String reverseWords(String s) {
        // Step 1: trim spaces and split by one or more spaces
        String[] words = s.trim().split("\\s+");

        // Step 2: reverse the order of words
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }

        // Step 3: return final reversed string
        return sb.toString();
    }
}
