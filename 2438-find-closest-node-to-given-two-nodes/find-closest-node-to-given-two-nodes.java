public class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        
        int[] dist1 = new int[n];
        int[] dist2 = new int[n];
        
        // Fill with -1 to mark unreachable nodes
        for (int i = 0; i < n; i++) {
            dist1[i] = -1;
            dist2[i] = -1;
        }
        
        // Compute distances from node1 and node2
        fillDistances(edges, node1, dist1);
        fillDistances(edges, node2, dist2);
        
        int answer = -1;
        int minMaxDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            if (dist1[i] != -1 && dist2[i] != -1) {
                int maxDist = Math.max(dist1[i], dist2[i]);
                if (maxDist < minMaxDistance) {
                    minMaxDistance = maxDist;
                    answer = i;
                }
            }
        }
        
        return answer;
    }
    
    private void fillDistances(int[] edges, int start, int[] dist) {
        int distance = 0;
        int current = start;
        
        while (current != -1 && dist[current] == -1) { // not visited yet
            dist[current] = distance++;
            current = edges[current];
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] edges1 = {2, 2, 3, -1};
        System.out.println(sol.closestMeetingNode(edges1, 0, 1)); // Output: 2
        
        int[] edges2 = {1, 2, -1};
        System.out.println(sol.closestMeetingNode(edges2, 0, 2)); // Output: 2
    }
}
