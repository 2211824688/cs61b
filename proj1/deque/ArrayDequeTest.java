package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayDequeTest {
    @Test
    public void randomTest() {
        ArrayDeque<Integer> myDeque = new ArrayDeque<>();
        java.util.ArrayDeque<Integer> stdDeque = new java.util.ArrayDeque<>();

        int N = 100000000;
        for (int i = 0; i < N; i++) {
            int opt = StdRandom.uniform(0, 5);
            switch (opt) {
                case 0: // addFirst()
                    int randomNum1 = StdRandom.uniform(10000);
                    myDeque.addFirst(randomNum1);
                    stdDeque.addFirst(randomNum1);
                    break;
                case 1: // addLast()
                    int randomNum2 = StdRandom.uniform(10000);
                    myDeque.addLast(randomNum2);
                    stdDeque.addLast(randomNum2);
                    break;
                case 2: // size()
                    assertEquals(myDeque.size(), stdDeque.size());
                    break;
                case 3: // removeFirst()
                    if (stdDeque.isEmpty()) break;
                    assertEquals(myDeque.removeFirst(), stdDeque.removeFirst());
                    break;
                case 4: // removeLast()
                    if (stdDeque.isEmpty()) break;
                    assertEquals(myDeque.removeLast(), stdDeque.removeLast());
                    break;
                default:
                    break;
            }
        }
    }
}
