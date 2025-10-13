import java.util.*;

public class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        
        for (String word : words) {
            if (!result.isEmpty() && isAnagram(result.get(result.size() - 1), word)) {
                // If current word and last word in result are anagrams, remove the last one
                continue; 
            } else {
                result.add(word);
            }
        }
        
        return result;
    }

    private boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        
        int[] count = new int[26];
        for (char c : a.toCharArray()) count[c - 'a']++;
        for (char c : b.toCharArray()) count[c - 'a']--;
        
        for (int val : count) {
            if (val != 0) return false;
        }
        return true;
    }

    // For quick testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] words1 = {"abba","baba","bbaa","cd","cd"};
        System.out.println(sol.removeAnagrams(words1)); // Output: [abba, cd]

        String[] words2 = {"a","b","c","d","e"};
        System.out.println(sol.removeAnagrams(words2)); // Output: [a, b, c, d, e]
    }
}
