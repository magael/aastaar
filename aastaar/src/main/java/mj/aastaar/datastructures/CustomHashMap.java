package mj.aastaar.datastructures;

/**
 *
 * @author MJ
 */
public class CustomHashMap<K, V> {

    private CustomEntry<K, V>[] buckets;
    private final int DEFAULT_INITIAL_SIZE = 262144; // 512^2
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
            }
            buckets[i] = newEntry;
//            checkSize();
        } else {
            currentEntry.setValue(newEntry.getValue());
        }
    }

    public void checkSize() {
        int n = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                n++;
            }
        }
        if (n > LOAD_FACTOR * buckets.length) {
            resize();
        }
    }

    public void resize() {
        CustomEntry<K, V>[] newBuckets = new CustomEntry[buckets.length * 2];
        for (CustomEntry<K, V> firstEntry : buckets) {
            if (firstEntry != null) {
                if (firstEntry.getNext() == null) {
                    // rehash the only entry in the bucket
                    rehash(firstEntry, newBuckets);
//                } else {
//                    // rehash all entries in the bucket
//                    CustomEntry<K, V> nextEntry = firstEntry;
//                    while (nextEntry != null) {
//                        rehash(nextEntry, newBuckets);
//                        nextEntry = nextEntry.getNext();
//                    }
                }
            }
        }
        buckets = newBuckets;
    }

    private void rehash(CustomEntry<K, V> entry, CustomEntry<K, V>[] newBuckets) {
        int i = entry.getKey().hashCode() % newBuckets.length;
        CustomEntry<K, V> newBucketFirst = newBuckets[i];
        // if the new bucket is not empty
        if (newBucketFirst != null) {
            newBucketFirst.setPrev(entry);
            entry.setNext(newBucketFirst);
        }
        newBuckets[i] = entry;
    }

// TODO: if load would exceed load factor, resize & rehash
//    public void put(CustomEntry<K, V> newEntry) {
//        int i = findIndex(newEntry.getKey());
//        CustomEntry<K, V> currentEntry = buckets[i];
//
//        // if the bucket is empty, set the new entry at front
//        if (currentEntry == null) {
//            buckets[i] = newEntry;
//        } else {
////            CustomEntry<K, V> previousEntry = null;
//            while (currentEntry != null) {
//                // if the key already exists in the bucket, update the value
//                if (currentEntry.getKey() == newEntry.getKey()) {
////                    newEntry.setNext(currentEntry.getNext());
////                    // if found at head, set new as head
////                    if (previousEntry == null) {
////                        buckets[i] = newEntry;
////                    } else {
////                        previousEntry.setNext(newEntry);
////                    }
////                    return;
//                    currentEntry.setValue(newEntry.getValue());
//                    return;
//                }
//                // traverse the bucket (linked list)
////                previousEntry = currentEntry;
//                currentEntry = currentEntry.getNext();
//            }
//            newEntry.setNext(buckets[i]);
//            buckets[i].setPrev(newEntry);
//            buckets[i] = newEntry;
//        }
//    }
    private int findIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    ////////////////////////////////////////////////////////////////////////////
//    // TODO: if load would exceed load factor, resize & rehash
//    public void put(CustomEntry<K, V> newEntry) {
//        int i = findIndex(newEntry.getKey());
//        CustomEntry<K, V> currentEntry = buckets[i];
//
//        // if the bucket is empty, set the new entry at front
//        if (currentEntry == null) {
//            buckets[i] = newEntry;
//        } else {
//            while (currentEntry != null) {
//                // if the key already exists in the bucket, update the value
//                if (currentEntry.getKey() == newEntry.getKey()) {
//                    currentEntry.setValue(newEntry.getValue());
//                    return;
//                // if reached the end of the linked list, set the new entry as tail
//                } else if (currentEntry.getNext() == null) {
//                    currentEntry.setNext(newEntry);
//                    newEntry.setPrev(currentEntry);
//                    return;
//                }
//                // traverse the bucket (linked list)
//                currentEntry = currentEntry.getNext();
//            }
//        }
//    }  
//            boolean updatedValue = false;
//            while (currentEntry != null) {
//                if (currentEntry.getKey() == newEntry.getKey()) {
//                    currentEntry.setValue(newEntry.getValue());
//                    updatedValue = true;
//                    return;
//                }
//                if (currentEntry.getNext() != null) {
//                    currentEntry = currentEntry.getNext();
//                } else if (!updatedValue) {
//                    currentEntry.setNext(newEntry);
//                    newEntry.setPrev(currentEntry);
//                }
//            }
//        }
//    }
//}
//    public CustomEntry<K, V> listSearch(CustomEntry<K, V> currentEntry, CustomEntry<K, V> newEntry) {
//        while (currentEntry != null) {
//            if (currentEntry.getKey() == newEntry.getKey()) {
//                return currentEntry;
//            }
//            currentEntry = currentEntry.getNext();
//        }
//        return null;
//    }
}
