import java.util.*;

class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        String smallest = s;

        queue.offer(s);
        seen.add(s);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.compareTo(smallest) < 0) smallest = curr;

            // Operation 1: add 'a' to all odd indices
            String addA = addOperation(curr, a);
            if (seen.add(addA)) queue.offer(addA);

            // Operation 2: rotate by 'b'
            String rotated = rotate(curr, b);
            if (seen.add(rotated)) queue.offer(rotated);
        }

        return smallest;
    }

    private String addOperation(String s, int a) {
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i += 2) {
            int newDigit = (arr[i] - '0' + a) % 10;
            arr[i] = (char) (newDigit + '0');
        }
        return new String(arr);
    }

    private String rotate(String s, int b) {
        int n = s.length();
        b %= n;
        return s.substring(n - b) + s.substring(0, n - b);
    }
}
