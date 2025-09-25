import java.util.*;

class Solution {
    static class ClassInfo {
        int pass, total;
        double gain;

        ClassInfo(int p, int t) {
            pass = p;
            total = t;
            gain = calcGain(p, t);
        }

        static double calcGain(int p, int t) {
            return (double)(p + 1) / (t + 1) - (double)p / t;
        }
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<ClassInfo> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b.gain, a.gain) // max heap by gain
        );

        // Initialize heap
        for (int[] c : classes) {
            pq.add(new ClassInfo(c[0], c[1]));
        }

        // Assign extra students
        while (extraStudents-- > 0) {
            ClassInfo top = pq.poll();
            top.pass++;
            top.total++;
            top.gain = ClassInfo.calcGain(top.pass, top.total);
            pq.add(top);
        }

        // Compute final average
        double sum = 0.0;
        for (ClassInfo c : pq) {
            sum += (double)c.pass / c.total;
        }

        return sum / classes.length;
    }
}
