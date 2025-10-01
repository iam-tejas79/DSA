/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.*;

// Definition for a binary tree node
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                
                // Add children to queue for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            
            // Reverse the list if we're going right to left
            if (!leftToRight) {
                Collections.reverse(currentLevel);
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight; // Toggle direction
        }
        
        return result;
    }
    
    // Helper method to create a tree from array representation
    public static TreeNode createTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            
            if (i < values.length && values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;
            
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }
        
        return root;
    }
    
    // Test the solution
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example 1: [3,9,20,null,null,15,7]
        System.out.println("Example 1:");
        TreeNode root1 = createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println("Input: root = [3,9,20,null,null,15,7]");
        System.out.println("Output: " + sol.zigzagLevelOrder(root1));
        System.out.println("Expected: [[3],[20,9],[15,7]]\n");
        
        // Example 2: [1]
        System.out.println("Example 2:");
        TreeNode root2 = createTree(new Integer[]{1});
        System.out.println("Input: root = [1]");
        System.out.println("Output: " + sol.zigzagLevelOrder(root2));
        System.out.println("Expected: [[1]]\n");
        
        // Example 3: []
        System.out.println("Example 3:");
        TreeNode root3 = createTree(new Integer[]{});
        System.out.println("Input: root = []");
        System.out.println("Output: " + sol.zigzagLevelOrder(root3));
        System.out.println("Expected: []\n");
        
        // Additional test case
        System.out.println("Additional Example:");
        TreeNode root4 = createTree(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("Input: root = [1,2,3,4,5,6,7]");
        System.out.println("Output: " + sol.zigzagLevelOrder(root4));
        System.out.println("Expected: [[1],[3,2],[4,5,6,7]]\n");
    }
}