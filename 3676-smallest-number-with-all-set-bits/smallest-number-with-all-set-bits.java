class Solution {
    public int smallestNumber(int n) {
        int x = 1;
        while (x < n) {
            x = (x << 1) | 1;  // shift left and add 1, generating 1, 3, 7, 15, 31, ...
        }
        return x;
    }
}
