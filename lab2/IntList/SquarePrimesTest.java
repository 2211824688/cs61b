package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimes1() {
        IntList lst = IntList.of(2, 3, 10, 20);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 9 -> 10 -> 20", lst.toString());
        assertTrue(changed);
    }

    /**
     * 随机生成数字测试
     */
    @Test
    public void testSquarePrimes2() {
        Random random = new Random();
        int randSize = random.nextInt(64) + 5;
        System.out.println("lst size is: " + randSize);
        IntList lst = new IntList(0, null);
        IntList p = lst;
        StringBuilder expectedStr = new StringBuilder("0");

        // 生成随机的list
        for (int i = 0; i < randSize; i++) {
            int randNum = random.nextInt(256);
            IntList newList = new IntList(randNum, null);
            p.rest = newList;
            p = newList;
            expectedStr.append(" -> ").append(Primes.isPrime(randNum) ? randNum*randNum : randNum);
        }


        IntListExercises.squarePrimes(lst);

        System.out.println("expected list: " + expectedStr);
        System.out.println("actual list: " + lst);
        assertEquals(expectedStr.toString(), lst.toString());
    }
}
