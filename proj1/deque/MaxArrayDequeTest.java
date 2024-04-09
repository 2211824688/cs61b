package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void test1() {
        MaxArrayDeque<Integer> maxArrayDeque = new MaxArrayDeque<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < 10; i++) {
            int num = StdRandom.uniform(10000);
            System.out.println("add " + num);
            maxArrayDeque.addLast(num);
        }
        System.out.println(maxArrayDeque.max());
    }
}
