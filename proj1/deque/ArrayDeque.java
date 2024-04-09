package deque;

import java.util.Iterator;

public class ArrayDeque<Item> implements Deque<Item>,Iterable<Item> {

    private int front,tail;
    private int size;
    private Item[] items;

    public ArrayDeque() {
        front = 0;
        tail = 0;
        size = 0;
        items = (Item[]) new Object[8];
    }

    public ArrayDeque(int capacity) {
        front = 0;
        tail = 0;
        size = 0;
        items = (Item[]) new Object[capacity];
    }

    @Override
    public void addFirst(Item item) {
        if (size == items.length) {
            resize(items.length*2 + 1);
        }

        if (isEmpty()) {
            items[front] = item;
        } else {
            int idx = (front-1+items.length) % items.length;
            items[idx] = item;
            front = idx;
        }
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        if (size == items.length) {
            resize(items.length*2 + 1);
        }

        if (isEmpty()) {
            items[front] = item;
        } else {
            int idx = (tail+1) % items.length;
            items[idx] = item;
            tail = idx;
        }
        size += 1;
    }

    @Override
    public void printDeque() {
        int idx = front;
        for (int i = 0; i < size; i++) {
            Item item = items[idx];
            System.out.print(item + " ");
            idx = (idx+1) % items.length;
        }
    }

    @Override
    public Item removeFirst() {
        if (items.length >= 16 && size*4 <= items.length) {
            resize(items.length/2);
        }

        if (isEmpty()) {
            return null;
        } else {
            Item item = items[front];
            if (size() > 1) {
                front = (front+1) % items.length;
            }
            size -= 1;
            return item;
        }
    }

    @Override
    public Item removeLast() {
        if (items.length >= 16 && size*4 <= items.length) {
            resize(items.length/2);
        }

        if (isEmpty()) {
            return null;
        } else {
            Item item = items[tail];
            if (size() > 1) {
                tail = (tail-1+ items.length) % items.length;
            }
            size -= 1;
            return item;
        }
    }

    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return items[(front+index) % items.length];
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        Iterator<Item> iterator = this.iterator();
        front = 0;
        tail = 0;
        while (iterator.hasNext()) {
            newItems[tail] = iterator.next();
            tail += 1;
        }
        tail -= 1;
        items = newItems;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<Item> {
        private int index,count;
        public ArrayDequeIterator() {
            index = front;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Item next() {
            Item item = items[index];
            index = (index+1) % items.length;
            count += 1;
            return item;
        }
    }
}
