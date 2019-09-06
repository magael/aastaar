package mj.aastaar.datastructures;

/**
 * A custom implementation of the hash map data structure. Uses the CustomEntry
 * class for the map entries, and linked lists to hold them.
 * 
 * @author MJ
 * @param <K> Key
 * @param <V> Value
 */
public class CustomHashMap<K, V> {

    private CustomEntry<K, V>[] buckets;
    private final int DEFAULT_INITIAL_SIZE = 16;
    private final int INITIAL_SIZE;
    private final double DEFAULT_LOAD_FACTOR = 0.75;
    private final double LOAD_FACTOR;
    private int filledBucketCount;

    /**
     * Initializing the default size and load factor.
     */
    public CustomHashMap() {
        INITIAL_SIZE = DEFAULT_INITIAL_SIZE;
        LOAD_FACTOR = DEFAULT_LOAD_FACTOR;
        buckets = new CustomEntry[INITIAL_SIZE];
        filledBucketCount = 0;
    }

    /**
     *
     * @param size The initial hash map size
     */
    public CustomHashMap(int size) {
        INITIAL_SIZE = size;
        LOAD_FACTOR = DEFAULT_LOAD_FACTOR;
        buckets = new CustomEntry[INITIAL_SIZE];
        filledBucketCount = 0;
    }

    /**
     *
     * @param size The initial hash map size
     * @param loadFactor The initial load factor
     */
    public CustomHashMap(int size, double loadFactor) {
        INITIAL_SIZE = size;
        LOAD_FACTOR = loadFactor;
        buckets = new CustomEntry[INITIAL_SIZE];
        filledBucketCount = 0;
    }

    /**
     * Checking if the map contains an entry with a specific key.
     *
     * @param key Key
     * @return True if the key is in the map key set, otherwise false
     */
    public boolean containsKey(K key) {
        return find(key) != null;
    }

    /**
     * Retrieving the value of an entry with a specific key.
     *
     * @param key Key
     * @return The value of the corresponding key or null, if the key is not
     * found in the map key set
     */
    public V get(K key) {
        return find(key).getValue();
    }

    /**
     * A linked list search to find an entry with a specific key.
     *
     * @param key Key
     * @return An entry object corresponding to the given key
     */
    public CustomEntry<K, V> find(K key) {
        int i = findIndex(key);
        CustomEntry<K, V> currentEntry = buckets[i];

        while (currentEntry != null && !currentEntry.getKey().equals(key)) {
            currentEntry = currentEntry.getNext();
        }
        return currentEntry;
    }

    /**
     * Inserting a new value to the map, or updating the value of an existing
     * key. Keeping track of the load factor.
     *
     * @param key Key
     * @param value Value
     */
    public void put(K key, V value) {
        CustomEntry<K, V> newEntry = new CustomEntry<K, V>(key, value);
        CustomEntry<K, V> currentEntry = find(newEntry.getKey());
        if (currentEntry == null) {
            int i = findIndex(newEntry.getKey());
            currentEntry = buckets[i];
            if (currentEntry != null) {
                newEntry.setNext(currentEntry);
                currentEntry.setPrev(newEntry);
            } else {
                filledBucketCount++;
            }
            buckets[i] = newEntry;
            checkLoadFactor();
        } else {
            currentEntry.setValue(newEntry.getValue());
        }
    }

    /**
     * Calling resize if the load factor is exceeded.
     */
    private void checkLoadFactor() {
        if (filledBucketCount > LOAD_FACTOR * buckets.length) {
            filledBucketCount = 0;
            resize();
        }
    }

    /**
     *
     * @return True if the map is empty, otherwise false
     */
    public boolean isEmpty() {
        return filledBucketCount < 1;
    }

    /**
     * Doubling the map size and calling rehash on all the pre-existing entries.
     */
    private void resize() {
        CustomEntry<K, V>[] newBuckets = new CustomEntry[buckets.length * 2];
        for (int i = 0; i < buckets.length; i++) {
            CustomEntry<K, V> currentEntry = buckets[i];
            while (currentEntry != null) {
                rehash(currentEntry, newBuckets);
                currentEntry = currentEntry.getNext();
            }
        }
        buckets = newBuckets;
    }

    /**
     * Copying a pre-existing map entry to the new map to a recalculated index
     * based on the new map size.
     * 
     * @param entry The entry to rehash
     * @param newBuckets The new buckets for the map entries
     */
    private void rehash(CustomEntry<K, V> entry, CustomEntry<K, V>[] newBuckets) {
        CustomEntry<K, V> entryCopy = new CustomEntry<K, V>(entry.getKey(), entry.getValue());
        int i = entryCopy.getKey().hashCode() % newBuckets.length;
        CustomEntry<K, V> newBucketFirst = newBuckets[i];
        if (newBucketFirst != null) {
            newBucketFirst.setPrev(entryCopy);
            entryCopy.setNext(newBucketFirst);
        } else {
            filledBucketCount++;
        }
        newBuckets[i] = entryCopy;
    }

    /**
     * Find the bucket index corresponding to a specific key.
     * 
     * @param key Key
     * @return Index
     */
    private int findIndex(K key) {
//        ((ak+b) modp) modm
//        int p = 262147;
//        int a = 184626;
//        int b = 35471;
//        int i = ((a * key.hashCode() + b) % p) % buckets.length;
//        i = (i > 0) ? i : 0 - i;
//        return i;
        return key.hashCode() % buckets.length;
    }
}
