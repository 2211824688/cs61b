package deque;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());

		System.out.println("Printing out deque: ");
		lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        LinkedListDeque<String>  lld1 = new LinkedListDeque<String>();
        LinkedListDeque<Double>  lld2 = new LinkedListDeque<Double>();
        LinkedListDeque<Boolean> lld3 = new LinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Test the get() method. */
    public void getTest() {
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>();
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
            lld2.addFirst(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals((Integer) i, lld1.get(i));
            assertEquals((Integer)(99-i), lld2.get(i));
        }
    }

    @Test
    /* Test iterator */
    public void iteratorTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        for (int i = 0; i < 100; i++) {
            lld.addLast(i);
        }
        for (int i :
             lld) {
            System.out.println(i);
        }
    }

    @Test
    public void randomTest() {
        Deque<Integer> myDeque = new LinkedListDeque<>();
        java.util.Deque<Integer> stdDeque = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            int option = StdRandom.uniform(0, 5);    // operate
            switch (option) {
                case 0: // addFirst()
                    int randomNum1 = StdRandom.uniform(10000);
                    myDeque.addFirst(randomNum1);
                    stdDeque.addFirst(randomNum1);
                    System.out.println("myDeque add first: " + randomNum1);
                    System.out.println("stdDeque add first: " + randomNum1);
                    break;
                case 1: // addLast()
                    int randomNum2 = StdRandom.uniform(10000);
                    myDeque.addLast(randomNum2);
                    stdDeque.addLast(randomNum2);
                    System.out.println("myDeque add last: " + randomNum2);
                    System.out.println("stdDeque add last: " + randomNum2);
                    break;
                case 2: // size()
                    assertEquals(myDeque.size(), stdDeque.size());
                    System.out.println("size is: " + myDeque.size());
                    break;
                case 3: // removeFirst()
                    if (stdDeque.isEmpty()) break;
                    assertEquals(myDeque.removeFirst(), stdDeque.removeFirst());
                    System.out.println("removeFirst()");
                    break;
                case 4: // removeLast()
                    if (stdDeque.isEmpty()) break;
                    assertEquals(myDeque.removeLast(), stdDeque.removeLast());
                    System.out.println("removeLast()");
                    break;
                default :
                    break;
            }
        }
        Iterator<Integer> iterator = stdDeque.iterator();
        int idx = 0;
        for (int i : stdDeque) {
            assertEquals(i, (int)myDeque.get(idx));
            idx++;
        }
    }

    @Test
    public void timingTest() {
        LinkedListDeque<Integer> myDeque = new LinkedListDeque<>();
        LinkedList<Integer> stdDeque = new LinkedList<>();
        int N = 10000000;

        // myDeque time
        Stopwatch timer1 = new Stopwatch();
        for (int i = 0; i < N; i++) {
            myDeque.addLast(i);
        }
        double time1 = timer1.elapsedTime();

        // stdDeque time
        Stopwatch timer2 = new Stopwatch();
        for (int i = 0; i < N; i++) {
            stdDeque.addLast(i);
        }
        double time2 = timer2.elapsedTime();

        System.out.println("my deque time is : " + time1 + "secs");
        System.out.println("std deque time is : " + time2 + "secs");
    }

    @Test
    public void equalsTest() {
        LinkedListDeque<String> lld1 = new LinkedListDeque<>();
        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
        lld1.addLast("yhc");
        lld2.addLast("yhc");
        lld1.addLast("ywt");
        lld2.addLast("ywt");
        assertEquals(lld1, lld2);
    }
}
