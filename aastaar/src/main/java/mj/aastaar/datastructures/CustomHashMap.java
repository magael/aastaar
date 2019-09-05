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
    private int filledBucketCount;

    public CustomHashMap() {
        INITIAL_SIZE = DEFAULT_INITIAL_SIZE;
        LOAD_FACTOR = DEFAULT_LOAD_FACTOR;
        buckets = new CustomEntry[INITIAL_SIZE];
        filledBucketCount = 0;
    }

    public CustomHashMap(int size) {
        INITIAL_SIZE = size;
        LOAD_FACTOR = DEFAULT_LOAD_FACTOR;
        buckets = new CustomEntry[INITIAL_SIZE];
        filledBucketCount = 0;
    }

    public CustomHashMap(int size, double loadFactor) {
        INITIAL_SIZE = size;
        LOAD_FACTOR = loadFactor;
        buckets = new CustomEntry[INITIAL_SIZE];
        filledBucketCount = 0;
    }

    public boolean containsKey(K key) {
        return find(key) != null;
    }

    public V get(K key) {
        return find(key).getValue();
    }

    public CustomEntry<K, V> find(K key) {
        int i = findIndex(key);
        CustomEntry<K, V> currentEntry = buckets[i];

        while (currentEntry != null && !currentEntry.getKey().equals(key)) {
            currentEntry = currentEntry.getNext();
        }
        return currentEntry;
    }

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
            if (filledBucketCount > LOAD_FACTOR * buckets.length) {
                System.out.println("overload");
                filledBucketCount = 0;
                resize();
            }
        } else {
            currentEntry.setValue(newEntry.getValue());
        }
    }

    private void resize() {
        CustomEntry<K, V>[] newBuckets = new CustomEntry[buckets.length * 2];
        for (int i = 0; i < buckets.length; i++) {
            CustomEntry<K, V> currentEntry = buckets[i];
            // rehash all entries in the bucket
            while (currentEntry != null) {
                rehash(currentEntry, newBuckets);
                currentEntry = currentEntry.getNext();
            }
        }
        buckets = newBuckets;
//        System.out.println("whew took it's time huh");
    }

    private void rehash(CustomEntry<K, V> entry, CustomEntry<K, V>[] newBuckets) {
        CustomEntry<K, V> entryCopy = new CustomEntry<K, V>(entry.getKey(), entry.getValue());
        int i = entryCopy.getKey().hashCode() % newBuckets.length;
        CustomEntry<K, V> newBucketFirst = newBuckets[i];
        // if the new bucket is not empty
        if (newBucketFirst != null) {
            newBucketFirst.setPrev(entryCopy);
            entryCopy.setNext(newBucketFirst);
        } else {
            filledBucketCount++;
        }
        newBuckets[i] = entryCopy;
    }

    private int findIndex(K key) {
        return key.hashCode() % buckets.length;
    }
}
