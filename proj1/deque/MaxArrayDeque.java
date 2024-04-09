package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        } else {
            Iterator<T> iterator = this.iterator();
            T max = iterator.next();
            while (iterator.hasNext()) {
                T next = iterator.next();
                int cmp = comparator.compare(next, max);
                if (cmp > 0) {
                    max = next;
                }
            }
            return max;
        }
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        } else {
            Iterator<T> iterator = this.iterator();
            T max = iterator.next();
            while (iterator.hasNext()) {
                T next = iterator.next();
                int cmp = c.compare(next, max);
                if (cmp > 0) {
                    max = next;
                }
            }
            return max;
        }
    }
}
