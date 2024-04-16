package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private double maxLoad;
    private Set<K> record;

    /** Constructors */
    public MyHashMap() {
        maxLoad = 0.75;
        size = 0;
        buckets = createTable(16);
        record = new HashSet<>();
    }

    public MyHashMap(int initialSize) {
        maxLoad = 0.75;
        size = 0;
        buckets = createTable(initialSize);
        record = new HashSet<>();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.maxLoad = maxLoad;
        size = 0;
        buckets = createTable(initialSize);
        record = new HashSet<>();
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection[] buckets = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            buckets[i] = createBucket();
        }
        return buckets;
    }

    @Override
    public void clear() {
        size = 0;
        buckets = createTable(buckets.length);
        record = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        int index = Math.abs(key.hashCode()) % buckets.length;
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode()) % buckets.length;
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        double load = (double) size / buckets.length;
        if (load == maxLoad) {
            resize(buckets.length * 2);
        }
        int index = Math.abs(key.hashCode()) % buckets.length;
        if (containsKey(key)) {
            for (Node node : buckets[index]) {
                if (node.key.equals(key)) {
                    node.value = value;
                    break;
                }
            }
        } else {
            buckets[index].add(createNode(key, value));
            size++;
        }
        record.add(key);
    }
    private void resize(int size) {
        Collection[] newBuckets = new Collection[size];
        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = createBucket();
        }
        for (K key : record) {
            int index = Math.abs(key.hashCode()) % buckets.length;
            newBuckets[index].add(createNode(key, this.get(key)));
        }
        buckets = newBuckets;
    }

    @Override
    public Set<K> keySet() {
        return record;
    }

    @Override
    public Iterator<K> iterator() {
        return record.iterator();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyHashMap<String, String> dictionary = new MyHashMap<>();
        dictionary.put("hello", "world");
        dictionary.put("hello", "kevin");
        System.out.println("hello".hashCode());
        System.out.println("hello".hashCode());
        System.out.println(dictionary.size());
    }

}
