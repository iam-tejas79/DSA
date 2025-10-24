import java.util.*;

public class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] flattened = flattenBoard(board, n);
        
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n * n + 1];
        
        queue.offer(1);
        visited[1] = true;
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n * n) return moves;
                
                for (int dice = 1; dice <= 6 && curr + dice <= n * n; dice++) {
                    int next = curr + dice;
                    int destination = flattened[next] != -1 ? flattened[next] : next;
                    
                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.offer(destination);
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
    
    // Convert 2D board into 1D array following Boustrophedon pattern
    private int[] flattenBoard(int[][] board, int n) {
        int[] flattened = new int[n * n + 1];
        int index = 1;
        boolean leftToRight = true;
        
        for (int row = n - 1; row >= 0; row--) {
            if (leftToRight) {
                for (int col = 0; col < n; col++) {
                    flattened[index++] = board[row][col];
                }
            } else {
                for (int col = n - 1; col >= 0; col--) {
                    flattened[index++] = board[row][col];
                }
            }
            leftToRight = !leftToRight;
        }
        return flattened;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[][] board1 = {
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}
        };
        System.out.println(sol.snakesAndLadders(board1)); // Output: 4
        
        int[][] board2 = {
            {-1,-1},
            {-1,3}
        };
        System.out.println(sol.snakesAndLadders(board2)); // Output: 1
    }
}
