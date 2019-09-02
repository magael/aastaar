package mj.aastaar.datastructures;

/**
 *
 * @author MJ
 */
public class CustomHashMap<K, V> {

    private CustomEntry<K, V>[] buckets;
    private final int DEFAULT_INITIAL_SIZE = 16;
    private final int INITIAL_SIZE;
    private final double DEFAULT_LOAD_FACTOR = 0.75;
    private final double LOAD_FACTOR;

    public CustomHashMap() {
        INITIAL_SIZE = DEFAULT_INITIAL_SIZE;
        LOAD_FACTOR = DEFAULT_LOAD_FACTOR;
        buckets = new CustomEntry[INITIAL_SIZE];
    }
    
    public CustomHashMap(int size) {
        INITIAL_SIZE = size;
        LOAD_FACTOR = DEFAULT_LOAD_FACTOR;
        buckets = new CustomEntry[INITIAL_SIZE];
    }

    public CustomHashMap(int size, double loadFactor) {
        INITIAL_SIZE = size;
        LOAD_FACTOR = loadFactor;
        buckets = new CustomEntry[INITIAL_SIZE];
    }
    
    

    public int findIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    public void put(CustomEntry<K, V> newEntry) {
        int i = findIndex(newEntry.getKey());
        CustomEntry<K, V> currentEntry = buckets[i];
        if (currentEntry == null) {
            buckets[i] = newEntry;
        } else {
            if 
        }
    }
    
}
