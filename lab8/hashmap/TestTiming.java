package hashmap;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

public class TestTiming {
    @Test
    public void timingTest() {
        Map61B<Integer, Integer> ullMap = new ULLMap<>();
        Map61B<Integer, Integer> myHashMapALBuckets = new MyHashMapALBuckets<>();
        Map61B<Integer, Integer> myHashMapHSBuckets = new MyHashMapHSBuckets<>();
        Map61B<Integer, Integer> myHashMapLLBuckets = new MyHashMapALBuckets<>();
        Map61B<Integer, Integer> myHashMapPQBuckets = new MyHashMapALBuckets<>();
        Map61B<Integer, Integer> myHashMapTSBuckets = new MyHashMapALBuckets<>();
        int N = 10000000;

//        Stopwatch timer1 = new Stopwatch();
//        for (int i = 0; i < N; i++) {
//            ullMap.put(i, i*i);
//        }
//        System.out.println("ULLMap time is: " + timer1.elapsedTime());

        Stopwatch timer2 = new Stopwatch();
        for (int i = 0; i < N; i++) {
            myHashMapALBuckets.put(i, i*i);
        }
        for (int i = 0; i < N; i++) {
            myHashMapALBuckets.get(i);
        }
        System.out.println("ArrayList buckets time is: " + timer2.elapsedTime());

        Stopwatch timer3 = new Stopwatch();
        for (int i = 0; i < N; i++) {
            myHashMapHSBuckets.put(i, i*i);
        }
        for (int i = 0; i < N; i++) {
            myHashMapHSBuckets.get(i);
        }
        System.out.println("HashSet buckets time is: " + timer3.elapsedTime());

        Stopwatch timer4 = new Stopwatch();
        for (int i = 0; i < N; i++) {
            myHashMapLLBuckets.put(i, i*i);
        }
        for (int i = 0; i < N; i++) {
            myHashMapLLBuckets.get(i);
        }
        System.out.println("LinkedList buckets time is: " + timer4.elapsedTime());

        Stopwatch timer5 = new Stopwatch();
        for (int i = 0; i < N; i++) {
            myHashMapPQBuckets.put(i, i*i);
        }
        for (int i = 0; i < N; i++) {
            myHashMapPQBuckets.get(i);
        }
        System.out.println("PriorityQueue buckets time is: " + timer5.elapsedTime());

        Stopwatch timer6 = new Stopwatch();
        for (int i = 0; i < N; i++) {
            myHashMapTSBuckets.put(i, i*i);
        }
        for (int i = 0; i < N; i++) {
            myHashMapTSBuckets.get(i);
        }
        System.out.println("TreeSet buckets time is: " + timer6.elapsedTime());

    }
}
