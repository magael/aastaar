package mj.aastaar.datastructures;

/**
 * A custom implementation of an entry class for CustomHashMap.
 * 
 * @author MJ
 * @param <K>
 * @param <V>
 */
public class CustomEntry<K, V> {
    
    private K key;
    private V value;
    private CustomEntry next;
    private CustomEntry prev;

    /**
     *
     * @param key Key
     * @param value Value
     */
    public CustomEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     *
     * @return Value
     */
    public V getValue() {
        return value;
    }

    /**
     *
     * @return Key
     */
    public K getKey() {
        return key;
    }
    
    /**
     *
     * @return CustomEntry object
     */
    public CustomEntry<K, V> getNext() {
        return next;
    }

    /**
     *
     * @return CustomEntry object
     */
    public CustomEntry<K, V> getPrev() {
        return prev;
    }

    /**
     *
     * @param value Value
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     *
     * @param key Key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     *
     * @param next CustomEntry object
     */
    public void setNext(CustomEntry<K, V> next) {
        this.next = next;
    }

    /**
     *
     * @param prev CustomEntry object
     */
    public void setPrev(CustomEntry<K, V> prev) {
        this.prev = prev;
    }
}
