package deque;

import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item>, Iterable<Item> {

    private class DequeNode {
        public Item item;
        public DequeNode prev;
        public DequeNode next;

        public DequeNode() {
            item = null;
            prev = null;
            next = null;
        }
        public DequeNode(Item item) {
            this.item = item;
        }
        public DequeNode(Item item, DequeNode prev, DequeNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private final DequeNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new DequeNode();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(Item item) {
        sentinel = new DequeNode();
        DequeNode newNode = new DequeNode(item, sentinel, sentinel);
        sentinel.prev = newNode;
        sentinel.next = newNode;
        size = 1;
    }

    @Override
    public void addFirst(Item item) {
        DequeNode newNode = new DequeNode(item, sentinel, sentinel.next);
        sentinel.next = newNode;
        newNode.next.prev = newNode;
        size += 1;
    }

    @Override
    public void addLast(Item item) {
        DequeNode lastNode = sentinel.prev;
        DequeNode newNode = new DequeNode(item, lastNode, sentinel);
        lastNode.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        DequeNode node = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            DequeNode firstNode = sentinel.next;
            sentinel.next = firstNode.next;
            firstNode.next.prev = sentinel;
            firstNode.prev = null;
            firstNode.next = null;
            size -= 1;

            return firstNode.item;
        }
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            DequeNode lastNode = sentinel.prev;
            sentinel.prev = lastNode.prev;
            lastNode.prev.next = sentinel;
            lastNode.prev = null;
            lastNode.next = null;
            size -= 1;

            return lastNode.item;
        }
    }

    @Override
    public Item get(int index) {
        if (index >= 0 && index < size) return null;
        DequeNode node = sentinel.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedListDeque) {
            if (((LinkedListDeque<?>) obj).size != this.size) {
                return false;
            } else {
                Iterator<Item> iterator1 = this.iterator();
                Iterator<?> iterator2 = ((LinkedListDeque<?>) obj).iterator();
                while (iterator1.hasNext()) {
                    if (!iterator1.next().equals(iterator2.next())) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    private class LinkedListDequeIterator implements Iterator<Item> {
        private int index;
        private DequeNode curNode;

        public LinkedListDequeIterator() {
            index = 0;
            curNode = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            Item item = curNode.item;
            curNode = curNode.next;
            index += 1;
            return item;
        }
    }
}
